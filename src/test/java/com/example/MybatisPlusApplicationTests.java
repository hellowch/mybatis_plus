package com.example;

import com.example.mapper.UserMapper;
import com.example.pojo.User;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void testSelect() {
        //查询全部用户
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        //插入数据
        User user = new User();
        user.setName("wch2");
        user.setAge(20);
        user.setEmail("11111@qq.com");
        int result = userMapper.insert(user);
        System.out.println(result);
        System.out.println(user);
    }

    @Test
    public void testUpdate(){
        //更新操作
        User user = new User();
        user.setId((long) 7);
        user.setName("wch");
        user.setAge(18);
        user.setEmail("222222@qq.com");
        int result = userMapper.updateById(user);
        System.out.println(result);
        System.out.println(user);
    }
}
