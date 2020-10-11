package com.example.demo.utils;

import java.math.BigDecimal;

/**
 * @Auther: wxb
 * @Date: 2018/9/14 09:40
 * @Auto: I AM A CODE MAN -_-!
 * @Description:
 */
public class PriceUtil {

    /**
     * 获取代理对应的商品价格-产品商城
     * @param retailPrice
     * @param agentLevel
     * @return
     */
    public static BigDecimal getPrice(String retailPrice, int agentLevel) {
        String price = retailPrice.split(",")[agentLevel];
        return new BigDecimal(price);
    }
}
