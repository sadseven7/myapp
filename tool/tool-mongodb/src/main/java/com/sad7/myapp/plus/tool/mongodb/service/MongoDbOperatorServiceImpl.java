package com.sad7.myapp.plus.tool.mongodb.service;

import com.google.common.collect.ImmutableMap;
import com.mongodb.client.result.UpdateResult;
import com.sad7.myapp.plus.tool.mongodb.api.MongoDbOperatorService;
import lombok.Data;
import org.apache.dubbo.config.annotation.Service;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Class;
/**
 * 操作类.
 */
@Service(version = "${services.versions.search.v1}")
public class MongoDbOperatorServiceImpl<T> implements MongoDbOperatorService<T>  {

    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private Class<T> tClass;

    public MongoDbOperatorServiceImpl(MongoTemplate mongoTemplate, Class<T> classz) {
        this.mongoTemplate = mongoTemplate;
        this.tClass = classz;
    }

    @Override
    public T getOne(ObjectId id) {
        return mongoTemplate.findById(id, tClass);
    }
    @Override
    public T getOne(String id) {
        return getOne(new ObjectId(id));
    }

    @Override
    public T getOne(Map<String, Object> filter) {
        Assert.notNull(filter, "过滤条件不能为空");
        Query queryFilter = getQueryFilter(filter);

        return mongoTemplate.findOne(queryFilter, tClass);
    }

    /**
     * 获取单个model
     *
     * @param filter 过滤条件
     * @param sort   Sort theSort = new Sort(Sort.Direction.ASC, "_id");
     */
    @Override
    public T getOne(Map<String, Object> filter, Sort sort) {
        Query queryFilter = getQueryFilter(filter);
        queryFilter = queryFilter.with(sort);

        return mongoTemplate.findOne(queryFilter, tClass);
    }

    /**
     * 按访问时间倒序排列
     *
     * @param filter 过滤条件
     * @param sort   排序条件
     * @param limit  前几个(0:不限制)
     */
    @Override
    public List<T> list(Map<String, Object> filter, Sort sort, int limit) {
        Query queryFilter = getQueryFilter(filter);
        if (sort != null) {
            queryFilter = queryFilter.with(sort);
        }
        if (limit > 0) {
            queryFilter = queryFilter.limit(limit);
        }

        return mongoTemplate.find(queryFilter, tClass);
    }

    /**
     * 按访问时间倒序排列
     *
     * @param filter 过滤条件
     */
    @Override
    public long count(Map<String, Object> filter) {
        Query queryFilter = getQueryFilter(filter);

        return mongoTemplate.count(queryFilter, tClass);
    }

    /**
     * 分页查询
     *
     * @param filter    过滤条件
     * @param sort      排序条件
     * @param pageIndex 第几页
     * @param pageSize  每页大小
     */
    @Override
    public Map pagedList(Map<String, Object> filter, Sort sort, int pageIndex, int pageSize) {
        Query queryFilter = getQueryFilter(filter);
        long count = count(queryFilter);
        if (sort != null) {
            queryFilter = queryFilter.with(sort);
        }
        queryFilter = queryFilter.skip((pageIndex - 1) * pageSize).limit(pageSize);
        List<T> subItems = mongoTemplate.find(queryFilter, tClass);

        Map map = new HashMap();
        map.put("data",subItems);
        map.put("page",pageIndex);
        map.put("size",pageSize);
        map.put("count",count);

        return map;
    }


    /**
     * 按访问时间倒序排列
     *
     * @param filter 过滤条件
     */
    @Override
    public List<T> list(Map<String, Object> filter) {
        return list(filter, null, 0);
    }


    /**
     * 插入新数据
     * 返回的id在obj中赋值
     */
    @Override
    public void insert(T obj) {
        mongoTemplate.insert(obj);
    }

    @Override
    public long updateOne(String id, Map<String, Object> filedValues) {
        return updateOne(new ObjectId(id), filedValues);
    }

    @Override
    public long updateOne(ObjectId id, Map<String, Object> filedValues) {
        Query queryFilter = getQueryFilter(id);

        return updateImpl(queryFilter, filedValues);
    }

