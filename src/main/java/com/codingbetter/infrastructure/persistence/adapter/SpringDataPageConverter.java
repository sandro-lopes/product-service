package com.codingbetter.infrastructure.persistence.adapter;

import com.codingbetter.domain.shared.model.Page;
import com.codingbetter.domain.shared.model.PageUtils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Adapter to convert Spring Data MongoDB Page to domain Page.
 */
public class SpringDataPageConverter {

    private SpringDataPageConverter() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts a Spring Data Page to a domain Page.
     *
     * @param mongoPage Spring Data Page
     * @param <T> Type of elements in the Page
     * @param <R> Type of elements in the domain Page
     * @param mapper Function to map from document to domain entity
     * @return Domain Page
     */
    public static <T, R> Page<R> toPage(org.springframework.data.domain.Page<T> mongoPage, Function<T, R> mapper) {
        if (mongoPage == null) {
            return PageUtils.empty();
        }

        List<T> content = mongoPage.getContent()
            .stream()
            .collect(Collectors.toList());

         Page<T> page = PageUtils.of(
                content,
                mongoPage.getTotalElements(),
                mongoPage.getNumber(),
                mongoPage.getSize()
        );

        return PageUtils.map(page, mapper);
    }
} 