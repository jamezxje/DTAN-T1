package com.capstone.app.entity.dto.pagination;

import lombok.Getter;

@Getter
public class PaginationResponse <T>{
    private T data;
    private final int currentPage;
    private final int totalPages;
    private final long totalItems;
    private final int pageSize;

    public PaginationResponse(T data, int currentPage, int totalPages, long totalItems, int pageSize) {
        this.data = data;
        this.currentPage = currentPage;
        this.totalPages = (int) Math.ceil((double) totalItems / pageSize);
        this.totalItems = totalItems;
        this.pageSize = pageSize;
    }

    public boolean hasNext() {
        return currentPage < totalPages;
    }

    public boolean hasPrevious() {
        return currentPage > 1;
    }
}
