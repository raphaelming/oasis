package com.raphaelming.oasis.repository;

import com.raphaelming.oasis.entity.PopularSearchKeyword;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toList;


@RequiredArgsConstructor
@Repository
public class PopularSearchRepositoryImpl implements  PopularSearchRepository {


    private static final String KEY_FORMAT = "Popular-rank:%s";

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void updateScoreByKeyword(String keyword, LocalDate baseDate) {
        redisTemplate.opsForZSet().incrementScore(toKey(baseDate), keyword, 1d);

    }

    @Override
    public List<PopularSearchKeyword> findTopPopularByScoreDesc(int count, LocalDate baseDate) {
        Set<ZSetOperations.TypedTuple<String>> tuples = redisTemplate.opsForZSet().reverseRangeWithScores(toKey(baseDate), 0, count - 1);
        if (Objects.isNull(tuples)) {
            return Collections.emptyList();
        }

        return tuples.stream()
                .map(tuple -> PopularSearchKeyword.builder()
                        .keyword(tuple.getValue())
                        .searchCount(tuple.getScore().longValue())
                        .build())
                .collect(toList());
    }

    private String toKey(LocalDate localDate) {
        return String.format(KEY_FORMAT, localDate) ;
    }
}
