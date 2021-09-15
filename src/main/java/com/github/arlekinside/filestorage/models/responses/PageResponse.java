package com.github.arlekinside.filestorage.models.responses;

import com.github.arlekinside.filestorage.models.File;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * API response model to GET /file
 */
public class PageResponse {

    private final int total;
    private final List<File> page;

    private PageResponse(PageResponseBuilder builder) {
        this.total = builder.total;
        this.page = builder.files;
    }

    public int getTotal() {
        return total;
    }

    public List<File> getPage() {
        return page;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageResponse that = (PageResponse) o;
        return total == that.total && Objects.equals(page, that.page);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, page);
    }

    @Override
    public String toString() {
        return "PageResponse{" +
                "total=" + total +
                ", page=" + page +
                '}';
    }

    public static class PageResponseBuilder {

        private int total = 0;
        private int size = 10;
        private int pageNum = 0;
        private List<File> files;

        /**
         *
         * @param files List taken directly from the database
         */
        public PageResponseBuilder(List<File> files) {
            this.files = files;
        }

        /**
         *
         * @param size Page's size taken directly from an API request
         */
        public PageResponseBuilder size(int size){
            this.size = size;
            return this;
        }

        /**
         *
         * @param pageNum Page's number taken directly from an API request
         */
        public PageResponseBuilder pageNum(int pageNum){
            this.pageNum = pageNum;
            return this;
        }

        public PageResponse build(){
            total = files.size();
            files = files.stream().skip((long) pageNum * size).collect(Collectors.toList());
            return new PageResponse(this);
        }
    }
}
