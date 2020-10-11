package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Classname OutGoodsDate
 * @Description TODO
 * @Date 2020-02-10 19:54
 * @Created by MR. Xb.Wu
 */
@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class CheckOutGoods implements Serializable {

    private String userId;

    private Date startTime;

    private Date entTime;

    private String skuCode;

    private String nextSkuCode;

    private Integer checkAmount;

    private Integer specNumber;

    private Integer type;

    private List<String> skuCodes;

    private Integer online;
}
