package com.mickey.core.enums;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 公共相应参数实体
 * @param <T>
 */
@Builder
@Data
@Accessors(chain=true)
@ApiModel(value = "公共相应参数实体")
public class JsonResult<T> implements Serializable
{
    private static final long serialVersionUID = 1772055640075900959L;

    @ApiModelProperty(value = "响应状态码:无特殊说明的话，200为正常，其余均为异常",example = "200")
    private int code;
    @ApiModelProperty("响应说明")
    private String msg;
    @ApiModelProperty("结果数据")
    private T data;

    public JsonResult(){
        super();
    }

    public JsonResult(T data)
    {
        super();
        this.code = ErrorCodeEnum.OK.getCode();
        this.msg = "success";
        this.data = data;
    }

    public JsonResult(int code, String msg)
    {
        super();
        this.code = code;
        this.msg = msg;
    }

    public JsonResult(int code, String msg, T data)
    {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
