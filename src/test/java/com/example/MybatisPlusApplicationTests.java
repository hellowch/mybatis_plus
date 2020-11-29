package com.example;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mapper.UserMapper;
import com.example.pojo.User;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
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
        user.setId((long) 8);
        user.setName("wch2");
        user.setAge(18);
        user.setEmail("222222@qq.com");
        int result = userMapper.updateById(user);
        System.out.println(result);
        System.out.println(user);
    }


    //测试乐观锁成功
    @Test
    public void testOptimisticLocker(){
         //1.查询用户信息
        User user = userMapper.selectById(6);
        //修改用户信息
        user.setName("wch1");
        user.setAge(18);
        user.setEmail("666666@qq.com");
        //执行更新
        userMapper.updateById(user);
    }


    //测试乐观锁失败,多线程下
    @Test
    public void testOptimisticLocker2(){
        //线程1
        User user = userMapper.selectById(6);
        user.setName("wch1111");
        user.setAge(18);
        user.setEmail("666666@qq.com");
        //模拟另一个线程执行插队操作
        User user2 = userMapper.selectById(6);
        user2.setName("wch2222");
        user2.setAge(18);
        user2.setEmail("666666@qq.com");
        userMapper.updateById(user2);
        //执行更新
        userMapper.updateById(user);  //如果没有乐观锁，这里会覆盖插队线程的值
    }


    //测试批量查询
    @Test
    public void testSelectById(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1,2,3));
        users.forEach(System.out::println);
    }

    //条件查询map
    @Test
    public void testSelectByBatchIds(){
        HashMap<String,Object> map = new HashMap<>();

        map.put("name","wch");
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    //测试分页查询
    @Test
    public void testPage(){
        //参数一：当前页
        //参数二：页面大小
        Page<User> page = new Page<>(1,5);
        userMapper.selectPage(page,null);

        page.getRecords().forEach(System.out::println);


    }
}
