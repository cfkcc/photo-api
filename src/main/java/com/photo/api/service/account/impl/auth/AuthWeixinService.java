package com.photo.api.service.account.impl.auth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.photo.api.common.util.AuthConfig;
import com.photo.api.common.util.HttpUtil;
import com.photo.api.model.user.Auth;
import com.photo.api.service.account.AuthService;

@Service("authWeixin")
public class AuthWeixinService implements AuthService {

	
	
	/*
	 * 移动应用微信登录开发指南
准备工作
移动应用微信登录是基于OAuth2.0协议标准构建的微信OAuth2.0授权登录系统。
在进行微信OAuth2.0授权登录接入之前，在微信开放平台注册开发者帐号，并拥有一个已审核通过的移动应用，并获得相应的AppID和AppSecret，申请微信登录且通过审核后，可开始接入流程。
目前移动应用上微信登录只提供原生的登录方式，需要用户安装微信客户端才能配合使用。
对于Android应用，建议总是显示微信登录按钮，当用户手机没有安装微信客户端时，请引导用户下载安装微信客户端。
对于iOS应用，考虑到iOS应用商店审核指南中的相关规定，建议开发者接入微信登录时，先检测用户手机是否已安装微信客户端（使用sdk中isWXAppInstalled函数 ），对未安装的用户隐藏微信登录按钮，只提供其他登录方式（比如手机号注册登录、游客登录等）。
授权流程说明
微信OAuth2.0授权登录让微信用户使用微信身份安全登录第三方应用或网站，在微信用户授权登录已接入微信OAuth2.0的第三方应用后，第三方可以获取到用户的接口调用凭证（access_token），通过access_token可以进行微信开放平台授权关系接口调用，从而可实现获取微信用户基本开放信息和帮助用户实现基础开放功能等。
微信OAuth2.0授权登录目前支持authorization_code模式，适用于拥有server端的应用授权。该模式整体流程为：
1. 第三方发起微信授权登录请求，微信用户允许授权第三方应用后，微信会拉起应用或重定向到第三方网站，并且带上授权临时票据code参数；
2. 通过code参数加上AppID和AppSecret等，通过API换取access_token；
3. 通过access_token进行接口调用，获取用户基本数据资源或帮助用户实现基本操作。
获取access_token时序图：

第一步：请求CODE
移动应用微信授权登录
开发者需要配合使用微信开放平台提供的SDK进行授权登录请求接入。正确接入SDK后并拥有相关授权域（scope，什么是授权域？）权限后，开发者移动应用会在终端本地拉起微信应用进行授权登录，微信用户确认后微信将拉起开发者移动应用，并带上授权临时票据（code）。
IOS平台应用授权登录接入代码示例（请参考IOS接入指南）：
-(void)sendAuthRequest
{ 
//构造SendAuthReq结构体 
SendAuthReq* req =[[[SendAuthReq alloc ] init ] autorelease ];
req.scope = @"snsapi_userinfo" ;
req.state = @"123" ;
//第三方向微信终端发送一个SendAuthReq消息结构
[WXApi sendReq:req]; }
Android平台应用授权登录接入代码示例（请参考Android接入指南）：
{ 
// send oauth request 
Final SendAuth.Req req = new SendAuth.Req();
req.scope = "snsapi_userinfo";
req.state = "wechat_sdk_demo_test";
api.sendReq(req);
}
参数说明
参数	是否必须	说明
appid	是	应用唯一标识，在微信开放平台提交应用审核通过后获得
scope	是	应用授权作用域，如获取用户个人信息则填写snsapi_userinfo（什么是授权域？）
state	否	用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验
示例：
appid: wxd477edab60670232
scope: snsapi_userinfo
state: wechat_sdk_demo
可拉起微信打开授权登录页：

返回说明
用户点击授权后，微信客户端会被拉起，跳转至授权界面，用户在该界面点击允许或取消，SDK通过SendAuth的Resp返回数据给调用方。
返回值	说明
ErrCode	ERR_OK = 0(用户同意)
ERR_AUTH_DENIED = -4（用户拒绝授权）
ERR_USER_CANCEL = -2（用户取消）
code	用户换取access_token的code，仅在ErrCode为0时有效
state	第三方程序发送时用来标识其请求的唯一性的标志，由第三方程序调用sendReq时传入，由微信终端回传，state字符串长度不能超过1K
lang	微信客户端当前语言
country	微信用户当前国家信息
第二步：通过code获取access_token
获取第一步的code后，请求以下链接获取access_token：
https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
参数说明
参数	是否必须	说明
appid	是	应用唯一标识，在微信开放平台提交应用审核通过后获得
secret	是	应用密钥AppSecret，在微信开放平台提交应用审核通过后获得
code	是	填写第一步获取的code参数
grant_type	是	填authorization_code
返回说明
正确的返回：
{ 
"access_token":"ACCESS_TOKEN", 
"expires_in":7200, 
"refresh_token":"REFRESH_TOKEN",
"openid":"OPENID", 
"scope":"SCOPE" 
}
参数	说明
access_token	接口调用凭证
expires_in	access_token接口调用凭证超时时间，单位（秒）
refresh_token	用户刷新access_token
openid	授权用户唯一标识
scope	用户授权的作用域，使用逗号（,）分隔
错误返回样例：
{"errcode":40029,"errmsg":"invalid code"}
刷新access_token有效期
access_token是调用授权关系接口的调用凭证，由于access_token有效期（目前为2个小时）较短，当access_token超时后，可以使用refresh_token进行刷新，access_token刷新结果有两种：
1. 若access_token已超时，那么进行refresh_token会获取一个新的access_token，新的超时时间；
2. 若access_token未超时，那么进行refresh_token不会改变access_token，但超时时间会刷新，相当于续期access_token。
refresh_token拥有较长的有效期（30天），当refresh_token失效的后，需要用户重新授权。
请求方法
获取第一步的code后，请求以下链接进行refresh_token：
https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN
参数说明
参数	是否必须	说明
appid	是	应用唯一标识
grant_type	是	填refresh_token
refresh_token	是	填写通过access_token获取到的refresh_token参数
返回说明
正确的返回：
{ 
"access_token":"ACCESS_TOKEN", 
"expires_in":7200, 
"refresh_token":"REFRESH_TOKEN", 
"openid":"OPENID", 
"scope":"SCOPE" 
}
参数	说明
access_token	接口调用凭证
expires_in	access_token接口调用凭证超时时间，单位（秒）
refresh_token	用户刷新access_token
openid	授权用户唯一标识
scope	用户授权的作用域，使用逗号（,）分隔
错误返回样例：
{"errcode":40030,"errmsg":"invalid refresh_token"}
第三步：通过access_token调用接口
获取access_token后，进行接口调用，有以下前提：
1. access_token有效且未超时；
2. 微信用户已授权给第三方应用帐号相应接口作用域（scope）。
对于接口作用域（scope），能调用的接口有以下：
授权作用域（scope）	接口	接口说明
snsapi_base	/sns/oauth2/access_token	通过code换取access_token、refresh_token和已授权scope
/sns/oauth2/refresh_token	刷新或续期access_token使用
/sns/auth	检查access_token有效性
snsapi_userinfo	/sns/userinfo	获取用户个人信息
其中snsapi_base属于基础接口，若应用已拥有其它scope权限，则默认拥有snsapi_base的权限。使用snsapi_base可以让移动端网页授权绕过跳转授权登录页请求用户授权的动作，直接跳转第三方网页带上授权临时票据（code），但会使得用户已授权作用域（scope）仅为snsapi_base，从而导致无法获取到需要用户授权才允许获得的数据和基础功能。
接口调用方法可查阅《微信授权关系接口调用指南》
F.A.Q
1. 什么是授权临时票据（code）？
答：第三方通过code进行获取access_token的时候需要用到，code的超时时间为10分钟，一个code只能成功换取一次access_token即失效。code的临时性和一次保障了微信授权登录的安全性。第三方可通过使用https和state参数，进一步加强自身授权登录的安全性。
 
2. 什么是授权作用域（scope）？
答：授权作用域（scope）代表用户授权给第三方的接口权限，第三方应用需要向微信开放平台申请使用相应scope的权限后，使用文档所述方式让用户进行授权，经过用户授权，获取到相应access_token后方可对接口进行调用。
3.开放平台移动应用微信登陆目前是否收费？
答：“微信登录”和第三方网站共享微信庞大的用户价值，同时为微信用户提供更便捷服务和更优质内容，实现双向共赢，目前不收取任何费用。
	 * 
	 * 
	 * 
	 * 
	 * 微信授权关系接口调用指引
通过code获取access_token
接口说明
通过code获取access_token的接口。
请求说明
http请求方式: GET
https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
参数说明
参数	是否必须	说明
appid	是	应用唯一标识，在微信开放平台提交应用审核通过后获得
secret	是	应用密钥AppSecret，在微信开放平台提交应用审核通过后获得
code	是	填写第一步获取的code参数
grant_type	是	填authorization_code
返回说明
正确的返回：
{ 
"access_token":"ACCESS_TOKEN", 
"expires_in":7200, 
"refresh_token":"REFRESH_TOKEN",
"openid":"OPENID", 
"scope":"SCOPE" 
}
参数	说明
access_token	接口调用凭证
expires_in	access_token接口调用凭证超时时间，单位（秒）
refresh_token	用户刷新access_token
openid	授权用户唯一标识
scope	用户授权的作用域，使用逗号（,）分隔
错误返回样例：
{
"errcode":40029,"errmsg":"invalid code"
}
刷新或续期access_token使用
接口说明
access_token是调用授权关系接口的调用凭证，由于access_token有效期（目前为2个小时）较短，当access_token超时后，可以使用refresh_token进行刷新，access_token刷新结果有两种：
1. 若access_token已超时，那么进行refresh_token会获取一个新的access_token，新的超时时间；
2.若access_token未超时，那么进行refresh_token不会改变access_token，但超时时间会刷新，相当于续期access_token。
refresh_token拥有较长的有效期（30天），当refresh_token失效的后，需要用户重新授权。
请求方法
使用/sns/oauth2/access_token接口获取到的refresh_token进行以下接口调用：
http请求方式: GET
https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN
参数说明
参数	是否必须	说明
appid	是	应用唯一标识
grant_type	是	填refresh_token
refresh_token	是	填写通过access_token获取到的refresh_token参数
返回说明
正确的返回：
{ 
"access_token":"ACCESS_TOKEN", 
"expires_in":7200, 
"refresh_token":"REFRESH_TOKEN", 
"openid":"OPENID", 
"scope":"SCOPE" 
}
参数	说明
access_token	接口调用凭证
expires_in	access_token接口调用凭证超时时间，单位（秒）
refresh_token	用户刷新access_token
openid	授权用户唯一标识
scope	用户授权的作用域，使用逗号（,）分隔
错误返回样例：
{
"errcode":40030,"errmsg":"invalid refresh_token"
}
接口说明
检验授权凭证（access_token）是否有效
请求说明
http请求方式: GET
https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID
参数说明
参数	是否必须	说明
access_token	是	调用接口凭证
openid	是	普通用户标识，对该公众帐号唯一
返回说明
正确的Json返回结果：
{ 
"errcode":0,"errmsg":"ok"
}
错误的Json返回示例:
{ 
"errcode":40003,"errmsg":"invalid openid"
}
获取用户个人信息（UnionID机制）
接口说明
此接口用于获取用户个人信息。开发者可通过OpenID来获取用户基本信息。特别需要注意的是，如果开发者拥有多个移动应用、网站应用和公众帐号，可通过获取用户基本信息中的unionid来区分用户的唯一性，因为只要是同一个微信开放平台帐号下的移动应用、网站应用和公众帐号，用户的unionid是唯一的。换句话说，同一用户，对同一个微信开放平台下的不同应用，unionid是相同的。
请求说明
http请求方式: GET
https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID
参数说明
参数	是否必须	说明
access_token	是	调用凭证
openid	是	普通用户的标识，对当前开发者帐号唯一，获取该openid的好友列表
返回说明
正确的Json返回结果：
{ 
"openid":"OPENID",
"nickname":"NICKNAME",
"sex":1,
"province":"PROVINCE",
"city":"CITY",
"country":"COUNTRY",
"headimgurl": "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0",
"privilege":[
"PRIVILEGE1", 
"PRIVILEGE2"
],
"unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL"

}
参数	说明
openid	普通用户的标识，对当前开发者帐号唯一
nickname	普通用户昵称
sex	普通用户性别，1为男性，2为女性
province	普通用户个人资料填写的省份
city	普通用户个人资料填写的城市
country	国家，如中国为CN
headimgurl	用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
privilege	用户特权信息，json数组，如微信沃卡用户为（chinaunicom）
unionid	用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的。
错误的Json返回示例:
{ 
"errcode":40003,"errmsg":"invalid openid"
}
调用频率限制
接口名	频率限制
通过code换取access_token	1万/分钟
刷新access_token	5万/分钟
获取用户基本信息	5万/分钟
	
*/
	public static final String appId=AuthConfig.getString("auth.wx.appId");
	public static final String appKey=AuthConfig.getString("auth.wx.appKey");
	public static final String tokenUrl=AuthConfig.getString("auth.wx.tokenUrl");
	public static final String refreshTokenUrl=AuthConfig.getString("auth.wx.refreshTokenUrl");
	public static final String authUrl=AuthConfig.getString("auth.wx.authUrl");
	
	
	
