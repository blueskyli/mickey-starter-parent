package com.mickey.core.exception;


import com.mickey.core.ErrorPrintUtils;
import lombok.Data;

@Data
public abstract class AbstractException extends RuntimeException
{
    private String errorCode;
    private String errorMsg;
    private String stackTraceMsg;

    private static final long serialVersionUID = -5992753399315247713L;

    public AbstractException(String code, String message, String... level)
    {
        super(new StringBuilder().append(code).append("|").append(message).toString());
        handleExceptionMessage(code, message, new StringBuilder().append(code).append("|").append(message).toString());
    }

    public AbstractException(String code, String message, Throwable th)
    {
        super(new StringBuilder().append(code).append("|").append(message).toString(), th);
        handleExceptionMessage(code, message, ErrorPrintUtils.printStackTrace(th));
    }

    public final void handleExceptionMessage(String code, String message, String stackTraceMsg)
    {
        this.errorCode = code;
        this.errorMsg = message;
        this.stackTraceMsg = stackTraceMsg;
    }
}
