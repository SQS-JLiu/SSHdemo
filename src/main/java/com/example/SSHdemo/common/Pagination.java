package com.example.SSHdemo.common;

public class Pagination {

    private String baseUrl;
    private int currentPage;
    private int firstPage;
    private int lastPage;
    private int pageSize;
    private int totalPages;
    private boolean isFirstPage;
    private boolean isLastPage;

    public Pagination(String baseUrl, int currentPage, int firstPage,
                      int lastPage, int pageSize, int totalPages,
                      boolean isFirstPage, boolean isLastPage) {
        this.baseUrl = baseUrl;
        this.currentPage = currentPage;
        this.firstPage = firstPage;
        this.lastPage = lastPage;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.isFirstPage = isFirstPage;
        this.isLastPage = isLastPage;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pagination that = (Pagination) o;

        if (currentPage != that.currentPage) return false;
        if (firstPage != that.firstPage) return false;
        if (lastPage != that.lastPage) return false;
        if (pageSize != that.pageSize) return false;
        if (totalPages != that.totalPages) return false;
        if (isFirstPage != that.isFirstPage) return false;
        if (isLastPage != that.isLastPage) return false;
        return baseUrl != null ? baseUrl.equals(that.baseUrl) : that.baseUrl == null;
    }

    @Override
    public int hashCode() {
        int result = baseUrl != null ? baseUrl.hashCode() : 0;
        result = 31 * result + currentPage;
        result = 31 * result + firstPage;
        result = 31 * result + lastPage;
        result = 31 * result + pageSize;
        result = 31 * result + totalPages;
        result = 31 * result + (isFirstPage ? 1 : 0);
        result = 31 * result + (isLastPage ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pagination{" +
                "baseUrl='" + baseUrl + '\'' +
                ", currentPage=" + currentPage +
                ", firstPage=" + firstPage +
                ", lastPage=" + lastPage +
                ", pageSize=" + pageSize +
                ", totalPages=" + totalPages +
                ", isFirstPage=" + isFirstPage +
                ", isLastPage=" + isLastPage +
                '}';
    }
}
