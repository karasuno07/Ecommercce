package com.techstore.ecommerce.object.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
public class Pagination {

    @JsonProperty("page")
    private int pageNumber;

    @JsonProperty("limit")
    private int pageSize;

    @JsonProperty("sort")
    private Sorting sorting;

    public Pagination(int pageSize) {
        this.pageSize = pageSize;
    }

    public Pageable getPageAndSort() {
        Sort sort = Sort.by(sorting.isAsc() ? Sort.Direction.ASC : Sort.Direction.DESC,
                            sorting.getFieldName());
        return PageRequest.of(pageNumber, pageSize, sort);
    }
}
