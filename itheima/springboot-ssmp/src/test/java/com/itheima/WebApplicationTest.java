package com.itheima;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.transaction.annotation.Transactional;

// Web环境模拟测试
// 测试类启动Web环境
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// 开启虚拟调用
@AutoConfigureMockMvc
// 事务自动回滚
@Rollback
public class WebApplicationTest {

    @Autowired
    private MockMvc mockMvc;
    @Test
    void testRandomPort() {
    }

    @Test
    void testResponseStatus(MockMvc mockMvc) throws Exception {
        // 创建一个虚拟请求
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/server_info");
        // 执行虚拟请求
        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);

        // 匹配请求结果的状态
        StatusResultMatchers statusResultMatchers = MockMvcResultMatchers.status();
        // 定义预期状态
        ResultMatcher resultMatcher = statusResultMatchers.isOk();
        // 使用本次请求结果 与 预期结果进行比对
        resultActions.andExpect(resultMatcher);
    }

    @Test
    void testResponseContent(MockMvc mockMvc) throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/get_info");
        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);
        ContentResultMatchers contentResultMatchers = MockMvcResultMatchers.content();
        ResultMatcher resultMatcher = contentResultMatchers.contentType("1111");
        resultActions.andExpect(resultMatcher);
    }
}
