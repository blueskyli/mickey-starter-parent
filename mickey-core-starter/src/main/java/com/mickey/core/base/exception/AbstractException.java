package com.mickey.core.base.exception;

import com.mickey.core.base.utils.ErrorPrintUtils;
import lombok.Data;

@Data
public abstract class AbstractException extends RuntimeException
{

    private static final long serialVersionUID = -5992753399315247713L;

    private String errorCode;                               //错误码
    private String errorMsg;                                //错误信息
    private String stackTraceMsg;
    private String level;                                   //错误等级
    private String messageID;                               //消息ID

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

        errorCode = code;
        errorMsg = message;
        this.stackTraceMsg = stackTraceMsg;
    }

    public AbstractException(Throwable cause)
    {
        super(cause);
        AbstractException.ErrorDesc errorDesc = getErrorDesc(cause);
        if(errorDesc != null)
        {
            errorCode = errorDesc.errorCode;
            errorMsg = errorDesc.errorMsg;
        }
    }

    public AbstractException(String message)
    {
        super(message);
    }

    public abstract AbstractException.ErrorDesc getErrorDesc(Throwable var1);


    public static class ErrorDesc
    {

        public String errorCode;
        public String errorMsg;

        public ErrorDesc(String errorCode, String errorMsg)
        {
            this.errorCode = errorCode;
            this.errorMsg = errorMsg;
        }
    }
}
