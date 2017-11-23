package service.test;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.photo.api.service.pay.PayApiService;

@RunWith(SpringJUnit4ClassRunner.class)//表示整合JUnit4进行测试
@ContextConfiguration(locations={"classpath:conf/spring.xml"})//加载spring配置文件
public class PayServiceTest {

	@Resource(name="payApiService")
	private PayApiService payApiService;
	
	@Test
	public void findProductInfo(){
		String channelId ="0cf4a2d6cdd811e7bb381866da0f00a4";
		String[] channelIds = new String[]{"0cf4a4e9cdd811e7bb381866da0f00a4","0cf4a2d6cdd811e7bb381866da0f00a4","0cf4a588cdd811e7bb381866da0f00a4","0cf4a5eccdd811e7bb381866da0f00a4"};
		for (int i = 0; i < channelIds.length; i++) {
			Map<String, Object> result = payApiService.findProductsByChannelId(channelIds[i]);
			JSONObject json = new JSONObject();
			json.putAll(result);
			System.out.println("########################################");
			System.out.println(json);
			System.out.println("########################################");
		}
	}
}
