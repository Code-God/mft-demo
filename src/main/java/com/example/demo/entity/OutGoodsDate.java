package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Classname OutGoodsDate
 * @Description TODO
 * @Date 2020-02-10 19:54
 * @Created by MR. Xb.Wu
 */
@Data
public class OutGoodsDate implements Serializable {

    private String userId;

    private Date startTime;

    private Date entTime;
}
