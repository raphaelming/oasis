package com.raphaelming.oasis.controller;


import com.raphaelming.oasis.dto.SearchDto;
import com.raphaelming.oasis.entity.SearchResult;
import com.raphaelming.oasis.repository.PopularSearchRepository;
import com.raphaelming.oasis.service.SearchApiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class OasisApiController {


    private final SearchApiService searchApiService;



    @GetMapping("/v1/blog")
    public SearchResult getBlog(@Valid SearchDto searchDto) {
        SearchResult<?> result = searchApiService.searchBlog(searchDto);
        return result;
    }


}
