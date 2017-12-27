package com.photo.api.service.account.impl.auth;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.photo.api.common.enums.SysType;
import com.photo.api.common.util.AuthConfig;
import com.photo.api.common.util.HttpClientUtil;
import com.photo.api.common.util.HttpUtil;
import com.photo.api.model.user.Auth;
import com.photo.api.service.account.AuthService;

import net.sf.json.JSONObject;

@Service("authFB")
public class AuthFBService implements AuthService {

	private static Logger logger = LoggerFactory.getLogger(AuthFBService.class);
	public static final String appId=AuthConfig.getString("auth.fb.appId.android");
	public static final String appKey=AuthConfig.getString("auth.fb.appKey.android");
	public static final String authUrl=AuthConfig.getString("auth.fb.authUrl.android");
	public static final String meUrl=AuthConfig.getString("auth.fb.meUrl.android");
	public static final String callback=AuthConfig.getString("auth.fb.callback.android");
	
	
	@Override
	public Auth auth(String code) {
		StringBuilder params = new StringBuilder();
		params.append("client_id=").append(appId);
		params.append("&client_secret=").append(appKey);
		params.append("&grant_type=authorization_code");
		params.append("&code=").append(code);
		params.append("&redirect_uri=").append(callback);
		
		
		String result = HttpUtil.getHtml(authUrl, "utf-8",params.toString());
		
		if(StringUtils.isNotBlank(result) && !result.contains("callback")){
			JSONObject jsonObject = JSONObject.fromObject(result);
			if(jsonObject != null && jsonObject.containsKey("access_token")){
				Auth auth = new Auth();
				auth.setAccessToken(jsonObject.getString("access_token"));
				auth.setRefreshToken(jsonObject.getString("refresh_token"));
				auth.setExpires(jsonObject.getLong("expires_in"));
				
				String result2 = HttpUtil.getHtml(meUrl, "utf-8","access_token="+auth.getAccessToken());
				
				Matcher mat =Pattern.compile("\\s*\"openid\":\"(.*?)\"\\s*").matcher(result2);
				if(mat.find()){
					auth.setOpenId(jsonObject.getString(mat.group(1)));
				}
				
				return auth;
			}
		}
		
		return null;
	}
		
		
		
	public static void main(String []args) throws UnsupportedEncodingException{
		if(SysType.ANDROID.toString().equals("ANDROID"))
			System.out.println("yes");
		else{
			System.out.println("no");
		}
		
	}



	@Override
	public boolean auth(String oid, String accessToken, String sysType,String packagename) {
		logger.info("-------------------开始校验-----------------------");
		boolean isAuth=false;
		try{
			logger.info("-------------------111111111-----------------------");
			logger.info("-----"+ meUrl+"?input_token="+accessToken+"&access_token="+appId+"%7C"+appKey);
	        JSONObject jsonObject = JSONObject.fromObject(HttpClientUtil.doGet(meUrl+"?input_token="+accessToken+"&access_token="+appId+"%7C"+appKey));
			JSONObject data = (JSONObject) jsonObject.get("data");
			if(data != null && data.containsKey("is_valid")){
				String is_valid = data.getString("is_valid");
				if(is_valid != null && is_valid.equals("true")){
					isAuth = true;
				}
	        }
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return isAuth;
	}
	
	
	
}
