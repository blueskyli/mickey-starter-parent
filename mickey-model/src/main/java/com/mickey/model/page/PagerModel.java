package com.mickey.model.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author J·K
 * @Description: 分页
 * @date 2020/8/10 12:18 下午
 */
@Data
@ApiModel(description = "分页请求参数")
public class PagerModel {
    @ApiModelProperty(value = "页码", example = "1",required = true)
    @Min(value = 1, message = "当前页不能小于1")
    @NotNull(message = "页码不能为空")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页条数", example = "20",required = true)
    @Min(value = 1, message = "页码大小不能小于1")
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize = 20;
}
