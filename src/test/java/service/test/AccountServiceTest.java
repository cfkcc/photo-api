package service.test;

import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.photo.api.common.util.Page;
import com.photo.api.model.user.UserFans;
import com.photo.api.service.account.AccountApiService;
import com.photo.api.service.account.UserFansService;
import com.photo.api.service.account.UserLikeService;

@RunWith(SpringJUnit4ClassRunner.class)//表示整合JUnit4进行测试
@ContextConfiguration(locations={"classpath:conf/spring.xml"})//加载spring配置文件
public class AccountServiceTest {

	private final static String [] user_arrays = {
			"0005bc3c224544fe9ea7ec6e71afc64f",
			"0005df670c894c4b9b0a7d7307bbd6cd",
			"00060851bd6a44e48b4c72af57c147fb",
			"00062d0042ad45e18de56e3ec3d542d1",
			"000644a0bfc646c793decad750e134d7",
			"00077260bb98424b8a60fadf36442eb2",
			"000844df9eff42bb8d58fb90e92b671a",
			"00092f4b09eb4290b9133aa46b5b8e20",
			"0009ccdfc98044e2a109246ee31f4ec4",
			"000d8e5eb5164ef7b697e838dea5d99c",
			"000efe03a29f4977b27536378ce2a211",
			"000f15e978e940e79c72e217d3d948c7",
			"000fe2853e9249b8988e288f7c8263fd",
			"0013c9900ac84a49ba3298c291f5da4e",
			"00140f23a74a470595453061b3207a62",
			"001596208d16443094327fb2234fe04f",
			"00159c35f44940b5990ee05a977b699b",
			"00159e20c6764525b53d2d709114ab48",
			"00162a0c172f45629f8c66d4712b3e9b",
			"0018f5dac5a647bbaf1a387aea40f1f4",
			"001a1807c9094fa0a02fbbb0604bba55"
			
	};

	@Resource(name="userFansService")
	private UserFansService userFansService;
	
	@Resource(name="userLikeService")
	private UserLikeService userLikeService;
	@Resource(name="accountApiService")
	private AccountApiService accountApiService;
	
//	@Test
	public void addUserLike(){
		String likerId ="00004c879b3d4697add98d47cde5f1d0";
		for (int i = 0; i < user_arrays.length; i++) {
			String userId =user_arrays[i];
			userLikeService.saveOrUpdateUserLike(userId, likerId, Boolean.TRUE);
		}
		System.out.println("########################################");
		System.out.println("点赞成功！");
		System.out.println("########################################");
	}
	
//	@Test
	public void updateUserLike(){
		String userId ="00004c879b3d4697add98d47cde5f1d0";
		String likerId ="00022758da034680b19453d01b12eeef";
		userLikeService.saveOrUpdateUserLike(userId, likerId, Boolean.TRUE);
		System.out.println("########################################");
		System.out.println("点赞取消成功！");
		System.out.println("########################################");
	}
//	@Test
	public void findLikeCountByLikerId(){
		String likerId ="00004c879b3d4697add98d47cde5f1d0";
		long count = userLikeService.findLikeCountByLikerId(likerId);
		System.out.println("########################################");
		System.out.println("00004c879b3d4697add98d47cde5f1d0 用户被点赞："+count+"次");
		System.out.println("########################################");
	}
	
//	@Test
	public void addUserFollowed(){
		String userId ="00004c879b3d4697add98d47cde5f1d0";
		for (int i = 0; i < user_arrays.length; i++) {
			String fansId =user_arrays[i];
			userFansService.saveOrUpdateUserFans(userId, fansId, Boolean.TRUE);
		}
		System.out.println("########################################");
		System.out.println("关注成功！");
		System.out.println("########################################");
	}
	
//	@Test
	public void updateUserFollowed(){
		String userId ="00004c879b3d4697add98d47cde5f1d0";
		String fansId ="0018f5dac5a647bbaf1a387aea40f1f4";
		userFansService.saveOrUpdateUserFans(userId, fansId, Boolean.FALSE);
		System.out.println("########################################");
		System.out.println("关注取消成功！");
		System.out.println("########################################");
	}
//	@Test
	public void findCountByUserId(){
		String userId ="00004c879b3d4697add98d47cde5f1d0";
		long count = userFansService.findCountByUserId(userId);
		System.out.println("########################################");
		System.out.println("00004c879b3d4697add98d47cde5f1d0 用户关注了："+count+"个用户");
		System.out.println("########################################");
	}
	
//	@Test
	public void findFansCountByFansId(){
		String userId ="00004c879b3d4697add98d47cde5f1d0";
		long count = userFansService.findFansCountByUserId(userId);
		System.out.println("########################################");
		System.out.println("00004c879b3d4697add98d47cde5f1d0 用户被关注了："+count+"次");
		System.out.println("########################################");
	}
//	@Test
	/*public void findFansByPage(){
		String fansId ="00004c879b3d3434697add98d47cde5f1d0";
//		Page page = userFansService.findFansByPage(fansId,1,10," create_time desc");
		Page page = userFansService.findUsersByPage(fansId,1,10," create_time desc");
		Iterator<UserFans> it = (Iterator<UserFans>) page.getRecords().iterator();
		while(it.hasNext()){
			System.out.println("########################################");
			System.out.println(it.next());
			System.out.println("########################################");
		}
	}*/
//	@Test
	public void findFansByPage(){
//		String fansId ="00004c879b3d3434697add98d47cde5f1d0";
		String userId ="00004c879b3d4697add98d47cde5f1d0";
		Page page = new Page();
		page.setOrderBy(" create_time desc");
		page.getParams().put("userId", userId);
		Map<String, Object> result = accountApiService.findFans(page);
		JSONObject json = new JSONObject();
		json.putAll(result);
		System.out.println("########################################");
		System.out.println(json);
		System.out.println("########################################");
	}
//	@Test
	public void findFollowedByPage(){
		String fansId ="00004c879b3d4697add98d47cde5f1d0";
		Page page = new Page();
		page.setOrderBy(" create_time desc");
		page.getParams().put("fansId", fansId);
		page = userFansService.findUsersByPage(page);
		Map<String, Object> result = accountApiService.findFowllowed(page);
		JSONObject json = new JSONObject();
		json.putAll(result);
		System.out.println("########################################");
		System.out.println(json);
		System.out.println("########################################");
	}
	
	@Test
	public void findUserInfo(){
		String userId ="00004c879b3d4697add98d47cde5f1d0";
		Map<String, Object> result = accountApiService.getUserInfo(userId);
		JSONObject json = new JSONObject();
		json.putAll(result);
		System.out.println("########################################");
		System.out.println(json);
		System.out.println("########################################");
	}
}
