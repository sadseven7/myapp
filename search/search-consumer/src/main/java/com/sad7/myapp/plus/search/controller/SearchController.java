package com.sad7.myapp.plus.search.controller;

import com.sad7.myapp.plus.provider.api.SearchService;
import com.sad7.myapp.plus.provider.domain.TbItemResult;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "search")
public class SearchController {

    @Reference(version = "${services.versions.search.v1}")
    private SearchService searchService;

    @GetMapping(value = "{query}/{page}/{rows}")
    public List<TbItemResult> search(
            @PathVariable(required = true) String query,
            @PathVariable(required = true) int page,
            @PathVariable(required = true) int rows
    ) {
        return searchService.search(query, page, rows);
    }
}
