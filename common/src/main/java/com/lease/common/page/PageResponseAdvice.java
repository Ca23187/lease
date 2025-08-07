package com.lease.common.page;

import com.lease.common.result.Result;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class PageResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {

        // 如果不是 Result 类型，不处理
        if (!(body instanceof Result<?> result)) {
            return body;
        }

        Object data = result.getData();

        // 如果是分页数据，转换 Page -> PageResult
        if (data instanceof Page<?> page) {
            PageResult<?> pageResult = new PageResult<>();

            pageResult.setRecords(page.getContent());
            pageResult.setTotal(page.getTotalElements());
            pageResult.setSize(page.getSize());
            pageResult.setCurrent(page.getNumber() + 1);
            pageResult.setPages(page.getTotalPages());

            // orders 转换（可选）
            List<String> orders = new ArrayList<>();
            for (Sort.Order order : page.getPageable().getSort()) {
                orders.add(order.getProperty() + " " + order.getDirection().name());
            }
            pageResult.setOrders(orders);

            return Result.ok(pageResult);
        }

        // 不是分页就原样返回
        return result;
    }
}
