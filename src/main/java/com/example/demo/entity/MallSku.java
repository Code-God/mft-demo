package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liuliang on 2018/9/28.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MallSku implements Serializable {
    private String id;
    private String skuCode;
    private String relationSku;
    private String itemId;
    private String title;
    private String belongsCode;
    private String retailPrice;
    private String currency;
    private BigDecimal stock;
    private String unit;
    private String spec;
    private String skuImg;
    private String itemProperties;
    private Date createDate;
    private Date updateDate;
    private String isShareProfit;
    private String isSubStock;
    private String isChange;
    private String review;
    private String status;
    private String isDel;
    private String label;
    private Integer sort;
    private String logisticsMode;
}
