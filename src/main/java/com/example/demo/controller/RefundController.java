package com.example.demo.controller;

import com.example.demo.service.AddressService;
import com.example.demo.service.MobilePayService;
import com.example.demo.service.RefundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/init")
public class RefundController {

    @Autowired
    private RefundService refundService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private MobilePayService mobilePayService;

    @PostMapping(value = "/refund")
    public ResponseEntity<String> readExcel(@RequestBody MultipartFile file) {
        refundService.refund(file);
        return ResponseEntity.ok("SUCCESS");
    }

    @GetMapping(value = "/address")
    public ResponseEntity<String> address() {
        addressService.addressParticiple();
        return ResponseEntity.ok("SUCCESS");
    }

    @GetMapping(value = "/query/trade")
    public ResponseEntity<List<String>> queryTrade() {
        List<String> result = mobilePayService.getResult();
        return ResponseEntity.ok(result);
    }


}
