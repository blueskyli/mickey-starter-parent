package com.mickey.core;

import com.mickey.core.enums.JsonResult;
import com.mickey.model.page.QueryResult;
import org.springframework.ui.ModelMap;

/**
 * @Description: Resp
 * @author J·K
 * @date 2020/3/23 4:43 下午
 */
public class Resp
{
    private static final int SUCCESS_CODE = 200;
    private static final String SUCCESS_MSG = "";
    private static final int ERROR_CODE = 500;
    private static final String ERROR_MSG = "error";

    public static JsonResult<Void> ok()
    {
        return ok(null);
    }

    public static <T> JsonResult<T> ok(T data)
    {
        return ok(SUCCESS_CODE,SUCCESS_MSG,data);
    }

    public static <T> JsonResult<T> ok(Integer code,T data)
    {
        return ok(code,SUCCESS_MSG,data);
    }

    public static <T> JsonResult<T> ok(Integer code, String msg, Object data)
    {
        return build(code,msg,data);
    }

    public static JsonResult<Void> error()
    {
        return error(ERROR_MSG);
    }

    public static <T> JsonResult<T> error(String msg)
    {
        return error(ERROR_CODE,msg,null);
    }

    public static <T> JsonResult<T> error(Integer code,String msg)
    {
        return error(code,msg,null);
    }

    public static <T> JsonResult<T> error(Integer code, String msg, Object data)
    {
        return build(code,msg,data);
    }

    private static JsonResult build(Integer code, String msg, Object data)
    {
        return JsonResult.builder().code(code).msg(msg).data(data).build();
    }

    /**
     * 生成分页对象
     * @param queryResult
     * @param <T>
     * @return
     */
    public static <T> ModelMap makeModelMap(QueryResult<T> queryResult)
    {
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("total", queryResult.getTotal());
        modelMap.addAttribute("rows", queryResult.getList());
        return modelMap;
    }

}
