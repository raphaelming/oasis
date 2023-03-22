package com.raphaelming.oasis.service;


import com.raphaelming.oasis.dto.SearchDto;
import com.raphaelming.oasis.entity.SearchResult;
import com.raphaelming.oasis.repository.PopularSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@Service
public class SearchApiService {

    @Value("${api-spec.kakao.blog.host}")
    private String kakaoBlogHost;

    @Value("${api-spec.kakao.blog.uri}")
    private String kakaoBlogUri;

    @Value("${api-spec.kakao.rest.apikey}")
    private String kakaoApiKey;

    @Autowired
    private PopularSearchRepository popularSearchRepository;

    public SearchResult<?> searchBlog(SearchDto searchDto) {
        SearchResult result = null;

        try {

            ResponseEntity<SearchResult> response = getKakaoBlog(searchDto);
            result = response.getBody();
        } catch (HttpClientErrorException e) {
            if (HttpStatus.OK != e.getStatusCode()){
                System.out.println("naver 요청");
            }
        }
        return result;

    }

    public ResponseEntity<SearchResult> getKakaoBlog(SearchDto searchDto) {
        System.out.println("kakaoBlogHost = " + kakaoBlogHost);
        System.out.println("kakaoBlogUri = " + kakaoBlogUri);
        ResponseEntity<SearchResult> response = null;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", kakaoApiKey);
        HttpEntity request = new HttpEntity(headers);

        URI uri = UriComponentsBuilder
                .fromUriString(kakaoBlogHost)
                .path(kakaoBlogUri)
                .queryParam("query", searchDto.getQuery())
                .queryParam("sort", searchDto.getSort())
                .queryParam("size", searchDto.getSize())
                .queryParam("page", searchDto.getPage())
                .encode()
                .build()
                .toUri();
        System.out.println("uri = " + uri);
        RestTemplate restTemplate = new RestTemplate();
        response = restTemplate.exchange(uri, HttpMethod.GET, request, SearchResult.class);

        System.out.println("response.getBody() = " + response.getBody());
        System.out.println("response.getStatusCode() = " + response.getStatusCode());
//        popularSearchRepository.updateScoreByKeyword(searchDto.getQuery(), LocalDate.now());

        return response;

    }

    public SearchResult<?> getNaverBlog(SearchDto searchDto) {
        return null;
    }


}
