//package com.example.demo.rocketmq;
//
//import com.aliyun.openservices.ons.api.MessageListener;
//import com.aliyun.openservices.ons.api.PropertyKeyConst;
//import com.aliyun.openservices.ons.api.bean.ConsumerBean;
//import com.aliyun.openservices.ons.api.bean.ProducerBean;
//import com.aliyun.openservices.ons.api.bean.Subscription;
////import com.meifute.core.service.*;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
///**
// * @Auther: wuxb
// * @Date: 2018-12-27 15:31
// * @Auto: I AM A CODE MAN -_-!
// * @Description:
// */
//@Configuration
//@Slf4j
//public class RocketMQConfig {
//
//    @Value("${ali-onsAddr}")
//    public String onsAddr;
//
//    @Value("${ali-onsAddr1}")
//    public String onsAddr1;
//
//    @Value("${ali-sendMsgTimeoutMillis}")
//    public String sendMsgTimeoutMillis;
//
//    @Value("${ali-suspendTimeMillis}")
//    public String suspendTimeMillis;
//
//    @Value("${ali-maxReconsumeTimes}")
//    public String maxReconsumeTimes;
//
//    /***********30************/
//    @Value("${ali-30m-producerId}")
//    public String ali30mProducerId;
//
//    @Value("${ali-30m-consumerId}")
//    public String ali30mConsumerId;
//
//    @Value("${ali-30m-topic}")
//    public String ali30mTopic;
//
//    @Value("${ali-30m-tag}")
//    public String ali30mTag;
//
//
//    @Value("${mq_access_key_id}")
//    public String accessKeyId;
//
//    @Value("${mq_access_key_secret}")
//    public String accessKeySecret;
//
//
//    /*************************30分钟未支付*******************************/
//    @Bean(initMethod = "start", destroyMethod = "shutdown")
//    public ProducerBean get30mProducer() {
//        ProducerBean producerBean = new ProducerBean();
//        Properties properties = new Properties();
//        properties.put(PropertyKeyConst.ProducerId, ali30mProducerId);
//        // AccessKey 阿里云身份验证，在阿里云服务器管理控制台创建
//        properties.put(PropertyKeyConst.AccessKey, accessKeyId);
//        // SecretKey 阿里云身份验证，在阿里云服务器管理控制台创建
//        properties.put(PropertyKeyConst.SecretKey, accessKeySecret);
//        properties.put(PropertyKeyConst.SendMsgTimeoutMillis, sendMsgTimeoutMillis);
//        properties.put(PropertyKeyConst.ONSAddr, onsAddr);
//        producerBean.setProperties(properties);
//        return producerBean;
//    }
//
//    @Bean(initMethod = "start", destroyMethod = "shutdown")
//    public ConsumerBean get30mConsumer() {
//        ConsumerBean consumerBean = new ConsumerBean();
//        Properties properties = new Properties();
//        properties.put(PropertyKeyConst.ConsumerId, ali30mConsumerId);
//        // AccessKey 阿里云身份验证，在阿里云服务器管理控制台创建
//        properties.put(PropertyKeyConst.AccessKey, accessKeyId);
//        // SecretKey 阿里云身份验证，在阿里云服务器管理控制台创建
//        properties.put(PropertyKeyConst.SecretKey, accessKeySecret);
//        properties.put(PropertyKeyConst.SuspendTimeMillis, sendMsgTimeoutMillis);
//        properties.put(PropertyKeyConst.MaxReconsumeTimes, maxReconsumeTimes);
//        properties.put(PropertyKeyConst.ONSAddr, onsAddr);
//        consumerBean.setProperties(properties);
//        Subscription subscription = new Subscription();
//        subscription.setTopic(ali30mTopic);
//        subscription.setExpression(ali30mTag);
//        Map<Subscription, MessageListener> map = new HashMap();
//        map.put(subscription, new MQ30mPayConsumerListener());
//        consumerBean.setSubscriptionTable(map);
//        return consumerBean;
//    }
////
////    /*************************京东推单*********************************/
////    @Bean(initMethod = "start", destroyMethod = "shutdown")
////    public ProducerBean getJdPushProducer() {
////        ProducerBean producerBean = new ProducerBean();
////        Properties properties = new Properties();
////        properties.put(PropertyKeyConst.ProducerId, aliJdProducerId);
////        // AccessKey 阿里云身份验证，在阿里云服务器管理控制台创建
////        properties.put(PropertyKeyConst.AccessKey, accessKeyId);
////        // SecretKey 阿里云身份验证，在阿里云服务器管理控制台创建
////        properties.put(PropertyKeyConst.SecretKey, accessKeySecret);
////        properties.put(PropertyKeyConst.SendMsgTimeoutMillis, sendMsgTimeoutMillis);
////        properties.put(PropertyKeyConst.ONSAddr, onsAddr);
////        producerBean.setProperties(properties);
////        return producerBean;
////    }
////
////    @Bean(initMethod = "start", destroyMethod = "shutdown")
////    public ConsumerBean getJdPushConsumer() {
////        ConsumerBean consumerBean = new ConsumerBean();
////        Properties properties = new Properties();
////        properties.put(PropertyKeyConst.ConsumerId, aliJdConsumerId);
////        // AccessKey 阿里云身份验证，在阿里云服务器管理控制台创建
////        properties.put(PropertyKeyConst.AccessKey, accessKeyId);
////        // SecretKey 阿里云身份验证，在阿里云服务器管理控制台创建
////        properties.put(PropertyKeyConst.SecretKey, accessKeySecret);
////        properties.put(PropertyKeyConst.SuspendTimeMillis, suspendTimeMillis);
////        properties.put(PropertyKeyConst.MaxReconsumeTimes, maxReconsumeTimes);
////        properties.put(PropertyKeyConst.ONSAddr, onsAddr);
////        consumerBean.setProperties(properties);
////        Subscription subscription = new Subscription();
////        subscription.setTopic(aliJdTopic);
////        subscription.setExpression(aliJdTag);
////        Map<Subscription, MessageListener> map = new HashMap();
////        map.put(subscription, new MQJdPushConsumerListener());
////        consumerBean.setSubscriptionTable(map);
////        return consumerBean;
////    }
////
////
////    /*************************7天自动收货*********************************/
////    @Bean(initMethod = "start", destroyMethod = "shutdown")
////    public ProducerBean get7daysConfirmProducer() {
////        ProducerBean producerBean = new ProducerBean();
////        Properties properties = new Properties();
////        properties.put(PropertyKeyConst.ProducerId, ali7daysProducerId);
////        // AccessKey 阿里云身份验证，在阿里云服务器管理控制台创建
////        properties.put(PropertyKeyConst.AccessKey, accessKeyId);
////        // SecretKey 阿里云身份验证，在阿里云服务器管理控制台创建
////        properties.put(PropertyKeyConst.SecretKey, accessKeySecret);
////        properties.put(PropertyKeyConst.SendMsgTimeoutMillis, sendMsgTimeoutMillis);
////        properties.put(PropertyKeyConst.ONSAddr, onsAddr);
////        producerBean.setProperties(properties);
////        return producerBean;
////    }
////
////    @Bean(initMethod = "start", destroyMethod = "shutdown")
////    public ConsumerBean get7daysConfirmConsumer() {
////        ConsumerBean consumerBean = new ConsumerBean();
////        Properties properties = new Properties();
////        properties.put(PropertyKeyConst.ConsumerId, ali7daysConsumerId);
////        // AccessKey 阿里云身份验证，在阿里云服务器管理控制台创建
////        properties.put(PropertyKeyConst.AccessKey, accessKeyId);
////        // SecretKey 阿里云身份验证，在阿里云服务器管理控制台创建
////        properties.put(PropertyKeyConst.SecretKey, accessKeySecret);
////        properties.put(PropertyKeyConst.SuspendTimeMillis, suspendTimeMillis);
////        properties.put(PropertyKeyConst.MaxReconsumeTimes, maxReconsumeTimes);
////        properties.put(PropertyKeyConst.ONSAddr, onsAddr);
////        consumerBean.setProperties(properties);
////        Subscription subscription = new Subscription();
////        subscription.setTopic(ali7daysTopic);
////        subscription.setExpression(ali7daysTag);
////        Map<Subscription, MessageListener> map = new HashMap();
////        map.put(subscription, new MQ7daysConfirmConsumerListener());
////        consumerBean.setSubscriptionTable(map);
////        return consumerBean;
////    }
////
////    /*************************3天后后处理审核单*********************************/
////    @Bean(initMethod = "start", destroyMethod = "shutdown")
////    public ProducerBean getVerifyOrderProducer() {
////        ProducerBean producerBean = new ProducerBean();
////        Properties properties = new Properties();
////        properties.put(PropertyKeyConst.ProducerId, aliVerifyProducerId);
////        // AccessKey 阿里云身份验证，在阿里云服务器管理控制台创建
////        properties.put(PropertyKeyConst.AccessKey, accessKeyId);
////        // SecretKey 阿里云身份验证，在阿里云服务器管理控制台创建
////        properties.put(PropertyKeyConst.SecretKey, accessKeySecret);
////        properties.put(PropertyKeyConst.SendMsgTimeoutMillis, sendMsgTimeoutMillis);
////        properties.put(PropertyKeyConst.ONSAddr, onsAddr);
////        producerBean.setProperties(properties);
////        return producerBean;
////    }
////
////    @Bean(initMethod = "start", destroyMethod = "shutdown")
////    public ConsumerBean getVerifyOrderConsumer() {
////        ConsumerBean consumerBean = new ConsumerBean();
////        Properties properties = new Properties();
////        properties.put(PropertyKeyConst.ConsumerId, aliVerifyConsumerId);
////        // AccessKey 阿里云身份验证，在阿里云服务器管理控制台创建
////        properties.put(PropertyKeyConst.AccessKey, accessKeyId);
////        // SecretKey 阿里云身份验证，在阿里云服务器管理控制台创建
////        properties.put(PropertyKeyConst.SecretKey, accessKeySecret);
////        properties.put(PropertyKeyConst.SuspendTimeMillis, suspendTimeMillis);
////        properties.put(PropertyKeyConst.MaxReconsumeTimes, maxReconsumeTimes);
////        properties.put(PropertyKeyConst.ONSAddr, onsAddr);
////        consumerBean.setProperties(properties);
////        Subscription subscription = new Subscription();
////        subscription.setTopic(aliVerifyTopic);
////        subscription.setExpression(aliVerifyTag);
////        Map<Subscription, MessageListener> map = new HashMap();
////        map.put(subscription, new MQVerifyOrderConsumerListener());
////        consumerBean.setSubscriptionTable(map);
////        return consumerBean;
////    }
////
////    /*************************团队关系链*********************************/
////    @Bean(initMethod = "start", destroyMethod = "shutdown")
////    public ProducerBean getTeamProducer() {
////        log.info("===========团队生产者=============="+agentTeamProducerId);
////        ProducerBean producerBean = new ProducerBean();
////        Properties properties = new Properties();
////        properties.put(PropertyKeyConst.GROUP_ID, agentTeamProducerId);
////        // AccessKey 阿里云身份验证，在阿里云服务器管理控制台创建
////        properties.put(PropertyKeyConst.AccessKey, accessKeyId);
////        // SecretKey 阿里云身份验证，在阿里云服务器管理控制台创建
////        properties.put(PropertyKeyConst.SecretKey, accessKeySecret);
////        properties.put(PropertyKeyConst.SendMsgTimeoutMillis, sendMsgTimeoutMillis);
////        properties.put(PropertyKeyConst.NAMESRV_ADDR, onsAddr1);
////        producerBean.setProperties(properties);
////        log.info("===========团队生产者 bean =============="+producerBean);
////        return producerBean;
////
////    }
////
////    @Bean(initMethod = "start", destroyMethod = "shutdown")
////    public ConsumerBean getTeamConsumer() {
////        ConsumerBean consumerBean = new ConsumerBean();
////        log.info("===========团队消费者=============="+agentTeamConsumerId);
////        Properties properties = new Properties();
////        // 您在控制台创建的 Group ID
////        properties.put(PropertyKeyConst.GROUP_ID, agentTeamConsumerId);
////        // 鉴权用 AccessKey，在阿里云服务器管理控制台创建
////        properties.put(PropertyKeyConst.AccessKey, accessKeyId);
////        // 鉴权用 SecretKey，在阿里云服务器管理控制台创建
////        properties.put(PropertyKeyConst.SecretKey, accessKeySecret);
////        // 设置 TCP 接入域名，进入控制台的实例管理页面，在页面上方选择实例后，在实例信息中的“获取接入点信息”区域查看
////        properties.put(PropertyKeyConst.NAMESRV_ADDR,onsAddr1);
////
////        consumerBean.setProperties(properties);
////        Subscription subscription = new Subscription();
////        subscription.setTopic(agentTeamTopic);
////        subscription.setExpression(agentTeamTag);
////        Map<Subscription, MessageListener> map = new HashMap();
////        map.put(subscription, new MQTeamConsumerListener());
////        consumerBean.setSubscriptionTable(map);
////        log.info("===========团队消费者bean======="+consumerBean);
////        return consumerBean;
////
////    }
//
//
//    /*************代理树******************/
////    @Bean(initMethod = "start", destroyMethod = "shutdown")
////    public ProducerBean getAgentTreeProducer() {
////        log.info("===========代理树生产者=============="+agentTreeProducerId);
////        ProducerBean producerBean = new ProducerBean();
////        Properties properties = new Properties();
////        properties.put(PropertyKeyConst.GROUP_ID, agentTreeProducerId);
////        // AccessKey 阿里云身份验证，在阿里云服务器管理控制台创建
////        properties.put(PropertyKeyConst.AccessKey, accessKeyId);
////        // SecretKey 阿里云身份验证，在阿里云服务器管理控制台创建
////        properties.put(PropertyKeyConst.SecretKey, accessKeySecret);
////        properties.put(PropertyKeyConst.SendMsgTimeoutMillis, sendMsgTimeoutMillis);
////        properties.put(PropertyKeyConst.NAMESRV_ADDR, onsAddr1);
////        properties.put(PropertyKeyConst.MessageModel, PropertyValueConst.BROADCASTING);
////        producerBean.setProperties(properties);
////        log.info("===========代理树生产者 bean =============="+producerBean);
////        return producerBean;
////
////    }
////
////    @Bean(initMethod = "start", destroyMethod = "shutdown")
////    public ConsumerBean getAgentTreeConsumer() {
////
////
////
////        ConsumerBean consumerBean = new ConsumerBean();
////        log.info("===========代理树消费者=============="+agentTreeConsumerId);
////        Properties properties = new Properties();
////        // 您在控制台创建的 Group ID
////        properties.put(PropertyKeyConst.GROUP_ID, agentTreeConsumerId);
////        // 鉴权用 AccessKey，在阿里云服务器管理控制台创建
////        properties.put(PropertyKeyConst.AccessKey, Const.ACCESSKEYID);
////        // 鉴权用 SecretKey，在阿里云服务器管理控制台创建
////        properties.put(PropertyKeyConst.SecretKey, Const.ACCESSKEYSECRET);
////        // 设置 TCP 接入域名，进入控制台的实例管理页面，在页面上方选择实例后，在实例信息中的“获取接入点信息”区域查看
////        properties.put(PropertyKeyConst.NAMESRV_ADDR,onsAddr1);
////
////        consumerBean.setProperties(properties);
////        Subscription subscription = new Subscription();
////        subscription.setTopic(agentTreeTopic);
////        subscription.setExpression(agentTreeTag);
////
////        Map<Subscription, MessageListener> map = new HashMap();
////        map.put(subscription, new MQAgentTreeListener());
////        consumerBean.setSubscriptionTable(map);
////
////        log.info("===========代理树消费者bean======="+consumerBean);
////        return consumerBean;
//////        Consumer consumer = null;
//////
//////        //消费者id
//////        DefaultMQPushConsumer mqPushConsumer = new DefaultMQPushConsumer(agentTreeConsumerId);
//////
//////        mqPushConsumer.setNamesrvAddr(onsAddr1);
//////
//////        mqPushConsumer.setInstanceName(agentTreeTag);
//////
//////        //消息设置为广播模式
//////        mqPushConsumer.setMessageModel(MessageModel.BROADCASTING);
//////
//////        mqPushConsumer.setConsumerGroup(agentTreeConsumerId);
//////
//////        return mqPushConsumer;
////    }
//
//
//}
