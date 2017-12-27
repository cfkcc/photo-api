package com.photo.api.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

public class HttpUtil
{
	public static String getGetRequestUrl(String url,Map<String,Object> params){
		StringBuffer buffer= new StringBuffer();
		for(Entry<String,Object> entry : params.entrySet()){
			buffer.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		if(buffer.substring(buffer.length()-1).equals("&")){
			buffer.deleteCharAt(buffer.length()-1);
		}
		url=url+(url.indexOf("?")!=-1?"&":"?")+buffer.toString();
		return url;
	}
	/**
	 * 通过URL得到url内容
	 * @param url http://**地址
	 * @return 地址对象内容
	 */
	public static String getHtml(String url)
	{
		return getHtml(url, null, null, null);
	}
	
	/**
	 * 通过URL得到url内容
	 * @param url http://**地址
	 * @param charset 编码
	 * @return 地址对象内容
	 */
	public static String getHtml(String url,String charset)
	{
		return getHtml(url,charset,null,null);
	}
	
	/**
	 * 通过URL得到url内容
	 * @param url http://**地址
	 * @param charset 编码
	 * @param params key=value&key=value
	 * @return 地址对象内容
	 */
	public static String getHtml(String url,String charset,String params)
	{
		return getHtml(url,charset,null,params);
	}

	/**
	 * 通过URL得到url内容
	 * @param url http://**地址
	 * @param charset 编码
	 * @param heads http头信息
	 * @param params key=value&key=value
	 * @return 地址对象内容
	 */
	public static String getHtml(String url,String charset,final Map<String,String> heads,String params)
	{
		BufferedReader reader=null;
		OutputStream out=null;
		try
		{
			URL _url=new URL(url);
			HttpURLConnection connection=(HttpURLConnection) _url.openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			if(heads!=null && !heads.isEmpty())
			{
				for (Map.Entry<String, String> entry : heads.entrySet()) 
				{
					connection.setRequestProperty(entry.getKey(),entry.getValue());
				}
			}
			
			if(params!=null && !"".equals(params.trim()))
			{
				out=connection.getOutputStream();
				out.write(params.toString().getBytes());
			}
			
			
			
			if(200!=connection.getResponseCode())
			{
				System.out.print("应答异常 url["+url+"] responseMessage["+connection.getResponseMessage()+"] responseCode["+connection.getResponseCode()+"]");
				return null;
			}
			
			StringBuffer buffer=new StringBuffer();
			String line=null;
			if(charset==null)
			{
				reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
			}else{
				reader=new BufferedReader(new InputStreamReader(connection.getInputStream(),charset));
			}
			while((line=reader.readLine())!=null)
			{
				buffer.append(line);
			}
			return buffer.toString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}finally{
			if(reader!=null){
				try
				{
					reader.close();
				}catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			if(out!=null){
				try
				{
					out.flush();
					out.close();
				}catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 通过URL得到url内容
	 * @param url http://**地址
	 * @param charset 编码
	 * @param heads http头信息
	 * @return 地址对象内容
	 */
	public static String getHtml(String url,String charset,final Map<String,String> heads)
	{
		return getHtml(url, charset, heads, null);
	}
	
	public static String doGet(String url,Map<String,String []> params){
		HttpClient httpClient =null;
		String result =null;
		try{
			httpClient  = new DefaultHttpClient();
			if(null != params){
				StringBuffer urlStr=new StringBuffer();
				for(Entry<String, String[]> set :params.entrySet()){
					String [] values= set.getValue();
					String key = set.getKey();
					for(String value : values){
						urlStr.append(key).append("=").append(value).append("&");
					}
				}
				if(urlStr.length()>0){
					url = url +"?"+ urlStr.substring(0, urlStr.length()-1);
				}
			}
			HttpGet  httpGet = new HttpGet(url);
			HttpResponse httpResponse =httpClient.execute(httpGet);
			HttpEntity entity = httpResponse.getEntity();
			if(null != entity){
				result = EntityUtils.toString(entity, "utf-8");
			}
			EntityUtils.consume(entity);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null !=httpClient){
				httpClient.getConnectionManager().shutdown();
			}
		}
		return result;
	}
	/**
	 * 通过post方式授权访问mas接口
	 * @param uid
	 * @param url
	 * @param params 参数
	 * @return
	 */
	public static String doPost(String url,Map<String,String[]> params){
		HttpClient httpClient =null;
		String result =null;
		try{
			httpClient  = new DefaultHttpClient();
			HttpPost  httpPost = new HttpPost(url);
			if(null != params){
				List<NameValuePair> nvps=new ArrayList<NameValuePair>();
				for(Entry<String, String[]> set :params.entrySet()){
					String key = set.getKey();
					String values [] = set.getValue();
					for(String value : values){
						nvps.add(new BasicNameValuePair(key, value));
					}
				}
				httpPost.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));
			}
			HttpResponse httpResponse =httpClient.execute(httpPost);
			HttpEntity entity = httpResponse.getEntity();
			if(null != entity){
				result = EntityUtils.toString(entity, "utf-8");
			}
			EntityUtils.consume(entity);
		}catch(Exception e){
			
		}finally{
			if(null !=httpClient){
				httpClient.getConnectionManager().shutdown();
			}
		}
		return result;
	}
	
	/**
	 * post请求并获取返回数据
	 * @param url
	 * @param data
	 * @return json string 
	 * @throws Exception 
	 */
	public static Map<String,Object> doPost(String url,String data,String contentType,long timeout){
		DefaultHttpClient httpClient =null;
	    Map<String,Object> result = new HashMap<String,Object>();
		try{
			httpClient  = new DefaultHttpClient();
			HttpPost  httpPost = new HttpPost(url);
//			httpClient.getParams().setLongParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout);
			httpPost.setHeader("content-type",contentType);
			httpPost.setEntity(new StringEntity(data, "UTF-8"));
			HttpResponse httpResponse =httpClient.execute(httpPost);
			//添加请求状态码
			result.put("code", httpResponse.getStatusLine().getStatusCode());
			if(httpResponse.getStatusLine().getStatusCode() == 200){
				HttpEntity entity = httpResponse.getEntity();
				if(null != entity){
					String requestResult = EntityUtils.toString(entity, "UTF-8");
					//添加请求返回结果
					result.put("result", requestResult);
				}
				EntityUtils.consume(entity);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("code", 500);
		}finally{
			if(null !=httpClient){
				httpClient.getConnectionManager().shutdown();
			}
		}
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		
	/*	  String address = "https://www.google.com.hk?aa=bb";  
		   
			Map<String,Object> authParams= new HashMap<String,Object>();
			String tt= UUID.random();
			authParams.put("uid", 1111);
			authParams.put("tt",tt);
			System.out.println(HttpUtil.getGetRequestUrl(address, authParams));*/
		        
	/*	String url = "https://graph.facebook.com/debug_token?input_token=EAACJT2KZCgfIBAAWNsJxdiuQZBMiG5T1wHZAiohsse0WYtwSyhVBEmPSTtlTZCVdEGkTFWIWH2ZBJPAK2wgaYAUHqTEy0TIZBFN3ZAGijMHvYhjq4Lpe6QlLH0LsoGuuTQ1NR7ZCSH4ykWZCHIlPXV2pzixFU3ZAamfuR6vczziNZA2lfeFaOAAotfV1AUwp6uzevf3U6Vh8U7YzS9rCp92An5zbvB1PYEyUlqsCNDjuimmawZDZD&access_token=150974052139506%7C0060f1437f0203257b297188bac4d66a";
		
		Map<String,String> heads = new HashMap<String,String>();
//		heads.put("Referer", "http://m.tvmao.com/");
		heads.put("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
		heads.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*;q=0.8");
		//heads.put("Accept-Encoding", "gzip, deflate, sdch");
		heads.put("Accept-Language", "zh-CN,zh;q=0.8");
		heads.put("Cache-Control", "max-age=0");
		heads.put("Connection", "keep-alive");
//		heads.put("Host", "m.tvmao.com");
//		heads.put("Cookie", "__utma=32842235.1135774885.1419759470.1419759470.1425893741.2; __utmz=32842235.1419759473.1.1.utmcsr=baidu|utmccn=(organic)|utmcmd=organic|utmctr=%E5%B8%A6%E6%B3%AA%E6%A2%A8%E8%8A%B1%E4%B9%8B%E7%BA%A2%E8%9D%8E%E5%AD%90; loc=0; JSESSIONID=abc4db4OsSCcDcJjnJxWu; _gat=1; tmid=769991_14.154.224.89; _ga=GA1.2.1135774885.1419759470");
//		heads.put("Cookie", "__utma=32842235.1135774885.1419759470.1419759470.1425893741.2; __utmz=32842235.1419759473.1.1.utmcsr=baidu|utmccn=(organic)|utmcmd=organic|utmctr=%E5%B8%A6%E6%B3%AA%E6%A2%A8%E8%8A%B1%E4%B9%8B%E7%BA%A2%E8%9D%8E%E5%AD%90; loc=0; JSESSIONID=abc4db4OsSCcDcJjnJxWu; tmid=769991_14.154.224.89; _ga=GA1.2.1135774885.1419759470");
		
		String content = getHtml(url,"utf-8",heads);
		System.out.println(content); */
		// 创建URL对象
		        URL myURL = new URL("https://www.googleapis.com/oauth2/v3/tokeninfo");
//		        URL myURL = new URL("https://graph.facebook.com/debug_token?input_token=EAACJT2KZCgfIBAAWNsJxdiuQZBMiG5T1wHZAiohsse0WYtwSyhVBEmPSTtlTZCVdEGkTFWIWH2ZBJPAK2wgaYAUHqTEy0TIZBFN3ZAGijMHvYhjq4Lpe6QlLH0LsoGuuTQ1NR7ZCSH4ykWZCHIlPXV2pzixFU3ZAamfuR6vczziNZA2lfeFaOAAotfV1AUwp6uzevf3U6Vh8U7YzS9rCp92An5zbvB1PYEyUlqsCNDjuimmawZDZD&access_token=150974052139506%7C0060f1437f0203257b297188bac4d66a");
		        // 创建HttpsURLConnection对象，并设置其SSLSocketFactory对象
		        HttpsURLConnection httpsConn = (HttpsURLConnection) myURL
		                .openConnection();
		        // 取得该连接的输入流，以读取响应内容
		        InputStreamReader insr = new InputStreamReader(httpsConn
		                .getInputStream());
		        // 读取服务器的响应内容并显示
		        int respInt = insr.read();
		        while (respInt != -1) {
		            System.out.print((char) respInt);
		            respInt = insr.read();
		        }
		
	}
	
	
//	public static JSONObject doGetByBrowser(String url)throws Exception{
//		JSONObject obj =null;
//		 try{
//			 DefaultHttpClient client = new DefaultHttpClient();
//			 //设置 HttpClient 接收 Cookie,用与浏览器一样的策略
//			 client.getParams().setParameter("http.protocol.cookie-policy",
//			 CookiePolicy.BROWSER_COMPATIBILITY);
//			 client.getParams().setParameter("http.protocol.single-cookie-header",true);
//			 HttpGet get= new HttpGet("https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=eyJhbGc");
//					 get.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:47.0) Gecko/20100101 Firefox/47.0");
//			 get.setHeader("Host", "www.googleapis.com");
//			 get.setHeader("Accept", "text/html,applic1ation/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//			 get.setHeader("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
//			 get.setHeader("Accept-Encoding","gzip, deflate, br");//没有这句，反馈回来的response Content-Length
//		//	 get.setHeader("Referer","https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=eyJhbGciOiJSUzI1NiIsImtpZCI6ImJhM2YzZTM0NzZlNjIyNzU0ODJmOGFjYjc0YWI2NzBlMzU5NjAzOWYifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhdWQiOiIyNjg5MTIwNDI5MzItOHJtbmkxNTFmaWdlM2tuMGtxazc3MXFmYmJwcjNoanEuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMDYwNDg2MzU2MzQ3NTA2MTMwMDMiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiYXpwIjoiMjY4OTEyMDQyOTMyLXUxMTlwODBuazIxNGhmNGhodWZ1bXNwcjgzN2diampvLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiZW1haWwiOiIzZHplZGFjY291bnRAM2R6ZWQuY29tIiwiaWF0IjoxNDY5MTYzOTA3LCJleHAiOjE0NjkxNjc1MDcsIm5hbWUiOiLlvpfoibIiLCJnaXZlbl9uYW1lIjoi6ImyIiwiZmFtaWx5X25hbWUiOiLlvpciLCJsb2NhbGUiOiJ6aC1DTiJ9.bohQFz82_H5mGfZjv7m50srucZmn-l9LqF73ICvVyypnaUghpciODqTrJvmBHXaZU3H8sVgiiT4yQFhXLLN5aNBmS0aAUIZ4UWMoGqmEM_uvDX33qgmw5yB7RTrY0ht-vuz7AAwnh4c2yogX3Jz-kINCr9am_KB0WyKGLRWhzvLT6FcMyIZPKBZsN22Bqsz4usCOSwTxuI84VhZw8xfvNBo8VDiXfr4ki76vxfMD31f3k1xlr8F6aAOvcIKY10pyLmQWiuEnrNOLJal34r_S2cJL2TWeoE94m7s9OIWtRChgXroZi7UAtzlyxNKVzcJMKSSryNON02ZSPhjG1sRzFA");
//			 get.setHeader("Connection","keep-alive");
//			 CloseableHttpResponse response=client.execute(get);
//			 if(response!=null){
//				 //处理返回信息
//				 if(response.getStatusLine().getStatusCode()==400){
//					 HttpEntity  entity =response.getEntity();
//					 String result = EntityUtils.toString(entity, "utf-8");
//					 obj = JSONObject.fromObject(result);
//				 }else if (response.getStatusLine().getStatusCode()==200){
//					 return obj;
//				 }
//			 }
//			
//			 }catch(Exception e){
//			 e.printStackTrace();
//			 }
//		return  obj;
//	}
	
	/**
	 * 专供auth鉴权使用
	 * @param url
	 * @return
	 */
	public static JSONObject doGetForAuth(String url){
		HttpClient httpClient =null;
		String result =null;
		JSONObject obj =null;
		try{
			httpClient  = new DefaultHttpClient();
			HttpGet  httpGet = new HttpGet(url);
			HttpResponse httpResponse =httpClient.execute(httpGet);
			if(httpResponse!=null){
				if(httpResponse.getStatusLine().getStatusCode()==200){
					HttpEntity entity = httpResponse.getEntity();
					if(null != entity){
						result = EntityUtils.toString(entity, "utf-8");
					}
					obj = new JSONObject().fromObject(result);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null !=httpClient){
				httpClient.getConnectionManager().shutdown();
			}
		}
		return obj;
	}
	
	
	/**
	 * IOS支付专用方法（暂且写死在这里）
	 * @param url_
	 * @param receiptData
	 * @param password
	 * @return
	 */
	public static String doPostForIOSPay(String url_, String receiptData,
			String password) {
		try {
			String ret = "";
			URL url = new URL(url_);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			// conn.setRequestProperty("Content-Type", "text/plain");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/json");
			String input = "{\"receipt-data\": \"" + receiptData
					+ "\", \"password\": \"" + password + "\"}";
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output = "";
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				ret += output;
				// System.out.println(output);
			}

			conn.disconnect();
			return ret;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public static void doGetHeaderRequest(String url){
		HttpClient httpClient =null;
		String result =null;
		HttpResponse httpResponse=null;
		HttpGet httpGet=null;
		try{
			httpClient  = new DefaultHttpClient();
			httpGet = new HttpGet(url);
			httpResponse =httpClient.execute(httpGet);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(httpResponse!=null && httpGet!=null){
				try {
					httpGet.releaseConnection();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
		}
	}
	
	
	public static HttpResponse  getFileInputStream(String url){
		InputStream is = null;
		HttpClient httpClient =null;
		HttpResponse httpResponse=null;
		HttpGet httpGet=null;
		try{
			httpClient  = new DefaultHttpClient();
			httpGet = new HttpGet(url);
			httpResponse =httpClient.execute(httpGet);
			is =httpResponse.getEntity().getContent();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			/*if(httpResponse!=null && httpGet!=null){
				try {
					httpGet.releaseConnection();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}*/
				
		}
		return httpResponse;
	}
	
}
