package com.mickey.redis.lock;

import org.apache.logging.log4j.util.Strings;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author J·K
 * @Description: TODO
 * @date 2019-06-27 12:55
 */
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
@ConditionalOnClass({Redisson.class})
@ConditionalOnExpression("'${spring.redis.mode}'=='single' or '${spring.redis.mode}'=='cluster' or '${spring.redis.mode}'=='sentinel'")
public class RedissonAutoConfiguration {

    @Autowired
    private RedisProperties redisProperties;

    /**
     * 单机模式 redisson 客户端
     */
    @Bean
    @ConditionalOnProperty(name = "spring.redis.mode", havingValue = "single")
    public RedissonClient redissonSingle() {
        Config config = new Config();
        String node = redisProperties.getHost() + ":" + redisProperties.getPort();
        node = node.startsWith("redis://") ? node : "redis://" + node;
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(node)
                .setTimeout(3000)
                .setConnectionPoolSize(64)
                .setConnectionMinimumIdleSize(8);
        if (Strings.isNotBlank(redisProperties.getPassword())) {
            serverConfig.setPassword(redisProperties.getPassword());
        }
        return Redisson.create(config);
    }


    /**
     * 集群模式的 redisson 客户端
     *
     * @return
     */
    @Bean
    @ConditionalOnProperty(name = "spring.redis.mode", havingValue = "cluster")
    public RedissonClient redissonCluster() {
        System.out.println("cluster redisProperties:" + redisProperties.getCluster());

        Config config = new Config();
        List<String> nodes = redisProperties.getCluster().getNodes();
        List<String> newNodes = new ArrayList(nodes.size());
        nodes.forEach((index) -> newNodes.add(
                index.startsWith("redis://") ? index : "redis://" + index));

        ClusterServersConfig serverConfig = config.useClusterServers()
                .addNodeAddress(newNodes.toArray(new String[0]))
                .setScanInterval(1000)
                .setIdleConnectionTimeout(3000)
                .setConnectTimeout(3000)
                .setRetryAttempts(3)
                .setRetryInterval(1500)
                .setMasterConnectionPoolSize(64)
                .setSlaveConnectionPoolSize(64)
                .setTimeout(3000);
        if (Strings.isNotBlank(redisProperties.getPassword())) {
            serverConfig.setPassword(redisProperties.getPassword());
        }
        return Redisson.create(config);
    }

    /**
     * 哨兵模式 redisson 客户端
     * @return
     */

    @Bean
    @ConditionalOnProperty(name = "spring.redis.mode", havingValue = "sentinel")
    public RedissonClient redissonSentinel() {
        System.out.println("sentinel redisProperties:" + redisProperties.getSentinel());
        Config config = new Config();
        List<String> nodes = redisProperties.getSentinel().getNodes();
        List<String> newNodes = new ArrayList(nodes.size());
        nodes.forEach((index) -> newNodes.add(
                index.startsWith("redis://") ? index : "redis://" + index));

        SentinelServersConfig serverConfig = config.useSentinelServers()
                .addSentinelAddress(newNodes.toArray(new String[0]))
                .setMasterName(redisProperties.getSentinel().getMaster())
                .setReadMode(ReadMode.SLAVE)
                .setTimeout(3000)
                .setMasterConnectionPoolSize(64)
                .setSlaveConnectionPoolSize(64);

        if (Strings.isNotBlank(redisProperties.getPassword())) {
            serverConfig.setPassword(redisProperties.getPassword());
        }

        return Redisson.create(config);
    }

    @Bean
    @ConditionalOnMissingBean(name = "redissonLocker")
    public RedissonDistributedLocker redissonLocker(){
        return new RedissonDistributedLocker();
    }
}
