package com.example.demo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.MallOrderInfo;
import com.example.demo.entity.MallSku;

import java.util.List;

/**
 * @Classname MallOrderInfoMapper
 * @Description TODO
 * @Date 2020-01-29 12:27
 * @Created by MR. Xb.Wu
 */
public interface MallOrderInfoMapper extends BaseMapper<MallOrderInfo> {

    List<MallOrderInfo> getOrderInfo();

    List<MallOrderInfo> getOrderInfoJd();

    List<MallOrderInfo> getNotDervier();

    List<MallOrderInfo> getNoPayInfoOrder();

    List<MallSku> getMallSku();


    List<MallOrderInfo> getOrderInfo1();
}
