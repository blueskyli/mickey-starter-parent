package com.mickey.model.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 分页查询结果集
 * @author J·K
 * @date 2020/3/20 5:24 下午
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "分页结果集")
public class QueryPageResult<T> implements Serializable {

	private static final long serialVersionUID = 461900815434592315L;

    public QueryPageResult(long total, List<T> list, boolean hasNextPage, Integer pages, int nextPage) {
        this.total = total;
        this.list = list;
        this.hasNextPage = hasNextPage;
        this.pages = pages;
        this.nextPage = nextPage;
    }

    @ApiModelProperty(value = "数据列表")
	private List<T> list;

    @ApiModelProperty(value = "总条数")
	private Long total = 0L;

    @ApiModelProperty(value = "总页数")
    private Integer pages;

    @ApiModelProperty(value = "是否有下一页")
    private boolean hasNextPage;

    @ApiModelProperty(value = "下一页页码")
    private int nextPage;

}
