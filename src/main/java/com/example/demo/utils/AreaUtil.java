package com.example.demo.utils;

import java.util.HashMap;
import java.util.Map;

public class AreaUtil {

    public static Map<String, String[]> createAreaMap() {
        Map<String, String[]> map = new HashMap<>();
        map.put("华北地区", new String[]{"北京", "天津", "河北", "山西", "内蒙古"});
        map.put("华东地区", new String[]{"上海", "江苏", "浙江", "安徽", "福建", "江西", "山东"});
        map.put("东北地区", new String[]{"辽宁", "吉林", "黑龙江"});
        map.put("中南地区", new String[]{"河南", "湖北", "湖南", "广东", "海南", "广西"});
        map.put("西南地区", new String[]{"重庆", "四川", "贵州", "云南", "西藏"});
        map.put("西北地区", new String[]{"陕西", "甘肃", "青海", "宁夏", "新疆"});
        map.put("港澳台地区", new String[]{"香港", "澳门", "台湾"});
        return map;
    }

    public static boolean getAreaByRegional(String[] regional, String province) {
        Map<String, String[]> map = createAreaMap();
        for (String r : regional) {
            if (r.contains("全国")) {
                return true;
            }
            String[] strings = map.get(r);
            if (strings != null) {
                for (String str : strings) {
                    if (province.contains(str)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String[] s = "华东地区".split(",");
        System.out.println(getAreaByRegional(s,"上海市"));
    }
}
