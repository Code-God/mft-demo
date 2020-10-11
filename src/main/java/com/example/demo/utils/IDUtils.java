package com.example.demo.utils;

import java.util.Random;

/**
 * @Auther: wxb
 * @Date: 2018/7/30 10:01
 * @Auto: I AM A CODE MAN -_-!
 * @Description: id生成策略
 */
public class IDUtils {

    private static int Guid = 100;
    private static IdWorker idWorker;
    private static OrderWorker orderWorker;

    /**
     * 生成id (64位，19位长度)
     */
    public static String genId() {
        if(idWorker == null) {
            idWorker = new IdWorker();
        }
        return String.valueOf(idWorker.nextId());
    }

    /**
     * 商品id生成 (64位，19位长度)
     */
    public static String genItemId() {
        if(idWorker == null) {
            idWorker = new IdWorker();
        }
        return String.valueOf(idWorker.nextId());
    }

    /**
     * 图片名生成 (64位，19位长度)
     */
    public static String genImageName() {
        if(idWorker == null) {
            idWorker = new IdWorker();
        }
        return String.valueOf(idWorker.nextId());
    }

    /**
     * 获取订单号 仅限订单号使用
     * @return
     */
    public static String genOrderId() {
        if(orderWorker == null) {
            orderWorker = new OrderWorker();
        }
        return String.valueOf(orderWorker.nextId());
    }

//    /**
//     * 生成18位的数字
//     * @return
//     */
//    private static String genId(int type) {
//        IDUtils.Guid += 1;
//        String millis = String.valueOf(System.currentTimeMillis());
//        millis = millis.substring(6, millis.length());
//        //加上四位随机数
//        Random random = new Random();
//        int end4 = random.nextInt(9999);
//        //如果不足两位前面补0
//        String str = String.format("%04d", end4);
//        //要是一段时间内的数据连过大会有重复的情况，所以做以下修改
//        if (IDUtils.Guid > 999) {
//            IDUtils.Guid = 100;
//        }
//        //获取三位随机数
//        Random random1 = new Random();
//        int end2 = random1.nextInt(999);
//        //如果不足三位前面补0
//        String str2 = String.format("%03d", end2);
//        String id = type + str2 + millis + IDUtils.Guid + str;
//        return id;
//    }
//
//    public static String genOrderItemId() {
//        //取当前时间的长整形值包含毫秒
//        String millis = System.currentTimeMillis() + "";
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//        String data = format.format(new Date());
//        millis = millis.substring(7, millis.length());
//        //加上四位随机数
//        Random random = new Random();
//        int end4 = random.nextInt(9999);
//        //如果不足两位前面补0
//        String str = String.format("%04d", end4);
//        return data + millis + str;
//    }
//

    /**
     * 生成定长随机字母数字
     * @param length 长度
     * @return
     */
    public static String getStringRandom(int length) {

        String val = "";
        Random random = new Random();

        //参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    /**
     * 随机id生成
     */
    public static String genRandomId() {
        //取当前时间的长整形值包含毫秒
        String millis = System.currentTimeMillis() + "";

        millis = millis.substring(5, millis.length());
        //加上四位随机数
        Random random = new Random();
        int end4 = random.nextInt(9999);
        //如果不足两位前面补0
        String str = String.format("%04d", end4);

        return millis + str;
    }

    public static void main(String[] args) {
        System.out.println(genItemId());
        System.out.println(genId());
    }
}
