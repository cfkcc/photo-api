package com.photo.api.service.account.impl.auth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.photo.api.common.enums.SysType;
import com.photo.api.common.util.AuthConfig;
import com.photo.api.common.util.HttpUtil;
import com.photo.api.model.user.Auth;
import com.photo.api.service.account.AuthService;

@Service("authTW")
public class AuthTwService implements AuthService {

	/*
	 * 
	 * 使用Authorization_Code获取Access_Token
	 * 
	 * 本步骤的作用： 通过用户验证登录和授权，获取Access Token，为下一步获取用户的OpenID做准备； 同时，Access
	 * Token是应用在调用OpenAPI访问和修改用户数据时必须传入的参数。
	 * 移动端应用可以直接获得AccessToken，请参考使用Implicit_Grant方式获取Access_Token 本步骤在整个流程中的位置：
	 * oauth2.0_guid_3.png
	 * 
	 * 目录 [隐藏] 上一步 1. 简介 2. 过程详解 Step1：获取Authorization Code
	 * Step2：通过Authorization Code获取Access Token Step3：（可选）权限自动续期，获取Access Token
	 * 3. 快速上手 4. 其他资源 下一步 上一步
	 * 
	 * 放置“QQ登录”按钮_OAuth2.0 1. 简介
	 * 
	 * 即server-side模式，是OAuth2.0认证的一种模式，又称Web Server Flow； 适用于需要从web
	 * server访问的应用，例如Web/wap网站。 其授权验证流程示意图如下（图片来源：OAuth2.0协议草案V21的4.1节 ）
	 * OAuth_guide_V2_1.png 对于应用而言，需要进行两步： 1. 获取Authorization Code； 2.
	 * 通过Authorization Code获取Access Token 2. 过程详解
	 * 
	 * Step1：获取Authorization Code
	 * 
	 * 请求地址： PC网站：https://graph.qq.com/oauth2.0/authorize
	 * WAP网站：https://graph.z.qq.com/moc2/authorize 请求方法： GET 请求参数： 请求参数请包含如下内容：
	 * 参数 是否必须 含义 response_type 必须 授权类型，此值固定为“code”。 client_id 必须
	 * 申请QQ登录成功后，分配给应用的appid。 redirect_uri 必须
	 * 成功授权后的回调地址，必须是注册appid时填写的主域名下的地址，建议设置为网站首页或网站的用户中心。注意需要将url进行URLEncode。
	 * state 必须
	 * client端的状态值。用于第三方应用防止CSRF攻击，成功授权后回调时会原样带回。请务必严格按照流程检查用户与state参数状态的绑定。
	 * scope 可选 请求用户授权时向用户显示的可进行授权的列表。
	 * 可填写的值是API文档中列出的接口，以及一些动作型的授权（目前仅有：do_like），如果要填写多个接口名称，请用逗号隔开。
	 * 例如：scope=get_user_info,list_album,upload_pic,do_like
	 * 不传则默认请求对接口get_user_info进行授权。 建议控制授权项的数量，只传入必要的接口名称，因为授权项越多，用户越可能拒绝进行任何授权。
	 * display 可选 仅PC网站接入时使用。 用于展示的样式。不传则默认展示为PC下的样式。
	 * 如果传入“mobile”，则展示为mobile端下的样式。 g_ut 可选 仅WAP网站接入时使用。 QQ登录页面版本（1：wml版本；
	 * 2：xhtml版本），默认值为1。
	 * 
	 * 返回说明： 1. 如果用户成功登录并授权，则会跳转到指定的回调地址，并在redirect_uri地址后带上Authorization
	 * Code和原始的state值。如：
	 * PC网站：http://graph.qq.com/demo/index.jsp?code=9A5F*******
	 * *****************06AF&state=test
	 * WAP网站：http://open.z.qq.com/demo/index.jsp
	 * ?code=9A5F************************06AF&state=test 注意：此code会在10分钟内过期。 2.
	 * 如果用户在登录授权过程中取消登录流程，对于PC网站，登录页面直接关闭；对于WAP网站，同样跳转回指定的回调地址，
	 * 并在redirect_uri地址后带上usercancel参数和原始的state值，其中usercancel值为非零，如：
	 * http://open.z.qq.com/demo/index.jsp?usercancel=1&state=test 错误码说明：
	 * 接口调用有错误时，会返回code和msg字段，以url参数对的形式返回，value部分会进行url编码（UTF-8）。
	 * PC网站接入时，错误码详细信息请参见：100000-100031：PC网站接入时的公共返回码。
	 * WAP网站接入时，错误码详细信息请参见：6000-6999：获取Authorization Code时，发生错误。
	 * Step2：通过Authorization Code获取Access Token
	 * 
	 * 请求地址： PC网站：https://graph.qq.com/oauth2.0/token
	 * WAP网站：https://graph.z.qq.com/moc2/token 请求方法： GET 请求参数： 请求参数请包含如下内容： 参数
	 * 是否必须 含义 grant_type 必须 授权类型，在本步骤中，此值为“authorization_code”。 client_id 必须
	 * 申请QQ登录成功后，分配给网站的appid。 client_secret 必须 申请QQ登录成功后，分配给网站的appkey。 code 必须
	 * 上一步返回的authorization code。 如果用户成功登录并授权，则会跳转到指定的回调地址，并在URL中带上Authorization
	 * Code。 例如，回调地址为www.qq.com/my.php，则跳转到：
	 * http://www.qq.com/my.php?code=520DD95263C1CFEA087****** 注意此code会在10分钟内过期。
	 * redirect_uri 必须 与上面一步中传入的redirect_uri保持一致。
	 * 
	 * 返回说明： 如果成功返回，即可在返回包中获取到Access Token。 如：
	 * access_token=FE04*****************
	 * *******CCE2&expires_in=7776000&refresh_token
	 * =88E4************************BE14 参数说明 描述 access_token 授权令牌，Access_Token。
	 * expires_in 该access token的有效期，单位为秒。 refresh_token
	 * 在授权自动续期步骤中，获取新的Access_Token时需要提供的参数。
	 * 
	 * 错误码说明： 接口调用有错误时，会返回code和msg字段，以url参数对的形式返回，value部分会进行url编码（UTF-8）。
	 * PC网站接入时，错误码详细信息请参见：100000-100031：PC网站接入时的公共返回码。
	 * WAP网站接入时，错误码详细信息请参见：7000-7999：通过Authorization Code获取Access Token时，发生错误。
	 * 
	 * Step3：（可选）权限自动续期，获取Access Token
	 * 
	 * Access_Token的有效期默认是3个月，过期后需要用户重新授权才能获得新的Access_Token。本步骤可以实现授权自动续期，
	 * 避免要求用户再次授权的操作，提升用户体验。 请求地址： PC网站：https://graph.qq.com/oauth2.0/token
	 * WAP网站：https://graph.z.qq.com/moc2/token 请求方法： GET 请求参数： 请求参数请包含如下内容： 参数
	 * 是否必须 含义 grant_type 必须 授权类型，在本步骤中，此值为“refresh_token”。 client_id 必须
	 * 申请QQ登录成功后，分配给网站的appid。 client_secret 必须 申请QQ登录成功后，分配给网站的appkey。
	 * refresh_token 必须 在Step2中，返回的refres_token。
	 * 
	 * 返回说明： 如果成功返回，即可在返回包中获取到Access Token。 如：
	 * access_token=FE04*****************
	 * *******CCE2&expires_in=7776000&refresh_token
	 * =88E4************************BE14。
	 * 
	 * 参数说明 描述 access_token 授权令牌，Access_Token。 expires_in 该access
	 * token的有效期，单位为秒。 refresh_token 在授权自动续期步骤中，获取新的Access_Token时需要提供的参数。
	 * 
	 * 错误码说明： 接口调用有错误时，会返回code和msg字段，以url参数对的形式返回，value部分会进行url编码（UTF-8）。
	 * PC网站接入时，错误码详细信息请参见：100000-100031：PC网站接入时的公共返回码。
	 * WAP网站接入时，错误码详细信息请参见：7000-7999：通过Authorization Code获取Access Token时，发生错误。
	 * 3. 快速上手
	 * 
	 * 详见：开发攻略_Server-side。 4. 其他资源
	 * 
	 * 移动端应用可以直接获得AccessToken，请参考使用Implicit_Grant方式获取Access_Token 。 下一步
	 * 
	 * 获取用户OpenID_OAuth2.0 1 请求地址
	 * 
	 * PC网站：https://graph.qq.com/oauth2.0/me
	 * WAP网站：https://graph.z.qq.com/moc2/me 2 请求方法
	 * 
	 * GET 3 请求参数
	 * 
	 * 请求参数请包含如下内容： 参数 是否必须 含义 access_token 必须 在Step1中获取到的access token。
	 * 
	 * 4 返回说明
	 * 
	 * PC网站接入时，获取到用户OpenID，返回包如下： 1 callback(
	 * {"client_id":"YOUR_APPID","openid":"YOUR_OPENID"} ); WAP网站接入时，返回如下字符串：
	 * client_id=100222222&openid=1704************************878C
	 * openid是此网站上唯一对应用户身份的标识，网站可将此ID进行存储便于用户下次登录时辨识其身份，或将其与用户在网站上的原有账号进行绑定。 5
	 * 错误码说明
	 * 
	 * 接口调用有错误时，会返回code和msg字段，以url参数对的形式返回，value部分会进行url编码（UTF-8）。
	 * PC网站接入时，错误码详细信息请参见：100000-100031：PC网站接入时的公共返回码。
	 * WAP网站接入时，错误码详细信息请参见：9000-9999：根据Access Token获得对应用户身份的openid时，发生错误。
	 * 
	 * 
	 * 
	 * Step5：使用Access Token以及OpenID来访问和修改用户数据
	 * 
	 * 1. 建议网站在用户登录后，即调用get_user_info接口，获得该用户的头像、昵称并显示在网站上，使用户体验统一。 2.
	 * 调用其他OpenAPI，以访问和修改用户数据。所有OpenAPI详见API列表。 以调用get_user_info接口为例：
	 * （1）发送请求到get_user_info的URL（请将access_token，appid等参数值替换为你自己的）：
	 * https://graph.
	 * qq.com/user/get_user_info?access_token=YOUR_ACCESS_TOKEN&oauth_consumer_key
	 * =YOUR_APP_ID&openid=YOUR_OPENID （2）成功返回后，即可获取到用户数据： 1 2 3 4 5 6 {
	 * "ret":0, "msg":"", "nickname":"YOUR_NICK_NAME", ... }
	 */
	public static final String appId = AuthConfig.getString("auth.tw.appId.ios");
	public static final String appKey = AuthConfig.getString("auth.tw.appKey.ios");

