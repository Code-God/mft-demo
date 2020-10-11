package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by liuliang on 2018/9/25.
 */
@Data
public class AddressDto implements Serializable {
    private String id;
    private String mallUserId;
    private String name;
    private String phone;
    private String area;
    private String provice;
    private String fullAddress;
    private Integer isDefault;
    private String areaCode;
}
