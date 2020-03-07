package com.gdio.springbootvotesystem;

import com.gdio.springbootvotesystem.tools.MyTool;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@SpringBootTest
class SpringBootVoteSystemApplicationTests {

    @Test
    void contextLoads() {
        String userName="阿祥";
        String password="12,3456789";
        int length=16;
        String code= MyTool.getMD5(userName+password,length);
        System.out.println(code);
    }

}
