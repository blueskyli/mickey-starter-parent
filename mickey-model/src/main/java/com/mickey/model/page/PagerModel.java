package com.mickey.model.page;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author J·K
 * @Description: 分页
 * @date 2020/8/10 12:18 下午
 */
@Data
public class PagerModel {
    @ApiModelProperty(value = "页码", example = "1",required = true)
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页条数", example = "20",required = true)
    private Integer pageSize = 20;
}
