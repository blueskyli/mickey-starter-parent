package com.mickey.mybatis.plus.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mickey.model.page.QueryPageResult;
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
    public <T, R> QueryPageResult<R> convertPage(Page<T> page, Function<T, R> convert) {
        List<R> collect = Optional.ofNullable(page.getRecords())
            .orElse(new ArrayList<>(0))
            .stream()
            .map(convert)
            .collect(Collectors.toList());
        int nexPage = page.hasNext() ? (int) page.getCurrent() + 1 : -1;
        return new QueryPageResult<>(page.getTotal(),
            collect,
            page.hasNext(),
            (int) page.getPages(),
            nexPage);
    }
}
