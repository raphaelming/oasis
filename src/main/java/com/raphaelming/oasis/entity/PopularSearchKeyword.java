package com.raphaelming.oasis.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
public class PopularSearchKeyword {

    private String keyword;
    private  long searchCount;

}
