package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;
import java.util.Map;

/**
 * @Auther: wxb
 * @Date: 2018/10/9 16:41
 * @Auto: I AM A CODE MAN -_-!
 * @Description:
 */
@Slf4j
public class HttpUtils {

    private static final int ConnectionTimeOut = 5000;
    private static final int SOTimeOut = 10000;
    private static final int AP = Runtime.getRuntime().availableProcessors();
    private static final int DefaultMaxConPerHost = AP > 1 ? AP * 10 : 20;
    private static final int DefaultMaxCon = DefaultMaxConPerHost * 10;
    public static MultiThreadedHttpConnectionManager connectionManager;
    private static String execGETMethodJSONSoTimeout;

    static {
        connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.getParams().setConnectionTimeout(ConnectionTimeOut);
        connectionManager.getParams().setSoTimeout(SOTimeOut);
        connectionManager.getParams().setDefaultMaxConnectionsPerHost(DefaultMaxConPerHost);
        connectionManager.getParams().setMaxTotalConnections(DefaultMaxCon);
        log.info("ConnectionTimeOut:{}", ConnectionTimeOut);
        log.info("SOTimeOut:{}", SOTimeOut);

        log.info("DefaultMaxConPerHost:{}", DefaultMaxConPerHost);
        log.info("DefaultMaxCon:{}", DefaultMaxCon);
    }

    public HttpUtils() {

    }

    /**
     * POST多个参数
     * @param url
     * @param parames
     * @return
     * @throws IOException
     * @throws HttpException
     */
    public static String execPOSTMethodMParames(String url, Map<String, String> parames, Map<String, String> headerParames) throws HttpException, IOException {
        StringBuilder reponseStr = new StringBuilder();
        // 构造HttpClient的实例
        HttpClient httpClient = new HttpClient(connectionManager);
        PostMethod postMethod = new PostMethod(url);
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        // 设置body参数
        if (parames.size() > 0) {
            NameValuePair[] data = new NameValuePair[parames.size()];
            int parameLenth = 0;
            for (Map.Entry<String, String> entry : parames.entrySet()) {
                data[parameLenth] = new NameValuePair(entry.getKey(), StringUtils.objectToString(entry.getValue()));
                parameLenth += 1;
            }
            postMethod.setRequestBody(data);
        }
        // 设置头部参数
        if (headerParames.size() > 0) {
            for (Map.Entry<String, String> entry : headerParames.entrySet()) {
                postMethod.setRequestHeader(entry.getKey(), StringUtils.objectToString(entry.getValue()));
            }
        }
        // 执行postMethod
        int statusCode = httpClient.executeMethod(postMethod);
        if (statusCode != HttpStatus.SC_OK) {
            log.info("request url" + url + "   Method failed: " + postMethod.getStatusLine());
        }
        // 读取内容
        byte[] responseBody = postMethod.getResponseBody();
        reponseStr.append(new String(responseBody));
        postMethod.releaseConnection();
        return reponseStr.toString();
    }
}
