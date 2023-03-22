package com.raphaelming.oasis.entity;


import lombok.Data;

import java.util.Map;

@Data
//@AllArgsConstructor
public class SearchResult<T> {
    private int count;

    private int code;
    private T documents;
    private Map meta;
}
