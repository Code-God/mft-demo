package com.example.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: wuxb
 * @Date: 2019-06-24 19:54
 * @Auto: I AM A CODE MAN -_-!
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MallPayType implements Serializable {

    private String id;

    private String companyId;

    private String appId;

    private String appSecret;

    private String mchId;

    private String key;

    private Date createDate;

    private String type;

    private String status;

    private String isDel;

}
