package com.codingbetter.domain.shared.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Default implementation of the Page interface.
 * @param <T> The type of object contained in the page
 */
public class PageImpl<T> implements Page<T> {
    
    private final List<T> content;
    private final int size;
    private final long totalElements;
    private final int totalPages;
    private final int page;
    private final int pageElements;
    
    /**
     * Constructor to create a page with all parameters.
     * 
     * @param content List of elements in the current page
     * @param page Current page number (starting from 0)
     * @param size Page size
     * @param totalElements Total number of elements found
     */
    public PageImpl(List<T> content, int page, int size, long totalElements) {
        this.content = content != null ? new ArrayList<>(content) : Collections.emptyList();
        this.page = Math.max(0, page);
        this.size = Math.max(1, size);
        this.totalElements = Math.max(0, totalElements);
        this.pageElements = this.content.size();
        
        // Calculate total pages
        this.totalPages = size > 0 ? (int) Math.ceil((double) totalElements / size) : 0;
    }
    
    /**
     * Constructor to create an empty page.
     */
    public PageImpl() {
        this(Collections.emptyList(), 0, 20, 0);
    }
    
    /**
     * Constructor to create a page with just the content.
     * 
     * @param content List of elements
     */
    public PageImpl(List<T> content) {
        this(content, 0, content != null ? content.size() : 0, content != null ? content.size() : 0);
    }
    
    @Override
    public List<T> getContent() {
        return Collections.unmodifiableList(content);
    }
    
    @Override
    public int getSize() {
        return size;
    }
    
    @Override
    public long getTotalElements() {
        return totalElements;
    }
    
    @Override
    public int getTotalPages() {
        return totalPages;
    }
    
    @Override
    public int getPage() {
        return page;
    }
    
    @Override
    public int getPageElements() {
        return pageElements;
    }
    
    @Override
    public boolean isFirst() {
        return page == 0;
    }
    
    @Override
    public boolean isLast() {
        return page >= totalPages - 1;
    }
    
    @Override
    public boolean hasNext() {
        return page < totalPages - 1;
    }
    
    @Override
    public boolean hasPrevious() {
        return page > 0;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        PageImpl<?> page1 = (PageImpl<?>) o;
        
        if (size != page1.size) {
            return false;
        }
        if (totalElements != page1.totalElements) {
            return false;
        }
        if (totalPages != page1.totalPages) {
            return false;
        }
        if (page != page1.page) {
            return false;
        }
        if (pageElements != page1.pageElements) {
            return false;
        }
        return Objects.equals(content, page1.content);
    }
    
    @Override
    public int hashCode() {
        int result = content != null ? content.hashCode() : 0;
        result = 31 * result + size;
        result = 31 * result + (int) (totalElements ^ (totalElements >>> 32));
        result = 31 * result + totalPages;
        result = 31 * result + page;
        result = 31 * result + pageElements;
        return result;
    }
    
    @Override
    public String toString() {
        return "Page{" +
                "content=" + content +
                ", size=" + size +
                ", totalElements=" + totalElements +
                ", totalPages=" + totalPages +
                ", page=" + page +
                ", pageElements=" + pageElements +
                '}';
    }
} 