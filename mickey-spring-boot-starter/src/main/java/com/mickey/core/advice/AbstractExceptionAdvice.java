package com.mickey.core.advice;

import com.mickey.core.Resp;
import com.mickey.core.enums.ErrorCodeEnum;
import com.mickey.core.enums.JsonResult;
import com.mickey.core.exception.NoveControllerException;
import com.mickey.core.exception.NoveServiceException;
import com.mickey.core.exception.NoveSystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jack
 * @Description: 全局异常类
 * @date 2018/1/30 19:16
 */
@Slf4j
public abstract class AbstractExceptionAdvice {

    @ExceptionHandler(value = Exception.class)
    public JsonResult<Void> defaultErrorHandler(HttpServletRequest request, Exception e) {
        Integer code = 500;
        String msg = "系统错误，请联系管理员";
        log.error("请求地址:{},系统异常:{}:", request.getRequestURL(), e);
        if (e instanceof NoveControllerException
            || e instanceof NoveServiceException
            || e instanceof NoveSystemException) {
            String message = e.getMessage();
            String[] messages = message.split("\\|");
            code = Integer.parseInt(messages[0]);
            msg = messages[1];
        }
        return Resp.error()
            .setCode(code)
            .setMsg(msg);
    }

    @ExceptionHandler(BindException.class)
    public JsonResult<Void> defalutValidErrorHandler(HttpServletRequest request, Exception exception) {
        log.error("请求地址:{},系统异常:{}:", request.getRequestURL(), exception);
        BindException bindException = (BindException) exception;
        List<String> resultList = new ArrayList<>();
        List<ObjectError> allErrors = bindException.getAllErrors();
        for (ObjectError objectError : allErrors) {
            resultList.add(objectError.getDefaultMessage());
        }
        return Resp.error()
            .setCode(ErrorCodeEnum.PARAM_IS_ERROR.getCode())
            .setMsg(resultList.size() > 0 ? resultList.get(0) : ErrorCodeEnum.PARAM_IS_ERROR.getDesc());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResult<Void> methodArgumentNotValidErrorHandler(HttpServletRequest request, Exception exception) {
        log.error("请求地址:{},系统异常:{}:", request.getRequestURL(), exception);
        MethodArgumentNotValidException argumentNotValidException = (MethodArgumentNotValidException) exception;
        List<String> resultList = new ArrayList<>();
        BindingResult bindingResult = argumentNotValidException.getBindingResult();
        if (bindingResult != null) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError objectError : allErrors) {
                resultList.add(objectError.getDefaultMessage());
            }
        }
        return Resp.error()
            .setCode(ErrorCodeEnum.PARAM_IS_ERROR.getCode())
            .setMsg(resultList.size() > 0 ? resultList.get(0) : ErrorCodeEnum.PARAM_IS_ERROR.getDesc());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public JsonResult<Void> httpMessageNotReadableExceptionHandler(HttpServletRequest request, Exception exception) {
        log.error("请求体不能为空，请求地址:{},系统异常:{}:", request.getRequestURL(), exception);
        HttpMessageNotReadableException messageNotReadableException = (HttpMessageNotReadableException) exception;
        return Resp.error()
            .setCode(400)
            .setMsg("请求体不能为空");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JsonResult<Void> defalutMethodNotSupportedErrorHandler(HttpServletRequest request, Exception exception) {
        log.error("请求地址:{},系统异常:{}:", request.getRequestURL(), exception);
        HttpRequestMethodNotSupportedException methodNotSupportedException = (HttpRequestMethodNotSupportedException) exception;
        return Resp.error()
            .setCode(405)
            .setMsg(methodNotSupportedException.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public JsonResult<Void> defalutNumberFormatErrorHandler(HttpServletRequest request, Exception exception) {
        log.error("参数转换失败，请求地址:{},系统异常:{}:", request.getRequestURL(), exception);
        MethodArgumentTypeMismatchException methodArgumentTypeMismatchException = (MethodArgumentTypeMismatchException) exception;
        log.error("参数转换失败，方法：" + methodArgumentTypeMismatchException.getParameter().getMethod().getName() + ",参数：" +
            methodArgumentTypeMismatchException.getName() + "，信息：" + methodArgumentTypeMismatchException.getLocalizedMessage());
        return Resp.error()
            .setCode(ErrorCodeEnum.PARAM_FORMAT_ERROR.getCode())
            .setMsg(ErrorCodeEnum.PARAM_FORMAT_ERROR.getDesc());
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public JsonResult<Void> dataIntegrityViolationErrorHandler(HttpServletRequest request, Exception e) {
        log.error("数据库处理异常，请求地址:{},系统异常:{}:", request.getRequestURL(), e);
        return Resp.error()
            .setCode(ErrorCodeEnum.INTERNAL_ERROR.getCode())
            .setMsg("数据库处理异常");
    }
}
