package Test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import net.sf.json.JSONObject;

public class AppAddTest {
	public static final String ADD_URL = "http://www.2cto.com";  
	  
    public static void appadd() {  
  
        try {  
            //创建连接  
            URL url = new URL(ADD_URL);  
            HttpURLConnection connection = (HttpURLConnection) url  
                    .openConnection();  
            connection.setDoOutput(true);  
            connection.setDoInput(true);  
            connection.setRequestMethod("POST");  
            connection.setUseCaches(false);  
            connection.setInstanceFollowRedirects(true);  
        //application/x-javascript text/xml->xml数据 application/x-javascript->json对象 application/x-www-form-urlencoded->表单数据 application/json;charset=utf-8 -> json数据  
            connection.setRequestProperty("Content-Type",  
                    "application/x-www-form-urlencoded");  
        connection.setRequestProperty("accept", "*/*");  
            connection.setRequestProperty("user-agent",  
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
  
            connection.connect();  
  
            //POST请求  
            DataOutputStream out = new DataOutputStream(  
                    connection.getOutputStream());  
            JSONObject obj = new JSONObject();  
            obj.element("app_name", "asdf");  
            obj.element("app_ip", "10.21.243.234");  
            obj.element("app_port", 8080);  
            obj.element("app_type", "001");  
            obj.element("app_area", "asd");  
  
            out.writeBytes(obj.toString());  
            out.flush();  
            out.close();  
  
            //读取响应  
            BufferedReader reader = new BufferedReader(new InputStreamReader(  
                    connection.getInputStream()));  
            String lines;  
            StringBuffer sb = new StringBuffer("");  
            while ((lines = reader.readLine()) != null) {  
                lines = new String(lines.getBytes(), "utf-8");  
                sb.append(lines);  
            }  
            System.out.println(sb);  
            reader.close();  
            // 断开连接  
            connection.disconnect();  
        } catch (MalformedURLException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
  
    }  
  
    public static void main(String[] args) {  
        appadd();  
    }  
}
