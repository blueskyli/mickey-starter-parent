package com.mickey.core.utils.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密
 */
public class MD5Utils
{
    private final static String DEFALUT_CHARSET = "UTF-8";

    /**
     * 创建一个新的实例MD5Util.
     */
    private MD5Utils()
    {

    }

    /**
     * md5加密方法 默认UTF-8编码
     *
     * @param source
     * @return
     */
    public static String getMD5(String source)
    {

        return getMD5(source, DEFALUT_CHARSET);
    }

    /**
     * md5加密方法
     *
     * @param source
     * @param charSet
     * @return
     */
    public static String getMD5(String source, String charSet)
    {
        String result = "";
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            try
            {
                md.update(source.getBytes(charSet));
            }
            catch(UnsupportedEncodingException e)
            {
                md.update(source.getBytes());
            }
            byte b[] = md.digest();

            int i;
            StringBuffer buf = new StringBuffer("");
            for(int offset = 0; offset < b.length; offset++)
            {
                i = b[offset];
                if(i < 0)
                    i += 256;
                if(i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        }
        catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return result;
    }

}
