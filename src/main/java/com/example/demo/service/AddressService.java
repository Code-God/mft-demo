package com.example.demo.service;

import com.example.demo.entity.AddressDto;
import com.example.demo.entity.MallUserAddress;
import com.example.demo.json.JSONUtil;
import com.example.demo.mapper.MallUserMapper;
import com.example.demo.utils.HttpclientProxy;
import com.example.demo.utils.MallConstant;
import com.example.demo.utils.ObjectUtils;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class AddressService {

    @Autowired
    MallUserMapper userMapper;

    /**
     * 地址分词
     *
     * @return
     */
    public void addressParticiple() {
        List<MallUserAddress> addresses = userMapper.getAddress();

        int i = addresses.size();
        for (MallUserAddress p : addresses) {
            --i;
            try {
                String url = MallConstant.sfUrl;
                Map<String, String> map = Maps.newHashMap();

                //2
                map.put("address", p.getProvice());
                String result = HttpclientProxy.execPOSTMethodMParames(url, map);
                Map<String, Object> maps2 = JSONUtil.json2map(result);
                Map<String, Object> mapObj2 = JSONUtil.json2map(JSONUtil.obj2json(maps2.get("obj")));
                String site = (String) mapObj2.get("site");
                String province = (String) mapObj2.get("province");
                String city = (String) mapObj2.get("city");
                String area = (String) mapObj2.get("area");


//                if (ObjectUtils.isNullOrEmpty(province) || ObjectUtils.isNullOrEmpty(city)) {
//                    map.put("address", p.getFullAddress());
//                    String addressResult = HttpclientProxy.execPOSTMethodMParames(url, map);
//                    Map<String, Object> maps = JSONUtil.json2map(addressResult);
//                    Map<String, Object> mapObj = JSONUtil.json2map(JSONUtil.obj2json(maps.get("obj")));
//                    province = (String) mapObj.get("province");
//                    city = (String) mapObj.get("city");
//                    area = (String) mapObj.get("area");
//                }

//                if (ObjectUtils.isNullOrEmpty(province) || ObjectUtils.isNullOrEmpty(city) || ObjectUtils.isNullOrEmpty(area)) {
//                    log.info("----------------- 失败" + i);
//                    continue;
//                }

                if (ObjectUtils.isNullOrEmpty(province)) {
                    log.info("----------------- 失败" + i);
                    continue;
                }

//                String Area = province + " " + city + " " + area;

                MallUserAddress userAddress = new MallUserAddress();
                userAddress.setId(p.getId());
                userAddress.setProvice(province);
//                userAddress.setFullAddress(site);
                userMapper.updateAddress(userAddress);


                log.info("----------------- 成功" + i);
            } catch (IOException e) {

            }
        }
    }

    public void a() {
        List<MallUserAddress> addresses = userMapper.getAddress();
        for (MallUserAddress p : addresses) {

        }
    }

    public static void main(String[] args) {
//        addressParticiple("江苏省苏州市吴中区夏松林江苏省苏州市虎丘区运河路与横山路交叉口中铁十六局");
        List<String> listA = new ArrayList<String>();
        List<String> listB = new ArrayList<String>();
        listA.add("A");
        listA.add("B");
        listB.add("B");
        listB.add("A");
//        listA.retainAll(listB);
        listA.removeAll(listB);
        System.out.println(listA);
    }

    /**
     * 地址分词
     *
     * @param address
     * @return
     */
    public static AddressDto addressParticiple(String address) {
        AddressDto addressDto = new AddressDto();
        try {
            address = address.replaceAll("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……& amp;*（）——+|{}【】‘；：”“’。，、？|-]", "");
            String url = MallConstant.sfUrl;
            Map<String, String> map = Maps.newHashMap();
            map.put("address", address);
            String addressResult = HttpclientProxy.execPOSTMethodMParames(url, map);
            log.info("===================:address:{}", addressResult);
            Map<String, Object> maps = JSONUtil.json2map(addressResult);
            System.out.println(maps.get("obj"));
            Map<String, Object> mapObj = JSONUtil.json2map(JSONUtil.obj2json(maps.get("obj")));
            String province = (String) mapObj.get("province");
            String city = (String) mapObj.get("city");
            String area = (String) mapObj.get("area");
            String site = (String) mapObj.get("site");
            String personalName = (String) mapObj.get("personalName");
            String telephone = (String) mapObj.get("telephone");
//            String fullAddress = area + site;
            addressDto.setFullAddress(site); //街道
            addressDto.setName(personalName);
            addressDto.setPhone(telephone);
            addressDto.setArea(province + " " + city); //省市区
            if (ObjectUtils.isNotNullAndEmpty(area)) {
                addressDto.setArea(province + " " + city + " " + area); //省市区
            }
            addressDto.setProvice(province);
            addressDto.setAreaCode("0");
        } catch (IOException e) {

        }
        return addressDto;
    }
}