    @Override
    public long update(Map<String, Object> filter, Map<String, Object> filedValues) {
        Query queryFilter = getQueryFilter(filter);

        return updateImpl(queryFilter, filedValues);
    }

    @Override
    public boolean exists(Map<String, Object> filter) {
        return getOne(filter) != null;
    }

    /**
     * 删除
     *
     * @param id _id
     */
    @Override
    public long delete(String id) {
        Query queryFilter = getQueryFilter(new ObjectId(id));

        return delete(queryFilter);
    }

    /**
     * 删除
     *
     * @param id _id
     */
    @Override
    public long delete(ObjectId id) {
        Query queryFilter = getQueryFilter(id);

        return delete(queryFilter);
    }

    /**
     * 删除
     *
     * @param filter 用户Id
     */
    @Override
    public long delete(Map<String, Object> filter) {
        Query queryFilter = getQueryFilter(filter);

        return delete(queryFilter);
    }

    /**
     * 软删除
     *
     * @param filter 用户Id
     */
    @Override
    public long softDelete(Map<String, Object> filter) {
        Query queryFilter = getQueryFilter(filter);
        UpdateResult result = mongoTemplate.updateMulti(queryFilter, Update.update("isDel", 1), tClass);

        return result.getModifiedCount();
    }

    /**
     * 根据当前表名获取自增id
     *
     *  collectionName 表名
     * @return 自增id
     */
    @Override
    public int getNextSequence() {
        String collectionName = mongoTemplate.getCollectionName(tClass);
        Query query = new Query(Criteria.where("_id").is(collectionName));
        Update update = new Update();
        update.inc("seq", 1);

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.upsert(true);
        options.returnNew(true);

        MongoSequence item = mongoTemplate.findAndModify(query, update, options, MongoSequence.class);

        return item.getSeq();
    }


    private long count(Query queryFilter) {
        return mongoTemplate.count(queryFilter, tClass);
    }

    /**
     * 删除
     *
     * @param queryFilter 用户Id
     */
    private long delete(Query queryFilter) {
        return mongoTemplate.remove(queryFilter, tClass).getDeletedCount();
    }

    private long updateImpl(Query queryFilter, Map<String, Object> filedValues) {
        Update updateOperations = new Update();
        for (Map.Entry<String, Object> entry : filedValues.entrySet()) {
            updateOperations.set(entry.getKey(), entry.getValue());
        }
        UpdateResult writeResult = mongoTemplate.updateMulti(queryFilter, updateOperations, tClass);
        return writeResult.getModifiedCount();
    }

    private Query getQueryFilter(ObjectId id) {
        return getQueryFilter(ImmutableMap.of("_id", id));
    }

    private Query getQueryFilter(Map<String, Object> filter) {
        Query query = new Query();
        if (CollectionUtils.isEmpty(filter)) {
            return query;
        }
        Criteria criteria = new Criteria();
        boolean w = false;
        for (Map.Entry<String, Object> entry : filter.entrySet()) {
            if (!w) {
                criteria = (Criteria.where(entry.getKey()).is(entry.getValue()));
                w = true;
            } else {
                criteria = criteria.and(entry.getKey()).is(entry.getValue());
            }
        }
        query.addCriteria(criteria);
        return query;
    }

    private Update getUpdateOperations(Map<String, Object> filedValues) {
        Update updateOperations = new Update();
        updateOperations.inc("version", 1);
        updateOperations.set("lastModifyTime", System.currentTimeMillis());
        for (Map.Entry<String, Object> entry : filedValues.entrySet()) {
            updateOperations.set(entry.getKey(), entry.getValue());
        }

        return updateOperations;
    }

    private Update getSoftDeleteOperations() {
        return getUpdateOperations(ImmutableMap.of("deleted", true));
    }

    /**
     * 自增序列维护表
     */
    @Data
    @Document(collection = "___MongoSequence___")
    private static class MongoSequence {
        /**
         * 表名做主键
         */
        @Id
        private String id;

        /**
         * 对应表的最新自增id
         */
        private int seq;
    }
}
