package com.codingbetter.infrastructure.persistence.adapter;

import com.codingbetter.domain.shared.model.Page;
import com.codingbetter.domain.shared.model.PageImpl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Adapter to convert Spring Data MongoDB Page to domain Page.
 */
public class MongoPageAdapter {

    private MongoPageAdapter() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts a Spring Data MongoDB Page to a domain Page.
     *
     * @param mongoPage Spring Data MongoDB Page
     * @param <T> Type of elements in the MongoDB Page
     * @param <R> Type of elements in the domain Page
     * @param mapper Function to map from MongoDB document to domain entity
     * @return Domain Page
     */
    public static <T, R> Page<R> toPage(org.springframework.data.domain.Page<T> mongoPage, Function<T, R> mapper) {
        if (mongoPage == null) {
            return new PageImpl<>();
        }

        List<R> content = mongoPage.getContent().stream()
                .map(mapper)
                .collect(Collectors.toList());

        return new PageImpl<>(
                content,
                mongoPage.getNumber(),
                mongoPage.getSize(),
                mongoPage.getTotalElements()
        );
    }

    /**
     * Converts a Spring Data MongoDB Page to a domain Page without mapping.
     *
     * @param mongoPage Spring Data MongoDB Page
     * @param <T> Type of elements in the page
     * @return Domain Page
     */
    public static <T> Page<T> toPage(org.springframework.data.domain.Page<T> mongoPage) {
        if (mongoPage == null) {
            return new PageImpl<>();
        }

        return new PageImpl<>(
                mongoPage.getContent(),
                mongoPage.getNumber(),
                mongoPage.getSize(),
                mongoPage.getTotalElements()
        );
    }
} 