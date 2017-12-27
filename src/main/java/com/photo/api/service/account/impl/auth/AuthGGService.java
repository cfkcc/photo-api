package com.photo.api.service.account.impl.auth;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.photo.api.common.enums.SysType;
import com.photo.api.common.util.AuthConfig;
import com.photo.api.common.util.HttpUtil;
import com.photo.api.model.user.Auth;
import com.photo.api.service.account.AuthService;

import net.sf.json.JSONObject;

@Service("authGG")
public class AuthGGService implements AuthService {

	public static final String appId = AuthConfig.getString("auth.gg.appId.ios");
	public static final String appKey = AuthConfig.getString("auth.gg.appKey.ios");

	public static final String authUrl = AuthConfig
			.getString("auth.gg.authUrl.ios");

	public static final String meUrl = AuthConfig.getString("auth.gg.meUrl.ios");

	public static final String callback = AuthConfig
			.getString("auth.gg.callback.ios");

	@Override
	public Auth auth(String code) {
		StringBuilder params = new StringBuilder();
		params.append("client_id=").append(appId);
		params.append("&client_secret=").append(appKey);
		params.append("&grant_type=authorization_code");
		params.append("&code=").append(code);
		params.append("&redirect_uri=").append(callback);

		String result = HttpUtil.getHtml(authUrl, "utf-8", params.toString());

		if (StringUtils.isNotBlank(result) && !result.contains("callback")) {
			JSONObject jsonObject = JSONObject.fromObject(result);
			if (jsonObject.containsKey("access_token")) {
				Auth auth = new Auth();
				auth.setAccessToken(jsonObject.getString("access_token"));
				auth.setRefreshToken(jsonObject.getString("refresh_token"));
				auth.setExpires(jsonObject.getLong("expires_in"));

				String result2 = HttpUtil.getHtml(meUrl, "utf-8",
						"access_token=" + auth.getAccessToken());

				Matcher mat = Pattern.compile("\\s*\"openid\":\"(.*?)\"\\s*")
						.matcher(result2);
				if (mat.find()) {
					auth.setOpenId(jsonObject.getString(mat.group(1)));
				}

				return auth;
			}
		}

		return null;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {

		/*
		 * String xx =
		 * "callback( {\"client_id\":\"YOUR_APPID\",\"openid\":\"YOUR_OPENID\",\"cli\":\"xx\"} );"
		 * ; String regex = "\\s*\"openid\":\"(.*?)\"\\s*"; Pattern pattern
		 * =Pattern.compile(regex); Matcher mat = pattern.matcher(xx);
		 * if(mat.find()){ String dateStr = mat.group(1);
		 * System.out.println(dateStr); }
		 * 
		 * //System.out.println(URLEncoder.encode(callback,"utf8"));
		 * 
		 * AuthQQ qq = new AuthQQ();
		 * qq.auth("861DE1495C2344A3AC6783F77D8A35D9");
		 */

	}

	@Override
	public boolean auth(String oid, String accessToken, String sysType,String packagename) {
		boolean isAuth=false;
		try{
			JSONObject obj = HttpUtil.doGetForAuth(meUrl+"?id_token="+accessToken);
			if(obj==null){
				return isAuth;
			}
			String appId =null;
			if(SysType.IOS.toString().equals(sysType)){
				appId =AuthConfig.getString("auth.gg.appId.ios");
			}else if(SysType.ANDROID.toString().equals(sysType)){
				appId =AuthConfig.getString("auth.gg.appId.android");
			}
			if(appId.equals(obj.getString("aud"))){
				isAuth= true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return isAuth;
	}

}
