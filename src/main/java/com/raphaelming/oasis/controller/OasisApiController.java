package com.raphaelming.oasis.controller;


import com.raphaelming.oasis.dto.SearchDto;
import com.raphaelming.oasis.entity.SearchResult;
import com.raphaelming.oasis.repository.PopularSearchRepository;
import com.raphaelming.oasis.service.SearchApiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class OasisApiController {

    @Value("${kakao.rest.apikey}")
    private String apiKey;

    @Autowired
    private SearchApiService searchApiService;







    @GetMapping("/v1/test")
    public void getTest() {
        System.out.println("apiKey = " + apiKey);
    }

    @GetMapping("/v1/blog")
    public SearchResult getBlog(@Valid SearchDto searchDto) {
        SearchResult<?> result = searchApiService.searchBlog(searchDto);
        return result;
    }

}
