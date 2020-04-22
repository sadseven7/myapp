package com.sad7.myapp.plus.tool.mongodb.dao;

import com.sad7.myapp.plus.tool.mongodb.po.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment,String> {

    /**
     * 根据父id，查询子评论的分页列表
     * @param parentid
     * @param pageable
     * @return
     */
    Page<Comment> findByParentid(String parentid, Pageable pageable);

    /**
     * 根据用户id，查询子评论的分页列表
     * @param userid
     * @param pageable
     * @return
     */
    Page<Comment> findByUserid(String userid, Pageable pageable);
}
