package com.example.demo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExcelModel extends BaseRowModel {

    @ExcelProperty(index = 0)
    private String orderId;

    @ExcelProperty(index = 2)
    private String phone;

    @ExcelProperty(index = 3)
    private BigDecimal paymentAmt;
}
