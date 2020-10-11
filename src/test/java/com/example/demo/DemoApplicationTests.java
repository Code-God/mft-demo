package com.example.demo;

import com.alipay.api.AlipayApiException;
import com.example.demo.entity.CheckOutGoods;
import com.example.demo.entity.LySerialNumberOff;
import com.example.demo.entity.MallUser;
import com.example.demo.entity.OutGoodsDate;
import com.example.demo.ratelimit.RateLimitTestService;
import com.example.demo.redisson.redislock.RedisLockService;
import com.example.demo.service.*;
import com.example.demo.utils.JsonUtils;
import com.example.demo.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.jdom.JDOMException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    RefundService refundService;
    @Autowired
    AddressService addressService;
    @Autowired
    MobilePayService mobilePayService;

//    @Test
//    public void sendMsg() {
//        refundService.sendMsg();
//    }
//
//    @Test
//    public void address() {
//        addressService.addressParticiple();
//    }

//    @Test
//    public void pay() {
//        mobilePayService.getResult2("1019698405945397248","139363840943988736");
//    }
//
//    @Test
//    public void pay1() {
//        mobilePayService.getResult();
//    }

    @Test
    public void test() {
        //access_to_refresh
        //auth
        //auth_to_access
        //client_id_to_access
        //mft_access
        //mft_user
        //refresh
        //refresh_auth
        //refresh_to_access
        //uname_to_access
        //systemToken
        for (int i = 0; i <= 7; i++) {
            if (i == 0) {
                Set<String> redisKeys = RedisUtil.getRedisKeys("access_to_refresh*");
                System.out.println(redisKeys.size());
                RedisUtil.delKeys(redisKeys);
            }
            if (i == 1) {
                Set<String> redisKeys = RedisUtil.getRedisKeys("auth*");
                System.out.println(redisKeys.size());
                RedisUtil.delKeys(redisKeys);
            }
            if (i == 2) {
                Set<String> redisKeys = RedisUtil.getRedisKeys("client_id_to_access*");
                System.out.println(redisKeys.size());
                RedisUtil.delKeys(redisKeys);
            }
            if (i == 3) {
                Set<String> redisKeys = RedisUtil.getRedisKeys("mft_access*");
                System.out.println(redisKeys.size());
                RedisUtil.delKeys(redisKeys);
            }
            if (i == 4) {
                Set<String> redisKeys = RedisUtil.getRedisKeys("mft_user*");
                System.out.println(redisKeys.size());
                RedisUtil.delKeys(redisKeys);
            }
            if (i == 5) {
                Set<String> redisKeys = RedisUtil.getRedisKeys("refresh*");
                System.out.println(redisKeys.size());
                RedisUtil.delKeys(redisKeys);
            }
            if (i == 6) {
                Set<String> redisKeys = RedisUtil.getRedisKeys("uname_to_access*");
                System.out.println(redisKeys.size());
                RedisUtil.delKeys(redisKeys);
            }
            if (i == 7) {
                Set<String> redisKeys = RedisUtil.getRedisKeys("systemToken*");
                System.out.println(redisKeys.size());
                RedisUtil.delKeys(redisKeys);
            }
        }
//        Set<String> redisKeys = RedisUtil.getRedisKeys("systemToken*");
//        System.out.println(redisKeys.size());
//        RedisUtil.delKeys(redisKeys);

//        for(int i)
    }

    @Test
    public void test4() {
//        RedisUtil.set("closeFlag","1");  //关闭下单

        RedisUtil.set("limitC038", "0");  //限制口喷

//        RedisUtil.set("notCanHunheOrder150","1"); //限制150ml混合下单

//        RedisUtil.set("notCanTakOfC036","1");  //限制米浮提货

//        RedisUtil.set("notCanHunheOrder20","1"); //限制消毒液混合下单

//        RedisUtil.set("isOnly20", "1");  //开启新亦源，0关闭 不用了

//        RedisUtil.set("canHaveGoods20", "20"); //限制消毒液购买数量单位瓶

//        RedisUtil.set("isCanCheckC036Z", "1"); //限制批量推单10组限制

//        RedisUtil.set("isCanBuy20", "1"); //不在指定时间不能发货

//        RedisUtil.set("isCanCheckC036Z", "0"); //取消限制批量推单10组限制

//        RedisUtil.set("check_freight_button", "1"); //限制修改地址

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            OutGoodsDate outGoodsDate = new OutGoodsDate();
//            outGoodsDate.setUserId("0123456789");
//            Date date1 = sdf.parse("2020-02-11 11:35:00"); //2020-02-11 11:35:00
//            outGoodsDate.setStartTime(date1);
//            Date date2 = sdf.parse("2020-02-12 18:00:00");
//            outGoodsDate.setEntTime(date2);
//
//            RedisUtil.set("outGoodsDate", JsonUtils.objectToJson(outGoodsDate));
//            String s = RedisUtil.get("outGoodsDate");
//            System.out.println(JsonUtils.jsonToPojo(s, OutGoodsDate.class));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void setCheckRedis1() {

        //不能混合下单
        List<String> list = new ArrayList<>();
        list.add("30290020");
        list.add("30030050");
        list.add("30030060");
        list.add("30290030");
        list.add("40000061");
        list.add("40000062");
        list.add("30030040");
        list.add("C036-Z");
        list.add("C036-H");
        RedisUtil.set("check_blend_order_item", JsonUtils.objectToJson(list));
    }

    //总代提货直发限制
    @Test
    public void setCheckRedis() {

        //不能混合下单
//        List<String> list = new ArrayList<>();
//        list.add("30290020");
//        list.add("30030050");
//        RedisUtil.set("check_blend_order_item", JsonUtils.objectToJson(list));

        //0开启，1关闭
        //限购
        List<CheckOutGoods> checkOutGoods = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            //消毒液
            CheckOutGoods checkOutGood1 = new CheckOutGoods();
            checkOutGood1.setUserId("0123456789");
            checkOutGood1.setStartTime(sdf.parse("2020-02-13 04:00:00"));
            checkOutGood1.setEntTime(sdf.parse("2021-04-25 18:00:00"));
            checkOutGood1.setSkuCode("30290020");
            checkOutGood1.setNextSkuCode("30290010");
            checkOutGood1.setCheckAmount(15); //15组
            checkOutGood1.setSpecNumber(2);
            checkOutGood1.setType(0); //每人每天
            checkOutGood1.setOnline(1); //关闭
            checkOutGoods.add(checkOutGood1);

            //白米浮
            CheckOutGoods checkOutGood2 = new CheckOutGoods();
            checkOutGood2.setUserId("0123456789");
            checkOutGood2.setStartTime(sdf.parse("2020-02-13 04:00:00"));
            checkOutGood2.setEntTime(sdf.parse("2021-04-25 18:00:00"));
            checkOutGood2.setSkuCode("C036-Z");
            checkOutGood2.setNextSkuCode("C036-H");
            checkOutGood2.setCheckAmount(15); //15组
            checkOutGood2.setSpecNumber(3);
            checkOutGood2.setType(0); //每人每天
            checkOutGood2.setOnline(1); //开启
            checkOutGoods.add(checkOutGood2);

            //50+150ml
            CheckOutGoods checkOutGood3 = new CheckOutGoods();
            checkOutGood3.setUserId("0123456789");
            checkOutGood3.setStartTime(sdf.parse("2020-02-13 04:00:00"));
            checkOutGood3.setEntTime(sdf.parse("2021-04-25 18:00:00"));
            checkOutGood3.setSkuCode("30030050");
            checkOutGood3.setNextSkuCode("30030050");
            checkOutGood3.setCheckAmount(15); //15组
            checkOutGood3.setSpecNumber(1);
            checkOutGood3.setType(0); //每人每天
            checkOutGood3.setOnline(1); //开启
            checkOutGoods.add(checkOutGood3);


            RedisUtil.set("check_out_goods_item_list", JsonUtils.objectToJson(checkOutGoods));
            String s = RedisUtil.get("check_out_goods_item_list");
            System.out.println(JsonUtils.jsonToList(s, CheckOutGoods.class));
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }


    //1级提货直发限制
    @Test
    public void setCheckRedis1ji() {
        //限购
        List<CheckOutGoods> checkOutGoods = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
//            //消毒液
//            CheckOutGoods checkOutGood1 = new CheckOutGoods();
//            checkOutGood1.setUserId("0123456789");
//            checkOutGood1.setStartTime(sdf.parse("2020-02-13 04:00:00"));
//            checkOutGood1.setEntTime(sdf.parse("2021-04-25 18:00:00"));
//            checkOutGood1.setSkuCode("30290020");
//            checkOutGood1.setNextSkuCode("30290010");
//            checkOutGood1.setCheckAmount(15); //15组
//            checkOutGood1.setSpecNumber(2);
//            checkOutGood1.setType(0); //每人每天
//            checkOutGood1.setOnline(0); //关闭
//            checkOutGoods.add(checkOutGood1);

            //白米浮
            CheckOutGoods checkOutGood2 = new CheckOutGoods();
            checkOutGood2.setUserId("0123456789");
            checkOutGood2.setStartTime(sdf.parse("2020-02-13 04:00:00"));
            checkOutGood2.setEntTime(sdf.parse("2021-04-25 18:00:00"));
            checkOutGood2.setSkuCode("C036-Z");
            checkOutGood2.setNextSkuCode("C036-H");
            checkOutGood2.setCheckAmount(10); //10组
            checkOutGood2.setSpecNumber(3);
            checkOutGood2.setType(0); //每人每天
            checkOutGood2.setOnline(1); //开启
            checkOutGoods.add(checkOutGood2);

            //50+150ml
            CheckOutGoods checkOutGood3 = new CheckOutGoods();
            checkOutGood3.setUserId("0123456789");
            checkOutGood3.setStartTime(sdf.parse("2020-02-13 04:00:00"));
            checkOutGood3.setEntTime(sdf.parse("2021-04-25 18:00:00"));
            checkOutGood3.setSkuCode("30030050");
            checkOutGood3.setNextSkuCode("30030050");
            checkOutGood3.setCheckAmount(10); //10组
            checkOutGood3.setSpecNumber(1);
            checkOutGood3.setType(0); //每人每天
            checkOutGood3.setOnline(1); //开启
            checkOutGoods.add(checkOutGood3);


            RedisUtil.set("check_1_grade_take_of", JsonUtils.objectToJson(checkOutGoods));
            String s = RedisUtil.get("check_1_grade_take_of");
            System.out.println(JsonUtils.jsonToList(s, CheckOutGoods.class));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private CancelOrderService cancelOrderService;


    @Test
    public void test1() {
//        RedisUtil.set("jd_post_fee_reduced_rate","0.6");

        //允许指定混合
//        List<String> list = new ArrayList<>();
//        list.add("40000061,30290030");
//        RedisUtil.set("check_can_blend_order_item", JsonUtils.objectToJson(list));

//        RedisUtil.set("sf:order_re_count", "15000");
//        cancelOrderService.cancel();

//        RedisUtil.set("jd_ex:order_count", "5");

//        RedisUtil.set("check_blend", "0");

//        RedisUtil.set("jd_ex:only_push_ck","1"); //指定商品选京东只推仓库

//        RedisUtil.set("jd_ex:on_off", "0"); //只有京东物流开关

//        RedisUtil.set("exchange_amount_on_off", "0"); //开放换货商品不等抛异常开关，1抛异常

//        RedisUtil.set("limitSH", "0");
//        String s = RedisUtil.get("jd:access_token"); //be38e13b-776e-4c71-a6a4-bfb7f59b508d
//        System.out.println(s);
        RedisUtil.set("jd:access_token", "0d5cc377-fbe1-497f-84c6-437c3a4cfe99");
    }

    @Test
    public void updateStatus() {
        cancelOrderService.updateStatus();
    }

    @Test
    public void test2() {
//        mobilePayService.insertPayInfo();
        String s = RedisUtil.get("ItemSkuStock:" + "1038353239180652544" + "_" + "C036-Z");
        System.out.println(s);
    }


    @Test
    public void test3() {
        RedisUtil.remove("companyInfo:id_1154290768564674560");
    }

    @Test
    public void test6() {
//        RedisUtil.set("enableRetailPriceOnOff","0");
        String enableRetailPriceOnOff = RedisUtil.get("enableRetailPriceOnOff");
        System.out.println(enableRetailPriceOnOff);
    }

    @Test
    public void test7() {
        String s = RedisUtil.get("jd:access_token");
        System.out.println(s);
    }

    @Autowired
    private RedisLockTestService redisLockTestService;

    @Test
    public void test8() {
        redisLockTestService.test("123", "Tom");
    }

    @Test
    public void test9() {
        MallUser user = new MallUser();
        user.setId("111");
        user.setName("jack");


        for (int i = 0; i < 10; i++) {
            int f = i;
            new Thread(() -> {
                redisLockTestService.test3(f, 123, user);
            }).start();
        }
    }


    @Autowired
    private RedisLockService redisLockService;
    int count = 0;

    @Test
    public void test10() {
        int clientcount = 1000;
        CountDownLatch countDownLatch = new CountDownLatch(clientcount);

        ExecutorService executorService = Executors.newFixedThreadPool(clientcount);
        long start = System.currentTimeMillis();
        for (int i = 0; i < clientcount; i++) {
            executorService.execute(() -> {

                //通过Snowflake算法获取唯一的ID字符串
                String lockKey = UUID.randomUUID().toString();
//                String lockKey = "123";
                String lock = "";
                try {
                    lock = redisLockService.lock(lockKey, 10, false);
                    count++;
                } finally {
                    redisLockService.unlock(lockKey, lock);
                }
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        log.info("执行线程数:{},总耗时:{},count数为:{}", clientcount, end - start, count);
    }


    @Autowired
    RateLimitTestService rateLimitTestService;

    @Test
    public void test11() {
        int clientcount = 100;
        CountDownLatch countDownLatch = new CountDownLatch(clientcount);

        ExecutorService executorService = Executors.newFixedThreadPool(clientcount);
        long start = System.currentTimeMillis();
        for (int i = 0; i < clientcount; i++) {
            int finalI = i;
            executorService.execute(() -> {
                rateLimitTestService.rateLimit(finalI + "");
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        log.info("执行线程数:{},总耗时:{},count数为:{}", clientcount, end - start, count);
    }

//    @Test
//    public void test12() throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        LySerialNumberOff lySerialNumberOff = new LySerialNumberOff();
//        lySerialNumberOff.setOn(true);
//        lySerialNumberOff.setStartTime(sdf.parse("2020-06-27 00:00:00"));
//        RedisUtil.set("Ly_SerialNumber_Off", JsonUtils.objectToJson(lySerialNumberOff));
//    }

    @Test
    public void test14() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executorService.execute(() -> {
                System.out.println(finalI);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println("结束了");
    }


    @Test
    public void test13() {
        for (int i = 0; i < 8; i++) {
            Set<String> redisKeys = RedisUtil.getRedisKeys("tx:manager:compensate:mall-admin:2020-0" + i + "*");
            RedisUtil.delKeys(redisKeys);
            Set<String> redisKeys1 = RedisUtil.getRedisKeys("tx:manager:compensate:mall-agent:2020-0" + i + "*");
            RedisUtil.delKeys(redisKeys1);
            Set<String> redisKeys2 = RedisUtil.getRedisKeys("tx:manager:compensate:mall-order:2020-0" + i + "*");
            RedisUtil.delKeys(redisKeys2);
            Set<String> redisKeys3 = RedisUtil.getRedisKeys("tx:manager:compensate:mall-item:2020-0" + i + "*");
            RedisUtil.delKeys(redisKeys3);
            Set<String> redisKeys4 = RedisUtil.getRedisKeys("tx:manager:compensate:mall-pay:2020-0" + i + "*");
            RedisUtil.delKeys(redisKeys4);
            Set<String> redisKeys5 = RedisUtil.getRedisKeys("tx:manager:compensate:mall-user:2020-0" + i + "*");
            RedisUtil.delKeys(redisKeys5);
            Set<String> redisKeys6 = RedisUtil.getRedisKeys("tx:manager:compensate:mall-wf:2020-0" + i + "*");
            RedisUtil.delKeys(redisKeys6);
        }

    }


    private CyclicBarrier cyclicBarrier = new CyclicBarrier(100);
    private CyclicBarrier cyclicBarrier1 = new CyclicBarrier(100);

    @Test
    public void test15() {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    cyclicBarrier.await();

                    MallUser user = new MallUser();
                    user.setId(1 + "");
                    redisLockTestService.test5(user);
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
            ).start();
            new Thread(() -> {
                try {
                    cyclicBarrier1.await();

                    MallUser user = new MallUser();
                    user.setId(2 + "");
                    redisLockTestService.test5(user);
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
            ).start();
        }

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test13() {
//        RedisUtil.set("maldivesOverseas-lock", "1");
        String s = RedisUtil.get("maldivesOverseas-lock");
        System.out.println(s);
    }

}
