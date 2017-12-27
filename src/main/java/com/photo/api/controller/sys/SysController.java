package com.photo.api.controller.sys;/**
 * Created by Dell on 2017/11/3.
 */

import com.alibaba.fastjson.JSON;
import com.photo.api.annotation.LoginSign;
import com.photo.api.common.param.BaseParam;
import com.photo.api.common.token.AESUtil;
import com.photo.api.common.token.TokenID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @create 2017-11-03 16:27
 **/
@RestController
@RequestMapping("/sys")
public class SysController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

   /* @LoginSign(isNeedLogin = true)
    @RequestMapping(value = "/awsInfo.action")
    public String getAwsInfo(BaseParam base){
        String info = sysApiService.getAwsInfo();

        return AESUtil.encryptData(info);
    }

    @RequestMapping(value = "/encode.action")
    public String getEncoding(BaseParam baseParams){
	    logger.info("userId:"+baseParams.getUid());
	    String uid= baseParams.getUid();
	    TokenID tokenID = new TokenID(uid);
	    String str = JSON.toJSONString(tokenID);
	    logger.info("待加密的字符串为：{}",str);
        return AESUtil.encryptData(str);
    }*/

}
