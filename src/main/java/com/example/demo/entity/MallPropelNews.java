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
@AllArgsConstructor
@NoArgsConstructor
public class MallPropelNews implements Serializable {
    private String id;
    private String title;
    private String subTitle;
    private BigDecimal amt;
    private String status;
    private Date createTime;
    private Date updateTime;
    private String mallUserId;
    private String sendUserId;
    private String relationId;
    private String relationType;
    private String isDel;
    private String type;
    private String newsType;
    private String isImg;
    private String imgUrl;

}