package com.mickey.model.page;

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
public class QueryResult<T> implements Serializable {

	private static final long serialVersionUID = 461900815434592315L;
	
	private List<T> list;
	private long total;

}