	public static final String authUrl = AuthConfig
			.getString("auth.tw.authUrl.ios");

	public static final String meUrl = AuthConfig.getString("auth.tw.meUrl.ios");

	public static final String callback = AuthConfig
			.getString("auth.tw.callback.ios");

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
			JSONObject jsonObject = (JSONObject) new JSONObject().parse(result);
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



	@Override
	public boolean auth(String oid, String accessToken,String sysType,String packagename) {
		boolean isAuth = false;
		try {
			String encodedCredentials = accessToken;
			String appId=null;
			String appKey=null;
			/*if(OriginType.SED_PLAYER.getType()==origin){
				if(SysType.IOS.toString().equals(sysType)){
					appId=AuthConfig.getString("auth.tw.appId.ios");
					appKey=AuthConfig.getString("auth.tw.appKey.ios");
				}else if(SysType.ANDROID.toString().equals(sysType)){
					//按照报名分别处理
					appId=AuthConfig.getString("auth.tw.appId.android");
					appKey=AuthConfig.getString("auth.tw.appKey.android");
				}
			}else if(OriginType.SED_LIVE.getType()==origin){*/
				if(SysType.IOS.toString().equals(sysType)){
					appId=AuthConfig.getString("auth.tw.appId.ios");
					appKey=AuthConfig.getString("auth.tw.appKey.ios");
				}else if(SysType.ANDROID.toString().equals(sysType)){
					appId=AuthConfig.getString("auth.tw.appId.android");
					appKey=AuthConfig.getString("auth.tw.appKey.android");
				}
				
//			}
			//https://api.twitter.com/oauth/authenticate?oauth_token=Z6eEdO8MOmk394WozF5oKyuAv855l4Mlqo7hhlSLik
			//https://api.twitter.com/1.1/account/verify_credentials.json
			//
			//做拼接
			String desUrl=(meUrl+"?" +
					"oauth_consumer_key="+appId +
					"&oauth_token=" +appKey+
					"&oauth_nonce=1dea468bf1f30848dd77fa26089df013" +
					"&oauth_signature=mLYp4Xwn0A%2F3FFwBiBp4Zdd0wTY%3D" +
					"&oauth_signature_method=HMAC-SHA1" +
					"&oauth_timestamp=" +(System.currentTimeMillis()/1000)+
					"&oauth_version=1.0");
			return this.fetchTimelineTweet(accessToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isAuth;
	}


	private  String readResponse(HttpsURLConnection connection) {
		try {
			StringBuilder str = new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line = "";
			while ((line = br.readLine()) != null) {
				str.append(line + System.getProperty("line.separator"));
			}
			return str.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return new String("");
		}

	}

	private  boolean fetchTimelineTweet(String bearerToken)
			throws IOException {
		HttpsURLConnection connection = null;
		try {
			URL url = new URL(meUrl);
			connection = (HttpsURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Host", "api.twitter.com");
			connection.setRequestProperty("User-Agent", "Your Program Name");
			connection.setRequestProperty("Authorization", "Basic "+ bearerToken);
			/*
			connection.setRequestProperty("OAuth oauth_consumer_key", "myfnv3EOLm5alxcgIPffHMcE7");
			connection.setRequestProperty("oauth_nonce", "e4a4d7ab9f792c6082ac1edbdad11c30");
			connection.setRequestProperty("oauth_signature", "TO8hV%2FakVyDy4r1JmJmPAxiKN14%3D");
			connection.setRequestProperty("oauth_signature_method", "HMAC-SHA1");
			connection.setRequestProperty("oauth_timestamp", "1473578399");
			connection.setRequestProperty("oauth_token", "754858754441699328-SEnzfTGF0TjDIHE9NJ81jSiTdUjf5NC");
			connection.setRequestProperty("oauth_version", "1.0");*/
			connection.setUseCaches(false);
			String ret = readResponse(connection);
			/*ret = ret.replace("callback( ", "");
			ret = ret.replace(" );", "");*/
			if(StringUtils.isBlank(ret)){
				return true;
			}else{
				return false;
			}
			/*JSONObject obj = JSONObject.fromObject(ret);
			if (obj != null) {
			}
			return new String();*/
		}

		catch (MalformedURLException e) {
			throw new IOException("Invalid endpoint URL specified.", e);
		}
		finally {
			if (connection != null) {
				connection.disconnect();
			}

		}

	}
	
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis()/1000);
	}

}
