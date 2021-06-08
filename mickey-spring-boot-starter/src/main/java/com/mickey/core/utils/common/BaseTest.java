package com.mickey.core.utils.common;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author J·K
 * @description: 父类测试
 * @date 2021/5/21 10:20 上午
 */
@Slf4j
@Data
@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseTest {

    private Long startTime;

    @Before
    public void before() {
        this.setStartTime(System.currentTimeMillis());
        log.info("====================> 测试开始执行 <====================");
    }

    @After
    public void after() {
        log.info("====================> 测试执行完成，耗时：{}ms <====================",
                System.currentTimeMillis() - this.getStartTime());
    }
}
