package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Auther: wxb
 * @Date: 2018/10/30 14:51
 * @Auto: I AM A CODE MAN -_-!
 * @Description:
 */
@Slf4j
public class HttpRequest {

    /**
     * 发送post请求
     * @param url
     * @param json
     * @return
     */
    public static String post(String url, String json,String token) {
        HttpClient httpClient = new HttpClient();
        httpClient.getParams().setContentCharset("UTF-8");
        PostMethod method = new PostMethod(url);
        RequestEntity entity = null;
        String returnStr = "";
        try {
            entity = new StringRequestEntity(json, "application/json", "UTF-8");
            method.setRequestEntity(entity);
            if(token != null) {
                method.setRequestHeader("Authorization",token);
            }
            httpClient.executeMethod(method);
            try(InputStream in = method.getResponseBodyAsStream()) {
                //下面将stream转换为String
                StringBuffer sb = new StringBuffer();
                try(InputStreamReader isr = new InputStreamReader(in, "UTF-8")) {
                    char[] b = new char[4096];
                    for (int n; (n = isr.read(b)) != -1; ) {
                        sb.append(new String(b, 0, n));
                    }
                }
                returnStr = sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            method.releaseConnection();
        }
        return returnStr;
    }

}
