package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.demo.entity.MallOrderInfo;
import com.example.demo.mapper.MallOrderInfoMapper;
import com.example.demo.utils.ObjectUtils;
import com.example.demo.utils.RedisUtil;
import com.example.demo.utils.StringUtils;
import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.domain.ECLP.EclpOpenService.response.cancelOrder.CancelResult;
import com.jd.open.api.sdk.request.ECLP.EclpOrderCancelOrderRequest;
import com.jd.open.api.sdk.request.ECLP.EclpOrderQueryOrderRequest;
import com.jd.open.api.sdk.response.ECLP.EclpOrderCancelOrderResponse;
import com.jd.open.api.sdk.response.ECLP.EclpOrderQueryOrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Classname CancelOrderService
 * @Description TODO
 * @Date 2020-01-29 12:20
 * @Created by MR. Xb.Wu
 */
@Slf4j
@Service
public class CancelOrderService {

    public static String SERVER_URL = "https://api.jd.com/routerjson";
    public static String appKey = "6C72B07E36261F273FD88F7989846D80";
    public static String appSecret = "7c44545f106d4d42bced3144383fdffe";

    @Autowired
    private MallOrderInfoMapper mallOrderInfoMapper;

    public boolean cancelOrder(String eclpSoNos) {
        JSONArray parse = JSONArray.parseArray(eclpSoNos);

        String eclpSoNo = parse.get(0).toString();
        String accessToken = RedisUtil.get("jd:access_token");

        if (queryJdOrder(accessToken, eclpSoNo)) {
            JdClient client = new DefaultJdClient(SERVER_URL, accessToken, appKey, appSecret);
            EclpOrderCancelOrderRequest request = new EclpOrderCancelOrderRequest();
            request.setEclpSoNo(eclpSoNo);
            try {
                EclpOrderCancelOrderResponse response = client.execute(request);
                CancelResult cancelorderResult = response.getCancelorderResult();
                log.info("取消出库单 cancelorderResult={}", JSON.toJSONString(cancelorderResult));
                return 1 == cancelorderResult.getCode();
            } catch (JdException e) {
                return false;
            }
        }
        return false;
    }

    public boolean queryJdOrder(String accessToken, String eclpSoNo) {
        try {
//            String accessToken = RedisUtil.get("jd:access_token");
            JdClient client = new DefaultJdClient(SERVER_URL, accessToken, appKey, appSecret);
            EclpOrderQueryOrderRequest request = new EclpOrderQueryOrderRequest();
            request.setEclpSoNo(eclpSoNo);
            EclpOrderQueryOrderResponse response = client.execute(request);
            if (ObjectUtils.isNullOrEmpty(response.getQueryorderResult()) || ObjectUtils.isNullOrEmpty(response)) {
                return false;
            }
            log.info("查询是否可关闭订单，eclpSoNo={}，response={},status={}", eclpSoNo, JSON.toJSONString(response), response.getQueryorderResult().getCurrentStatus());
            if ("10009".equals(response.getQueryorderResult().getCurrentStatus()) || "10010".equals(response.getQueryorderResult().getCurrentStatus())) {
                return true;
            }
            if ("100130".equals(response.getQueryorderResult().getCurrentStatus()) || "100131".equals(response.getQueryorderResult().getCurrentStatus())) {
                return true;
            }
            if ("100132".equals(response.getQueryorderResult().getCurrentStatus()) || "10014".equals(response.getQueryorderResult().getCurrentStatus())) {
                return true;
            }
            if ("10015".equals(response.getQueryorderResult().getCurrentStatus()) || "10016".equals(response.getQueryorderResult().getCurrentStatus()) || "10028".equals(response.getQueryorderResult().getCurrentStatus())) {
                return true;
            }
            return false;
        } catch (JdException e) {
            return false;
        }
    }

    public void cancel() {
        List<MallOrderInfo> orderInfos = mallOrderInfoMapper.getOrderInfo();
        int i = orderInfos.size();
        for(MallOrderInfo orderInfo: orderInfos) {
            if (!StringUtils.isEmpty(orderInfo.getEclpSoNo())) {
                boolean b = cancelOrder(orderInfo.getEclpSoNo());
                if (!b) {
                    MallOrderInfo order = new MallOrderInfo();
                    order.setOrderId(orderInfo.getOrderId());
                    order.setCsMemo("正在拦截京东出库订单");
                    mallOrderInfoMapper.updateById(order);
                }
                if (b) {
                    MallOrderInfo order = new MallOrderInfo();
                    order.setOrderId(orderInfo.getOrderId());
                    order.setOrderStatus("3");
                    order.setCsMemo("京东拦截订单成功");
                    mallOrderInfoMapper.updateById(order);
                }
            }
            System.out.println(--i);
        }
    }

    public void updateStatus() {
        List<MallOrderInfo> orderInfos = mallOrderInfoMapper.getOrderInfoJd();
        String accessToken = RedisUtil.get("jd:access_token");
        int i = 0;
        for(MallOrderInfo orderInfo: orderInfos) {
            JSONArray parse = JSONArray.parseArray(orderInfo.getEclpSoNo());
            String eclpSoNo = parse.get(0).toString();
            if (queryJdOrder(accessToken, eclpSoNo)) {
                MallOrderInfo order = new MallOrderInfo();
                order.setOrderId(orderInfo.getOrderId());
                order.setOrderStatus("3");
//                order.setCsMemo("京东拦截订单成功");
                mallOrderInfoMapper.updateById(order);
                System.out.println(orderInfo.getOrderId()+"  "+ (++i));
//                System.out.println(++i);
            }
        }
    }

}
