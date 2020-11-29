package com.example.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;
    private Integer age;
    private String email;

    //自动填充
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
}