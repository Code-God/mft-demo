package com.example.demo.utils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: wxb
 * @Date: 2018/8/15 19:00
 * @Auto: I AM A CODE MAN -_-!
 * @Description: 运费计算
 */
public class PostFeeUtil {

    //sku 规格 重量
    private static String[][] smallItemFreightArray = {{"C036-X", "120", "12"}, {"C036-H", "1", "0.1"}, {"C036-Z", "3", "0.3"}, {"C038-H", "1", "0.1"},
            {"C038-Z", "3", "0.3"}, {"C039-H", "1", "0.1"}, {"C039-Z", "3", "0.3"}, {"C040-H", "1", "0.1"}, {"C040-Z", "3", "0.3"},
            {"C037-H", "1", "0.1"}, {"C041-H", "1", "0.1"}, {"C042-H", "1", "0.14"}};

    private static String[][] FEIJIAFreightArray = {{"C035-H", "1", "0.25"}};

    private static String[][] MEIFUTEFreightArray = {{"C033-H", "1", "0.5"}, {"C034-H", "1", "0.5"}};

    private static String[] otherAreaArray = {"北京", "天津", "河北", "山西", "内蒙古", "福建", "江西", "山东", "河南", "湖北", "湖南", "广东",
            "广西", "海南", "重庆", "四川", "贵州", "云南", "西藏", "陕西", "甘肃", "青海", "宁夏", "新疆", "台湾", "香港", "澳门"};

    private static String[] cheapAreaArray = {"上海", "江苏", "浙江", "安徽"};
    private static String[] northAreaArray = {"辽宁", "吉林", "黑龙江"};

