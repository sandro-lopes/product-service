package com.codingbetter.domain.shared.util;

import com.codingbetter.domain.shared.model.Page;
import com.codingbetter.domain.shared.model.PageImpl;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Utility class for pagination-related operations.
 */
public final class PageUtils {

    private PageUtils() {
        // Private constructor to prevent instantiation
    }

    /**
     * Creates an empty page.
     *
     * @param <T> The type of object contained in the page
     * @return An empty page
     */
    public static <T> Page<T> emptyPage() {
        return new PageImpl<>();
    }

    /**
     * Creates a page from a complete list, applying pagination manually.
     *
     * @param list Complete list of elements
     * @param page Page number (starting from 0)
     * @param size Page size
     * @param <T>  The type of object contained in the page
     * @return A page with the corresponding elements
     */
    public static <T> Page<T> createPageFromList(List<T> list, int page, int size) {
        if (list == null || list.isEmpty()) {
            return new PageImpl<>(Collections.emptyList(), page, size, 0);
        }

        int totalElements = list.size();
        int fromIndex = page * size;
        
        if (fromIndex >= totalElements) {
            return new PageImpl<>(Collections.emptyList(), page, size, totalElements);
        }

        int toIndex = Math.min(fromIndex + size, totalElements);
        List<T> pageContent = list.subList(fromIndex, toIndex);

        return new PageImpl<>(pageContent, page, size, totalElements);
    }

    /**
     * Maps a page from one type to another using a mapping function.
     *
     * @param page Original page
     * @param mapper Mapping function
     * @param <T> Original type
     * @param <R> Target type
     * @return New page with mapped elements
     */
    public static <T, R> Page<R> map(Page<T> page, Function<T, R> mapper) {
        List<R> mappedContent = page.getContent().stream()
                .map(mapper)
                .collect(Collectors.toList());
        
        return new PageImpl<>(
                mappedContent,
                page.getPage(),
                page.getSize(),
                page.getTotalElements()
        );
    }
} 