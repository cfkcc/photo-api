package service.test;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.photo.api.service.vesion.VersionApiService;

@RunWith(SpringJUnit4ClassRunner.class)//表示整合JUnit4进行测试
@ContextConfiguration(locations={"classpath:conf/spring.xml"})//加载spring配置文件
public class VersionServiceTest {

	@Resource(name="versionApiService")
	private VersionApiService versionApiService;
	
	@Test
	public void addUserLike(){
		String appType ="1";
		String systemType ="ANDROID";
		String channel ="testChannel";
		Map<String, Object> result = versionApiService.findNewVersion(appType, systemType, channel);
		JSONObject json = new JSONObject();
		json.putAll(result);
		System.out.println("########################################");
		System.out.println(json);
		System.out.println("########################################");
	}
	
}
