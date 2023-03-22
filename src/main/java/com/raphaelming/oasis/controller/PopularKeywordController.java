package com.raphaelming.oasis.controller;


import com.raphaelming.oasis.entity.PopularSearchKeyword;
import com.raphaelming.oasis.repository.PopularSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PopularKeywordController {

    private final PopularSearchRepository popularSearchRepository;


    @GetMapping("/v1/trend")
    public List<PopularSearchKeyword> get() {
        List<PopularSearchKeyword> result = popularSearchRepository.findTopPopularByScoreDesc(10, LocalDate.now());
        return result;
    }




}
