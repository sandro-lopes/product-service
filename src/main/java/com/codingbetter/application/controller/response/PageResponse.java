package com.codingbetter.application.controller.response;

import java.util.Collections;
import java.util.List;

/**
 * DTO representing a page of results for REST API responses.
 * @param <T> The type of object contained in the page
 */
public class PageResponse<T> {
    
    private List<T> content;
    private int size;
    private long totalElements;
    private int totalPages;
    private int page;
    private int pageElements;
    private boolean first;
    private boolean last;
    private boolean hasNext;
    private boolean hasPrevious;
    
    public PageResponse(List<T> content, int size, long totalElements, int totalPages, 
                  int page, int pageElements, boolean first, boolean last, 
                  boolean hasNext, boolean hasPrevious) {
        this.content = content;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.page = page;
        this.pageElements = pageElements;
        this.first = first;
        this.last = last;
        this.hasNext = hasNext;
        this.hasPrevious = hasPrevious;
    }

    /**
     * Creates an empty page response.
     *
     * @param <T> Type of elements in the page
     * @return Empty page response
     */
    public static <T> PageResponse<T> empty() {
        return new PageResponse<>(
            Collections.emptyList(),
            0,
            0,
            0,
            0,
            0,
            true,
            true,
            false,
            false
        );
    }
    
    public List<T> getContent() {
        return content;
    }
    
    public void setContent(List<T> content) {
        this.content = content;
    }
    
    public int getSize() {
        return size;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    
    public long getTotalElements() {
        return totalElements;
    }
    
    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
    
    public int getTotalPages() {
        return totalPages;
    }
    
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    
    public int getPage() {
        return page;
    }
    
    public void setPage(int page) {
        this.page = page;
    }
    
    public int getPageElements() {
        return pageElements;
    }
    
    public void setPageElements(int pageElements) {
        this.pageElements = pageElements;
    }
    
    public boolean isFirst() {
        return first;
    }
    
    public void setFirst(boolean first) {
        this.first = first;
    }
    
    public boolean isLast() {
        return last;
    }
    
    public void setLast(boolean last) {
        this.last = last;
    }
    
    public boolean isHasNext() {
        return hasNext;
    }
    
    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }
    
    public boolean isHasPrevious() {
        return hasPrevious;
    }
    
    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }
} 