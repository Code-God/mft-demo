package com.example.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class MallAcOrder implements Serializable {

    private String id;

    private String userId;

    private BigDecimal totalAmt;

    private BigDecimal itemAmt;

    private BigDecimal postage;

    private String orderType;

    private String orderStatus;

    private String acId;

    private String recipientName;

    private String recipientPhone;

    private String recipientArea;

    private String recipientAddress;

    private String deliveryMode;

    private String logisticsCompany;

    private String singleNum;

    private String expressCode;

    private Date createTime;

    private Date payTime;

    private Date deliveryTime;

    private Date updateTime;

    private String payType;

    private String tradeNo;

    private String orderOrigin;

    private String remark;

    private Date payEndDate;

    private String isCanCancel;

    private String isDel;

    private Integer way;

    private String phone;

    private String name;
}
