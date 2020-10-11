package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by liuliang on 2018/9/25.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("mall_user_address")
public class MallUserAddress {

    private String id;

    @TableField("mall_user_id")
    private String mallUserId;

    @TableField(value = "area_code" )
    private String areaCode;
    private String name;
    private String phone;

    private String provice;

    private String  area;

    @TableField("full_address")
    private String fullAddress;

    @TableField("is_default")
    private Integer isDefault;

    @TableField("create_date")
    private Date createDate;

    @TableField("update_date")
    private Date update_date;
    private String remark;
    private Integer status;
    @TableField("is_del")
    private Integer isDel;

}
