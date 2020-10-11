package com.example.demo.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.MallAcOrder;
import com.example.demo.entity.MallOrderInfo;
import com.example.demo.entity.MallPayInfo;
import com.example.demo.entity.MallPayType;
import com.example.demo.mapper.MallOrderInfoMapper;
import com.example.demo.mapper.MallPayInfoMapper;
import com.example.demo.mapper.MallUserMapper;
import com.example.demo.utils.CommonUtil;
import com.example.demo.utils.IDUtils;
import com.example.demo.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MobilePayService extends ServiceImpl<MallPayInfoMapper, MallPayInfo> {

    @Autowired
    private MallUserMapper mallUserMapper;

    @Autowired
    private MallOrderInfoMapper mallOrderInfoMapper;

    @Autowired
    private MallPayInfoMapper mallPayInfoMapper;

    public static String ORDER_QUERY = "https://api.mch.weixin.qq.com/pay/orderquery";
    public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";
//public static String gatewayUrl = "https://aipay.trade.query";

    public List<String>  getResult() {
        List<String> list = new ArrayList<>();
        try {
            List<MallAcOrder> acOrder = mallUserMapper.getAcOrderss();
            int i = 0;
            int a = 0;

            for (MallAcOrder order : acOrder) {
                boolean wx = false;
                boolean ali = false;
                ++i;
                String companyId = mallUserMapper.getCompanyIdByUserId(order.getUserId());
                List<MallPayType> configs = mallUserMapper.getPayTypeConfig(companyId);
                MallPayType c = null;
                List<MallPayType> cs = configs.stream().filter(p -> "0".equals(p.getType())).collect(Collectors.toList());
                if (ObjectUtils.isNotNullAndEmpty(cs)) {
                    c = cs.get(0);
                    String result = CommonUtil.httpsRequest(ORDER_QUERY, "POST", getSendParam(c, order.getId()));
                    Map<String, String> map = CommonUtil.doXMLParse(result);

                    if ("SUCCESS".equalsIgnoreCase(map.get("trade_state"))) {
                        wx = true;
                    }
                }
                cs = configs.stream().filter(p -> "1".equals(p.getType())).collect(Collectors.toList());
                if (ObjectUtils.isNotNullAndEmpty(cs)) {
                    if (ObjectUtils.isNotNullAndEmpty(cs)) {
                        c = cs.get(0);
                        //实例化客户端（参数：网关地址、商户appid、商户私钥、格式、编码、支付宝公钥、加密类型）
                        //1  实例化客户端
                        AlipayClient alipayClient = initAliPayClient(c);
                        AlipayTradeQueryRequest queryRequest = new AlipayTradeQueryRequest();
                        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
                        model.setOutTradeNo(order.getId());
                        queryRequest.setBizModel(model);

                        AlipayTradeQueryResponse response = alipayClient.execute(queryRequest);
                        if ("TRADE_SUCCESS".equals(response.getTradeStatus())) {
                            ali = true;
                        }
                    }
                }
                if (wx || ali) {
                    ++a;
                }
                if (wx && ali) {
                    list.add("订单号："+order.getId()+" 姓名："+order.getName()+" 手机号："+order.getPhone()+ " <付了两次>");
                    System.out.println(order.getId() +"姓名："+order.getName()+"，手机号"+order.getPhone()+ " <付了两次>");
                }
                if(wx && !ali) {
                    list.add("订单号："+order.getId()+" 姓名："+order.getName()+" 手机号："+order.getPhone()+ " <只有微信>");
                    System.out.println(order.getId() +"姓名："+order.getName()+"，手机号"+order.getPhone()+ " <只有微信>");
                }
                if (ali && !wx) {
                    list.add("订单号："+order.getId()+" 姓名："+order.getName()+" 手机号："+order.getPhone()+ " <只有支付宝>");
                    System.out.println(order.getId() +"姓名："+order.getName()+"，手机号"+order.getPhone()+ " <只有支付宝>");
                }
            }
            System.out.println("成功支付" + a + "条,未支付" + (i - a) + "条");
            list.add("成功支付" + a + "条,未支付" + (i - a) + "条");
        } catch (Exception e) {
            log.error("" + e);
        }
        return list;
    }

    public void getResult2(String orderId, String userId) {
        try {
            int i = 0;
            int a = 0;
            ++i;
            String companyId = mallUserMapper.getCompanyIdByUserId(userId);
            List<MallPayType> configs = mallUserMapper.getPayTypeConfig(companyId);
            MallPayType c = null;
            List<MallPayType> cs = configs.stream().filter(p -> "0".equals(p.getType())).collect(Collectors.toList());
            if (ObjectUtils.isNotNullAndEmpty(cs)) {
                c = cs.get(0);
                String result = CommonUtil.httpsRequest(ORDER_QUERY, "POST", getSendParam(c, orderId));
                Map<String, String> map = CommonUtil.doXMLParse(result);

                if ("SUCCESS".equalsIgnoreCase(map.get("trade_state"))) {
                    System.out.println(orderId + "微信");
                    ++a;
                }

            }
            cs = configs.stream().filter(p -> "1".equals(p.getType())).collect(Collectors.toList());
            if (ObjectUtils.isNotNullAndEmpty(cs)) {
                if (ObjectUtils.isNotNullAndEmpty(cs)) {
                    c = cs.get(0);
                    //1  实例化客户端
                    AlipayClient alipayClient = initAliPayClient(c);
                    AlipayTradeQueryRequest queryRequest = new AlipayTradeQueryRequest();
                    AlipayTradeQueryModel model = new AlipayTradeQueryModel();
                    model.setOutTradeNo(orderId);
                    queryRequest.setBizModel(model);

                    AlipayTradeQueryResponse response = alipayClient.execute(queryRequest);
                    if ("TRADE_SUCCESS".equals(response.getTradeStatus()) && "TRADE_FINISHED".equals(response.getTradeStatus())) {
                        System.out.println(orderId + "支付宝");
                        ++a;
                    }
                }
            }
            System.out.println("成功支付" + a + "条,未支付" + (i - a) + "条");
        } catch (Exception e) {
            log.error("" + e);
        }

    }

    private String getSendParam(MallPayType config, String orderId) {
        TreeMap<String, Object> parameters = new TreeMap<>();
        parameters.put("appid", config.getAppId());
        parameters.put("mch_id", config.getMchId());
        parameters.put("out_trade_no", orderId);//商户订单号
        parameters.put("nonce_str", CommonUtil.getRandomString(32));
        parameters.put("sign", CommonUtil.createSign(config.getKey(), "UTF-8", parameters));
        return CommonUtil.getRequestXml(parameters);
    }

    //1  实例化客户端
    private AlipayClient initAliPayClient(MallPayType payConfig) {
        return new DefaultAlipayClient(gatewayUrl,
                payConfig.getAppId(), payConfig.getAppSecret(), AlipayConstants.FORMAT_JSON,
                AlipayConstants.CHARSET_UTF8, payConfig.getMchId(), AlipayConstants.SIGN_TYPE_RSA2);
    }


    public void insertPayInfo() {
        List<MallOrderInfo> orders = mallOrderInfoMapper.getNoPayInfoOrder();
        int i = 0;
        for(MallOrderInfo p: orders) {
            MallPayInfo payInfo = new MallPayInfo();
            payInfo.setPayId(IDUtils.genId());
            payInfo.setOrderId(p.getOrderId());
            payInfo.setMallUserId(p.getMallUserId());
            payInfo.setCreateDate(p.getCreateDate());
            payInfo.setTitle("补充支付单");
            payInfo.setTotalAmt(p.getPaymentAmt());
            payInfo.setPayStatus("0");
            if (!"0".equals(p.getOrderStatus())) {
                payInfo.setPayType("1");
                payInfo.setPayStatus("1");
            }
            if (p.getTradeNo() != null) {
                payInfo.setPayType("3");
                payInfo.setPayStatus("1");
                payInfo.setTradeNo(p.getTradeNo());
            }
            if (p.getTransactionId() != null) {
                payInfo.setPayType("2");
                payInfo.setPayStatus("1");
                payInfo.setTransactionId(p.getTransactionId());
            }
            payInfo.setCurrency("0");
            payInfo.setStatus("0");
            payInfo.setIsDel("0");
            boolean save = this.save(payInfo);
            System.out.println(save);
            ++i;
            System.out.println(i);
        }

    }
}
