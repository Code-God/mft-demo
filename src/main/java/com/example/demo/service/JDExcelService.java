package com.example.demo.service;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.example.demo.entity.JDExcelModel;
import com.example.demo.entity.JDPushExcelModel;
import org.springframework.stereotype.Service;
import xiong.exception.ExcelException;
import xiong.utils.ExcelUtil;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname JDExcelService
 * @Description TODO
 * @Date 2020-04-15 18:18
 * @Created by MR. Xb.Wu
 */
@Service
public class JDExcelService {


    public void getExcel(HttpServletResponse response) {
        List<JDPushExcelModel> list = new ArrayList<>();
        try {
            ExcelUtil.writeExcel(response, list , "测试表哥", "测试表哥", ExcelTypeEnum.XLS,  JDPushExcelModel.class);
        } catch (ExcelException e) {
            e.printStackTrace();
        }
    }

}
