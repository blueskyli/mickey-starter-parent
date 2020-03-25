package com.mickey.core.utils.common;

import com.google.common.collect.Lists;
import com.google.common.collect.Streams;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author J·K
 * @Description: 验签工具类
 * @date 2020/3/24 5:54 下午
 */
@Slf4j
public class SignUtils {
    /**
     * 从request得到排序完成的字符串 k,v不拼接
     * @param request
     * @param args 需要从request中移除不需要的可key 例如（signature，timestamp）
     * @return
     */
    public static String sign(HttpServletRequest request,String ... args){
        List<String> keys = new ArrayList<>(request.getParameterMap().keySet());
        if (args.length > 0) {
            keys.removeAll(Arrays.stream(args).collect(Collectors.toList()));
        }
        Collections.sort(keys);
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            String _value = request.getParameter(key);
            if (StringUtils.isNotBlank(_value) && !"null".equals(_value)) {
                //拼接字符串
                sb.append(key).append(request.getParameter(key));
            }
        }
//        DigestUtils.md5Hex(signSource)加密
        return sb.toString();
    }

    /**
     * 从request得到排序完成的字符串 k,v不拼接
     * @param map
     * @return
     */
    public static <V> String sign(Map<String, V> map){
        Map<String, V> treeMap = new TreeMap<>(String::compareTo);
        treeMap.putAll(map);
        StringBuilder sb = new StringBuilder("");
        Set<String> keySet = map.keySet();
        Iterator<String> iter = keySet.iterator();
        while(iter.hasNext())
        {
            String key = iter.next();
            String _value = (String) map.get(key);
            if (StringUtils.isNotBlank(_value) && !"null".equals(_value)) {
                sb.append(key).append(_value);
            }
        }
        return sb.toString();
    }

    /**
     * 返回等号连接 k=v&k=v
     * @param request
     * @param args 例如（signature，timestamp）
     * @return
     */
    public static String signpipe(HttpServletRequest request,String ... args){
        List<String> keys = new ArrayList<>(request.getParameterMap().keySet());
        if (args.length > 0) {
            keys.removeAll(Arrays.stream(args).collect(Collectors.toList()));
        }
        Collections.sort(keys);
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            String _value = request.getParameter(key);
            if (StringUtils.isNotBlank(_value) && !"null".equals(_value)) {
                if(sb.length()>0){ sb.append("&");}
                sb.append(key).append("=").append(request.getParameter(key));
            }
        }
        return sb.toString();
    }

    /**
     * 返回等号连接 k=v&k=v
     * @param map
     * @return
     */
    public static <V> String signpipe(Map<String, V> map){
        Map<String, V> treeMap = new TreeMap<>(String::compareTo);
        treeMap.putAll(map);
        StringBuilder sb = new StringBuilder("");
        Set<String> keySet = map.keySet();
        Iterator<String> iter = keySet.iterator();
        while(iter.hasNext())
        {
            String key = iter.next();
            String _value = (String) map.get(key);
            if (StringUtils.isNotBlank(_value) && !"null".equals(_value)) {
                if(sb.length()>0){ sb.append("&");}
                sb.append(key).append("=").append(_value);
            }
        }
        return sb.toString();
    }
}
