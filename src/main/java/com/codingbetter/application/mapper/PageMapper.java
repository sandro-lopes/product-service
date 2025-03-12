package com.codingbetter.application.mapper;

import com.codingbetter.application.controller.response.PageResponse;
import com.codingbetter.domain.shared.model.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Mapper class to convert between domain Page and PageResponse.
 */
public class PageMapper {

    private PageMapper() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts a domain Page to a PageResponse.
     *
     * @param page Domain Page
     * @param mapper Function to map domain entity to DTO
     * @param <T> Domain entity type
     * @param <R> DTO type
     * @return PageResponse
     */
    public static <T, R> PageResponse<R> toResponse(Page<T> page, Function<T, R> mapper) {
        if (page == null) {
            return new PageResponse<>();
        }

        List<R> content = page.getContent().stream()
                .map(mapper)
                .collect(Collectors.toList());

        return new PageResponse<>(
                content,
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getPage(),
                page.getPageElements(),
                page.isFirst(),
                page.isLast(),
                page.hasNext(),
                page.hasPrevious()
        );
    }
} 