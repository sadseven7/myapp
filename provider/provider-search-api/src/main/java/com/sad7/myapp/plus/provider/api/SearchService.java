package com.sad7.myapp.plus.provider.api;

import com.sad7.myapp.plus.provider.domain.TbItemResult;

import java.util.List;


public interface SearchService {
    /**
     * 搜索产品信息
     *
     * @param query {@code String} 查询
     * @param page {@code int} 页数
     * @param rows {@code int} 行数
     * @return {@code int} 大于 0 则表示添加成功
     */
    List<TbItemResult> search(String query, int page, int rows);
}
