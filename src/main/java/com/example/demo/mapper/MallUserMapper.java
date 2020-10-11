package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.MallAcOrder;
import com.example.demo.entity.MallPayType;
import com.example.demo.entity.MallUser;
import com.example.demo.entity.MallUserAddress;

import java.util.List;

public interface MallUserMapper extends BaseMapper<MallUser> {

    List<MallUserAddress> getAddress();

    void updateAddress(MallUserAddress address);

    List<MallPayType> getPayTypeConfig(String companyId);

    List<MallAcOrder> getAcOrder();

    String getCompanyIdByUserId(String userId);

    List<MallAcOrder> getAcOrders();

    List<MallAcOrder> getAcOrderss();
}
