package com.example.demo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @Classname JDExcelModel
 * @Description TODO
 * @Date 2020-04-15 18:10
 * @Created by MR. Xb.Wu
 */
@Data
public class JDExcelModel extends BaseRowModel {

    @ExcelProperty(value = {"表头1","表头1","表头31"},index = 0)
    private String p1;

    @ExcelProperty(value = {"表头1","表头1","表头32"},index = 1)
    private String p2;

    @ExcelProperty(value = {"表头3","表头3","表头3"},index = 2)
    private String p3;

    @ExcelProperty(value = {"表头4","表头4","表头4"},index = 3)
    private String p4;

    @ExcelProperty(value = {"表头5","表头51","表头52"},index = 4)
    private String p5;

    @ExcelProperty(value = {"表头6","表头61","表头611"},index = 5)
    private String p6;

    @ExcelProperty(value = {"表头6","表头61","表头612"},index = 6)
    private String p7;

    @ExcelProperty(value = {"表头6","表头62","表头621"},index = 7)
    private String p8;

    @ExcelProperty(value = {"表头6","表头62","表头622"},index = 8)
    private String p9;
}
