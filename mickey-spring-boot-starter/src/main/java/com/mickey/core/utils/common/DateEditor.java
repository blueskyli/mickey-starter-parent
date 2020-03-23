package com.mickey.core.utils.common;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 日期转换器
 * @author J·K
 * @date 2020/3/23 12:25 上午
 */
public class DateEditor extends PropertyEditorSupport
{
    public DateEditor()
    {
    }

    public void setAsText(String text) throws IllegalArgumentException
    {
        Date date = null;
        try
        {
            if(!StringUtils.isEmpty(text))
            {
                int length = text.length();

                String fmt = "yyyy-MM-dd";
                switch(length)
                {
                    case 19:
                        fmt = "yyyy-MM-dd HH:mm:ss";
                        break;
                    case 16:
                        fmt = "yyyy-MM-dd HH:mm";
                        break;
                    case 13:
                        fmt = "yyyy-MM-dd HH";
                    default:
                        break;
                }
                SimpleDateFormat format = new SimpleDateFormat(fmt);
                date = format.parse(text);
            }
        }
        catch(ParseException var5)
        {
            var5.printStackTrace();
        }

        this.setValue(date);
    }
}
