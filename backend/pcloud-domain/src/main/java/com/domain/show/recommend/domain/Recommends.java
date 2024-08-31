package com.domain.show.recommend.domain;

import com.domain.show.popups.domain.service.PopularityCalculator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Recommends {

    private final List<Recommend> recommends;

    public static Recommends from(final List<Recommend> recommends) {
        return new Recommends(recommends);
    }

    public List<Recommend> findPopularityShows(final PopularityCalculator popularityCalculator, final int limit) {
        return recommends.stream()
                .sorted(makeReverseOrder(popularityCalculator))
                .limit(limit)
                .toList();
    }

    private Comparator<Recommend> makeReverseOrder(final PopularityCalculator popularityCalculator) {
        return (o1, o2) -> Double.compare(
                o2.calculatePopularScore(popularityCalculator),
                o1.calculatePopularScore(popularityCalculator)
        );
    }
}
