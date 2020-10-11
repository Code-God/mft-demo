package com.example.demo.controller;

import com.example.demo.entity.JDExcelModel;
import com.example.demo.service.JDExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Classname ExcelController
 * @Description TODO
 * @Date 2020-04-15 18:25
 * @Created by MR. Xb.Wu
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class ExcelController {

    @Autowired
    private JDExcelService jdExcelService;

    @GetMapping(value = "/excel")
    public ResponseEntity<String> address(HttpServletResponse response) {
        jdExcelService.getExcel(response);
        return ResponseEntity.ok("SUCCESS");
    }
}
