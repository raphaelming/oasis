package com.raphaelming.oasis.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SearchDto {

    @NotBlank
    private String query;
    private String sort;
    private String page;
    private String size;
}
