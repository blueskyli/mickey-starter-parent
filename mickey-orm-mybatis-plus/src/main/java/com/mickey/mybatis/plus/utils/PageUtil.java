package com.mickey.mybatis.plus.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mickey.model.page.QueryPageResult;
import com.mickey.model.page.QueryResult;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author J·K
 * @description: PageUtil
 * @date 2021/5/28 2:30 下午
 */
@UtilityClass
public class PageUtil {

    public <T, R> QueryPageResult<R> QueryPageResult(IPage<T> page, Function<T, R> convert) {
        boolean hasNext = page.getCurrent() < page.getPages();
        List<R> collect = Optional.ofNullable(page.getRecords())
            .orElse(new ArrayList<>(0))
            .stream()
            .map(convert)
            .collect(Collectors.toList());
        int nexPage = hasNext ? (int) page.getCurrent() + 1 : -1;
        return new QueryPageResult<>(page.getTotal(),
            collect,
            hasNext,
            (int) page.getPages(),
            nexPage);
    }

    public <T, R> QueryResult<R> QueryResult(IPage<T> page, Function<T, R> convert) {
        List<R> collect = Optional.ofNullable(page.getRecords())
            .orElse(new ArrayList<>(0))
            .stream()
            .map(convert)
            .collect(Collectors.toList());
        return new QueryResult<>(collect, page.getTotal());
    }
}
