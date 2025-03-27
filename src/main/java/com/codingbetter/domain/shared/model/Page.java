package com.codingbetter.domain.shared.model;

import java.util.List;

/**
 * Interface that represents a page of results for pagination.
 * @param <T> The type of object contained in the page
 */
public interface Page<T> {
    
    /**
     * Returns the content of the current page.
     * @return List of elements in the current page
     */
    List<T> getContent();
    
    /**
     * Returns the number of elements per page.
     * @return Page size
     */
    int getSize();
    
    /**
     * Returns the total number of elements found.
     * @return Total elements
     */
    long getTotalElements();
    
    /**
     * Returns the total number of pages.
     * @return Total pages
     */
    int getTotalPages();
    
    /**
     * Returns the current page number (starting from 0).
     * @return Current page number
     */
    int getPage();
    
    /**
     * Returns the number of elements in the current page.
     * @return Number of elements in the current page
     */
    int getPageElements();
    
    /**
     * Checks if the current page is the first page.
     * @return true if it's the first page, false otherwise
     */
    boolean isFirst();
    
    /**
     * Checks if the current page is the last page.
     * @return true if it's the last page, false otherwise
     */
    boolean isLast();
    
    /**
     * Checks if there is a next page.
     * @return true if there is a next page, false otherwise
     */
    boolean hasNext();
    
    /**
     * Checks if there is a previous page.
     * @return true if there is a previous page, false otherwise
     */
    boolean hasPrevious();
} 