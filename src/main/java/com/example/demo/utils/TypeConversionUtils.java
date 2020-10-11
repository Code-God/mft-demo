package com.example.demo.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by kai.zhang on 2017-10-18.
 */
public class TypeConversionUtils {

    public static Long parseLong(Object object){
        try{
            return Long.parseLong(getString(object));
        }catch(Exception e){
            return null;
        }
    }

    public static Integer parseInt(Object object){
        try{
            return Integer.parseInt(getString(object));
        }catch(Exception e){
            return null;
        }
    }

    public static Float parseFloat(Object object){
        try{
            return Float.parseFloat(getString(object));
        }catch(Exception e){
            return null;
        }
    }

    public static Boolean parseBoolean(Object object){
        try{
            return Boolean.parseBoolean(getString(object));
        }catch(Exception e){
            return null;
        }
    }

    public static Double parseDouble(Object object){
        try{
            return Double.parseDouble(getString(object));
        }catch(Exception e){
            return null;
        }
    }

    public static String parseString(Object object){
        if(object == null){
            return null;
        }
        if(object.toString().equals("null")){
            return null;
        }
        // 注意：String类型  null和""
        if(object.toString().equals("")){
            return "";
        }
        return object.toString();
    }

    public static Map<String, List<Map<String, String>>> parseI18nMap(Object object){
        try{
            return (Map<String, List<Map<String, String>>>)object;
        }catch(Exception e){
            return null;
        }
    }

    public static ZonedDateTime parseZonedDateTime(Object object){
        String string = getString(object);
        try{
            return ZonedDateTime.parse(new CharSequence() {
                @Override
                public int length() {
                    return string.length();
                }

                @Override
                public char charAt(int index) {
                    return string.charAt(index);
                }

                @Override
                public CharSequence subSequence(int start, int end) {
                    return string.subSequence(start,end);
                }
            });
        }catch(Exception e){
            return null;
        }
    }

    public static String getString(Object object){
        if(object == null){
            return null;
        }
        if(object.toString().equals("") || object.toString().equals("null")){
            return null;
        }
        return object.toString();
    }

    public static <T extends Object> List<T> listAutoAdd(List<T> list,T value){
        return listAutoAdd(false,list,value);
    }

    public static <T extends Object> List<T> listAutoAdd(boolean isUniqueness,List<T> list,T value){
        if(getString(value) == null){
            return list;
        }
        if(list == null){
            list = new ArrayList();
        }
        if(isUniqueness){
            if(list.contains(value)){
                return list;
            }
        }
        list.add(value);
        return list;
    }

    public static byte[] toByteArray (Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray ();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    public static Boolean isEmpty(Object object){
        if(getString(object) == null){
            return true;
        }
        return false;
    }

    public static Boolean isNotEmpty(Object object){
        if(getString(object) == null){
            return false;
        }
        return true;
    }
}
