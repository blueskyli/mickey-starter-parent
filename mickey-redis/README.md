# mickey-redis

#### 项目介绍
redis初始化
Redisson 锁

#### session共享
https://segmentfault.com/a/1190000019625173

1，增加依赖
``` 
<dependency>
   <groupId>org.springframework.session</groupId>
   <artifactId>spring-session-data-redis</artifactId>
</dependency>
``` 
2，增加配置
``` 
maxInactiveIntervalInSeconds: 设置 Session 失效时间
使用 Redis Session 之后，原 Spring Boot 中的 server.session.timeout 属性不再生效。

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=1800)//默认超时时间1800S
public class RedisSessionConfig
{
}
``` 
3，使用
``` 
@RequestMapping(value = "/getSession")
public Object getSession (HttpServletRequest request){
    Map<String, Object> map = new HashMap<>();
    map.put("sessionId", request.getSession().getId());
    map.put("message", request.getSession().getAttribute("message"));
    return map;
}


@RequestMapping(value = "/login")
public String login (HttpServletRequest request,String userName,String password){
    String msg="logon failure!";
    if (userName!=null && "admin".equals(userName) && "123".equals(password)){
        request.getSession().setAttribute("user",userName);
        msg="login successful!";
    }
    return msg;
}
 
 ```


