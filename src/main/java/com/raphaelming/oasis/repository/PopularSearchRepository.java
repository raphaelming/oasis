package com.raphaelming.oasis.repository;

import com.raphaelming.oasis.entity.PopularSearchKeyword;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

public interface PopularSearchRepository {
    void updateScoreByKeyword(String keyword, LocalDate baseDate);
    List<PopularSearchKeyword> findTopPopularByScoreDesc(int count, LocalDate baseDate);
}
