package com.lease.common.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    private List<?> records;
    private long total;
    private long size;
    private long current;
    private List<String> orders = new ArrayList<>();
    private boolean optimizeCountSql = true;
    private boolean searchCount = true;
    private Long maxLimit = null;
    private String countId = null;
    private long pages;
}
