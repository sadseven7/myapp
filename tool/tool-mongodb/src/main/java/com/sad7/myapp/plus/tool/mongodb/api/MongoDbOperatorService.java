package com.sad7.myapp.plus.tool.mongodb.api;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

public interface MongoDbOperatorService<T>  {


    public T getOne(ObjectId id);

    public T getOne(String id);

    public T getOne(Map<String, Object> filter) ;

    /**
     * 获取单个model
     *
     * @param filter 过滤条件
     * @param sort   Sort theSort = new Sort(Sort.Direction.ASC, "_id");
     */
    public T getOne(Map<String, Object> filter, Sort sort) ;

    /**
     * 按访问时间倒序排列
     *
     * @param filter 过滤条件
     * @param sort   排序条件
     * @param limit  前几个(0:不限制)
     */
    public List<T> list(Map<String, Object> filter, Sort sort, int limit) ;

    /**
     * 按访问时间倒序排列
     *
     * @param filter 过滤条件
     */
    public long count(Map<String, Object> filter) ;

    /**
     * 分页查询
     *
     * @param filter    过滤条件
     * @param sort      排序条件
     * @param pageIndex 第几页
     * @param pageSize  每页大小
     */
    public Map pagedList(Map<String, Object> filter, Sort sort, int pageIndex, int pageSize) ;


    /**
     * 按访问时间倒序排列
     *
     * @param filter 过滤条件
     */
    public List<T> list(Map<String, Object> filter);


    /**
     * 插入新数据
     * 返回的id在obj中赋值
     */
    public void insert(T obj);

    public long updateOne(String id, Map<String, Object> filedValues);

    public long updateOne(ObjectId id, Map<String, Object> filedValues) ;

    public long update(Map<String, Object> filter, Map<String, Object> filedValues) ;

    public boolean exists(Map<String, Object> filter);

    /**
     * 删除
     *
     * @param id _id
     */
    public long delete(String id) ;

    /**
     * 删除
     *
     * @param id _id
     */
    public long delete(ObjectId id) ;

    /**
     * 删除
     *
     * @param filter 用户Id
     */
    public long delete(Map<String, Object> filter) ;

    /**
     * 软删除
     *
     * @param filter 用户Id
     */
    public long softDelete(Map<String, Object> filter);

    /**
     * 根据当前表名获取自增id
     *
     *  collectionName 表名
     * @return 自增id
     */
    public int getNextSequence() ;


}