    public static int reckonPsotFee(String[] productArray, String areaStr) {
        int smallItemCount = 0;
        int smallItemWeight = 0;
        int FEIJIACount = 0;
        int FEIJIAWeight = 0;
        int MEIFUTECount = 0;
        double MEIFUTEWeight = 0;
        int bulkPackageCount = 0;

        for (int i = 0; i < productArray.length; i++) {

            String productItem = productArray[i]; //拿到传过来的数组的值

            for (int j = 0; j < smallItemFreightArray.length; j++) {
                String[] freightItem = smallItemFreightArray[j];

                int featureIndex = productItem.indexOf(freightItem[0]); //判断该商品是否属于该数组中

                if (featureIndex >= 0) { //如果在
                    int setCount = Integer.parseInt(freightItem[1]); //拿取规格
                    float setWeight = Float.parseFloat(freightItem[2]); //拿取重量
                    String countStrOrigin = productItem.substring(featureIndex + freightItem[0].length(), productItem.length()); //拿到传过来的数量

                    String countStr = countStrOrigin.trim();
                    int itemCount = Double.valueOf(countStr).intValue(); //数量

                    smallItemCount += itemCount * setCount; //小商品总数+数量*规格
                    smallItemWeight += itemCount * setWeight; //小商品重量+数量*重量
                }
            }

            for (int j = 0; j < FEIJIAFreightArray.length; j++) {
                String[] freightItem = FEIJIAFreightArray[j];

                int featureIndex = productItem.indexOf(freightItem[0]); //判断该商品是否属于该数组中

                if (featureIndex >= 0) { //如果在
                    int setCount = Integer.parseInt(freightItem[1]); //拿取规格
                    float setWeight = Float.parseFloat(freightItem[2]); //拿取重量
                    String countStrOrigin = productItem.substring(featureIndex + freightItem[0].length(), productItem.length()); //拿到传过来的数量

                    String countStr = countStrOrigin.trim();
                    int itemCount = Double.valueOf(countStr).intValue(); //数量

                    FEIJIACount += itemCount * setCount;  //菲嘉商品总数+数量*规格
                    FEIJIAWeight += itemCount * setWeight; //菲嘉商品重量+数量*重量
                }
            }

            for (int j = 0; j < MEIFUTEFreightArray.length; j++) {
                String[] freightItem = MEIFUTEFreightArray[j];

                int featureIndex = productItem.indexOf(freightItem[0]); //判断该商品是否属于该数组中

                if (featureIndex >= 0) { //如果在
                    int setCount = Integer.parseInt(freightItem[1]); //拿取规格
                    float setWeight = Float.parseFloat(freightItem[2]); //拿取重量
                    String countStrOrigin = productItem.substring(featureIndex + freightItem[0].length(), productItem.length()); //拿到传过来的数量

                    String countStr = countStrOrigin.trim();
                    int itemCount = Double.valueOf(countStr).intValue(); //数量

                    MEIFUTECount += itemCount * setCount; //美浮特商品总数+数量*规格
                    MEIFUTEWeight += itemCount * setWeight; //美浮特商品重量+数量*重量
                }
            }
        }

        int first1KGFreight = 0;  //首重费用
        int add1KGFreight = 0;    //每增加一公斤费用
        int firstPackageFreight = 0; //首箱费用
        int addPackageFreight = 0; //每增加一箱费用
        /*
          1:江浙沪皖  2:其他地区 3::辽吉黑
         */
        int areaType = 0;  //分类区域

        // "上海", "江苏", "浙江", "安徽"
        for (int i = 0; i < cheapAreaArray.length; i++) {
            int areaFeatureIndex = areaStr.indexOf(cheapAreaArray[i]);
            if (areaFeatureIndex >= 0) {
                first1KGFreight = 5;  //首重5元
                add1KGFreight = 1;    //每增加一公斤1元
                firstPackageFreight = 17; //首箱17元
                addPackageFreight = 17; //每增加一箱17元
                areaType = 1;
                break;
            }
        }

        // "北京", "天津", "河北", "山西", "内蒙古", "福建", "江西", "山东", "河南", "湖北", "湖南", "广东","广西", "海南", "重庆", "四川", "贵州", "云南", "西藏", "陕西", "甘肃", "青海", "宁夏", "新疆", "台湾", "香港特别行政区", "澳门"
        if (areaType == 0) {
            for (int i = 0; i < otherAreaArray.length; i++) {
                int areaFeatureIndex = areaStr.indexOf(otherAreaArray[i]);
                if (areaFeatureIndex >= 0) {
                    first1KGFreight = 8; //首重8元
                    add1KGFreight = 5;   //每增加1公斤5元
                    firstPackageFreight = 50; //首箱50元
                    addPackageFreight = 25;  //每增加一箱25元
                    areaType = 2;
                    break;
                }
            }
        }

        // "辽宁", "吉林", "黑龙江"
        if (areaType == 0) {
            for (int i = 0; i < northAreaArray.length; i++) {
                int areaFeatureIndex = areaStr.indexOf(northAreaArray[i]);
                if (areaFeatureIndex >= 0) {
                    first1KGFreight = 8; //首重8元
                    add1KGFreight = 5; //每增加1公斤5元
                    firstPackageFreight = 50; //首箱50元
                    addPackageFreight = 25; //每增加一箱25元
                    areaType = 3;
                    break;
                }
            }
        }

        int freight = 0;  //初始化运费
        int smallItemPackageCount = (int) (Math.floor(smallItemCount / 120));  //算箱
        if (smallItemPackageCount > 0) {
            int tempPackageCount = Integer.valueOf(String.valueOf(smallItemPackageCount)); //箱数
            if (firstPackageFreight != 0) {  //首箱费用不为0
                freight += firstPackageFreight; //加上首箱费用
                firstPackageFreight = 0; //首箱费用至零
                first1KGFreight = 0;
                tempPackageCount -= 1; //减去第1箱
            }
            while (tempPackageCount > 0) {
                freight += addPackageFreight;  //除去首箱每箱递增费用
                tempPackageCount -= 1;
            }
        }
        smallItemCount = smallItemCount - smallItemPackageCount * 120; //不成箱的数量
        smallItemWeight = smallItemWeight - smallItemPackageCount * 12; //不成箱的重量

        int FEIJIAPackageCount = (int) (Math.floor(FEIJIACount / 40)); //算箱
        if (FEIJIAPackageCount > 0) {
            int tempPackageCount = Integer.valueOf(String.valueOf(FEIJIAPackageCount));
            if (firstPackageFreight != 0) {
                freight += firstPackageFreight;
                firstPackageFreight = 0;
                first1KGFreight = 0;
                tempPackageCount -= 1;
            }
            while (tempPackageCount > 0) {
                freight += addPackageFreight;
                tempPackageCount -= 1;
            }
        }
        FEIJIACount = FEIJIACount - FEIJIAPackageCount * 40; //不成箱的数量
        FEIJIAWeight = FEIJIAWeight - FEIJIAPackageCount * 10; //不成箱的重量

        int MEIFUTEPackageCount = (int) (Math.floor(MEIFUTECount / 25)); //算箱
        if (MEIFUTEPackageCount > 0) {
            int tempPackageCount = Integer.valueOf(String.valueOf(MEIFUTEPackageCount));
            if (firstPackageFreight != 0) {
                freight += firstPackageFreight;
                firstPackageFreight = 0;
                first1KGFreight = 0;
                tempPackageCount -= 1;
            }
            while (tempPackageCount > 0) {
                freight += addPackageFreight;
                tempPackageCount -= 1;
            }
        }
        MEIFUTECount = MEIFUTECount - MEIFUTEPackageCount * 25;  //不成箱的数量
        MEIFUTEWeight = MEIFUTEWeight - MEIFUTEPackageCount * 12.5; //不成箱的重量

        double weight = smallItemWeight + FEIJIAWeight + MEIFUTEWeight; //不成箱的商品总重
        if (areaType != 1) {  //不属于江浙沪地区
            // 12kg can be packed in one box when areaType != 1
            bulkPackageCount = (int) (Math.floor(weight / 12)); //不成箱的商品总重用12公斤成箱
            if (bulkPackageCount > 0) {
                int tempPackageCount = bulkPackageCount;
                if (firstPackageFreight != 0) { //判断是否首箱
                    freight += firstPackageFreight;
                    firstPackageFreight = 0;
                    first1KGFreight = 0;
                    tempPackageCount -= 1;
                }
                while (tempPackageCount > 0) {
                    freight += addPackageFreight;
                    tempPackageCount -= 1;
                }
            } else {  //如果不满12公斤成箱,则按9公斤成箱，
                if (firstPackageFreight != 0 && weight > 9) { //判断是否首箱
                    freight += firstPackageFreight;
                    firstPackageFreight = 0;
                    first1KGFreight = 0;
                    weight = 0;
                    bulkPackageCount = 1;
                } else if (firstPackageFreight == 0 && weight > 4) {
                    freight += addPackageFreight;
                    weight = 0;
                    bulkPackageCount = 1;
                }
            }
            weight = weight - bulkPackageCount * 12;
        }

        if (smallItemPackageCount == 0 && FEIJIAPackageCount == 0 && MEIFUTEPackageCount == 0 && bulkPackageCount == 0) {
            weight += 0.25;// box weight 0.25kg
        }
        if (freight == 0) {
            freight = first1KGFreight;
            weight -= 1;
        }
        String weightStr = String.valueOf(weight);
        int pointIndex = weightStr.indexOf("0.");
        if (pointIndex == 0) {
            weight = (int) (Math.floor(weight)) + 1;
        }
        if (freight == 0) {
            freight = first1KGFreight;
            weight -= 1;
        }
        while (weight > 0) {
            freight += add1KGFreight;
            weight -= 1;
        }
        return freight;
    }

    public static BigDecimal reckonPsotFee(List<String> skuFreightList, String province) {
        BigDecimal freight = BigDecimal.ZERO;
        if (skuFreightList.size() > 0) {
            String[] skuFreightArray = new String[skuFreightList.size()];
            skuFreightArray = skuFreightList.toArray(skuFreightArray);
            //算运费
            int freightamt = reckonPsotFee(skuFreightArray, province);
            freight = new BigDecimal(freightamt);
        }
        return freight;
    }

    public static void main(String[] args) {
        String[] productArray = {"C033-H 20"};
        int result = reckonPsotFee(productArray, "上海市");
        System.out.println(result);
    }


}
