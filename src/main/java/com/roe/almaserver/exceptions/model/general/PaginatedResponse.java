package com.roe.almaserver.exceptions.model.general;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collections;
import java.util.List;

public class PaginatedResponse<T> {
    public final List<T> pageData;
    public final Integer total;

    public PaginatedResponse(List<T> pageData, Integer total) {
        this.pageData = pageData;
        this.total = total;
    }

    @JsonIgnore
    public boolean isEmptyPage() {
        return pageData.isEmpty();
    }

    public static <T> PaginatedResponse<T> empty() {
        return new PaginatedResponse<>(Collections.emptyList(), 0);
    }

    public List<T> getPageData() {
        return pageData;
    }

    public Integer getTotal() {
        return total;
    }
}
