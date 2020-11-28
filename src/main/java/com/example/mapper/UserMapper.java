package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.User;
import org.apache.ibatis.annotations.Mapper;

//集成BaseMapper的库，简化开发
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
