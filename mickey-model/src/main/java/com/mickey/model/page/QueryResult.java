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
public class QueryResult<T> implements Serializable {

	private static final long serialVersionUID = 461900815434592315L;

    @ApiModelProperty(value = "数据列表")
	private List<T> list;

    @ApiModelProperty(value = "总条数")
	private Long total = 0L;

}
