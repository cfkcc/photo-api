package com.photo.api.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sumail on 2017/3/13.
 */
public class HttpClientUtils {

     static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    private static PoolingHttpClientConnectionManager cm;
    private static String EMPTY_STR="";
    private static String UTF_8 = "UTF-8";

    private static void init(){
        if(cm == null){
            cm = new PoolingHttpClientConnectionManager();
            cm.setMaxTotal(20);
            cm.setDefaultMaxPerRoute(5);
        }
    }
    /**
     * 通过连接池获取HttpClient
     *
     * @return
     */
    private static CloseableHttpClient getHttpClient() {
        init();
        HttpClientBuilder custom = HttpClients.custom();
        CloseableHttpClient build = custom.setConnectionManager(cm).build();
        return build;
    }

    private static CloseableHttpClient getHttpClient(String username,String password){
        init();
        HttpClientBuilder custom = HttpClients.custom();
        CredentialsProvider provider = new BasicCredentialsProvider();
        AuthScope scope = new AuthScope(AuthScope.ANY);
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username,password);
        provider.setCredentials(scope,credentials);
        custom.setDefaultCredentialsProvider(provider);
        return custom.setConnectionManager(cm).build();
    }
    /**
     *
     * @param url
     * @return
     */
    public static String httpGetRequest(String url) {
        HttpGet httpGet = new HttpGet(url);
        return getResult(httpGet);
    }

    public static String httpGetRequest(String url, Map<String, Object> params){
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);

        HttpGet httpGet = null;
        try {
            httpGet = new HttpGet(ub.build());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return getResult(httpGet);
    }

    public static String httpGetRequest(String url, Map<String, String> headers, Map<String, Object> params)
            throws URISyntaxException {
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);

        HttpGet httpGet = new HttpGet(ub.build());
        for (Map.Entry<String, String> param : headers.entrySet()) {
            httpGet.addHeader(param.getKey(),param.getValue());
        }
        return getResult(httpGet);
    }

    public static String httpGetRequestWithAuth(String username,String password,String url,Map<String,String> headers,Map<String,Object> params){
        StringBuilder stringBuilder = new StringBuilder(url);
        if(null != params){
            stringBuilder.append("?");
            for(Map.Entry<String,Object> param : params.entrySet()){
                stringBuilder.append(param.getKey()).append("=").append(param.getValue());
            }
        }
        HttpGet httpGet = new HttpGet(stringBuilder.toString());
        for (Map.Entry<String, String> head : headers.entrySet()) {
            httpGet.addHeader(head.getKey(), head.getValue());
        }
        return getResultWithAuth(httpGet,username,password);
    }

    public static String httpPostRequest(String url) {
        HttpPost httpPost = new HttpPost(url);
        return getResult(httpPost);
    }

    public static String httpPostRequest(String url, Map<String, Object> params) {
        HttpPost httpPost = new HttpPost(url);
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(),e);
        }
        return getResult(httpPost);
    }

    public static String httpPostRequestWithAuth(String username,String password,String url, Map<String, Object> params)  {
        HttpPost httpPost = new HttpPost(url);
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
        } catch (UnsupportedEncodingException e) {
           logger.error(e.getMessage(),e);
        }
        return getResultWithAuth(httpPost,username,password);
    }

    public static String httpPostRequest(String url, Map<String, Object> headers, Map<String, Object> params){
        HttpPost httpPost = new HttpPost(url);

        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpPost.addHeader(param.getKey(), String.valueOf(param.getValue()));
        }

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(),e);
        }

        return getResult(httpPost);
    }

    private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object> params) {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            pairs.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
        }

        return pairs;
    }

    /**
     * 处理Http请求
     *
     * @param request
     * @return
     */
    private static String getResult(HttpRequestBase request) {
        CloseableHttpClient httpClient = getHttpClient();
        return handlerResult(request,httpClient);
    }

    /**
     * 处理Http请求
     *
     * @param request
     * @return
     */
    private static String getResultWithAuth(HttpRequestBase request,String username,String password) {
        CloseableHttpClient httpClient = getHttpClient(username,password);
        return  handlerResult(request, httpClient);
    }

    private static String handlerResult(HttpRequestBase request, CloseableHttpClient httpClient) {
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity,"UTF-8");
                response.close();
                return result;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return EMPTY_STR;
    }
}