	@Override
	public Auth auth(String code) {
		StringBuilder params = new StringBuilder();
		params.append("appid=").append(appId);
		params.append("&secret=").append(appKey);
		params.append("&grant_type=authorization_code");
		params.append("&code=").append(code);
		
		
		String result = HttpUtil.getHtml(tokenUrl, "utf-8",params.toString());
		
		if(StringUtils.isNotBlank(result) && !result.contains("callback")){
			JSONObject jsonObject = (JSONObject) new JSONObject().parse(result);
			if(jsonObject.containsKey("access_token")){
				Auth auth = new Auth();
				auth.setAccessToken(jsonObject.getString("access_token"));
				auth.setRefreshToken(jsonObject.getString("refresh_token"));
				auth.setExpires(jsonObject.getLong("expires_in"));
				auth.setOpenId(jsonObject.getString("openid"));
				
				//延长超时时间
				if(auth.getExpires()<3600*24){
					StringBuilder params2 = new StringBuilder();
					params2.append("appid=").append(appId);
					params2.append("&secret=").append(appKey);
					params2.append("&grant_type=refresh_token");
					String result2 = HttpUtil.getHtml(refreshTokenUrl, "utf-8",params2.toString());
					if(StringUtils.isNotBlank(result2) && !result2.contains("callback")){
						Matcher mat =Pattern.compile("\\s*\"expires_in\":\"(.*)\"\\s*").matcher(result2);
						if(mat.find()){
							auth.setExpires(Long.parseLong(mat.group(1)));
						}
					}
				}
				
				
				return auth;
			}
		}
		
		return null;
	}



	@Override
	public boolean auth(String oid, String accessToken, String sysType,String packagename) {
		StringBuilder params = new StringBuilder();
		params.append("access_token=").append(accessToken);
		params.append("&openid=").append(oid);
		
		String result = HttpUtil.getHtml(authUrl, "utf-8",params.toString());
		
		if(StringUtils.isNotBlank(result) && result.contains("\"errcode\":0")){
			return true;
		}
		
		return false;
	}
	
}
