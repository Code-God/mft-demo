package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("mall_user")
public class MallUser {

    @TableId
    private String id;

    private String name;

    private String phone;
}
