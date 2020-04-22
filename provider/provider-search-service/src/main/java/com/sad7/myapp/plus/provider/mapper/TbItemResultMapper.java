package com.sad7.myapp.plus.provider.mapper;

import com.sad7.myapp.plus.provider.domain.TbItemResult;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbItemResultMapper {
    List<TbItemResult> selectAll();
}
