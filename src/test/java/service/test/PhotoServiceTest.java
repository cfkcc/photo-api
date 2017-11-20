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
import com.photo.api.model.photo.Photo;
import com.photo.api.model.photo.PhotoGroup;
import com.photo.api.service.photo.PhotoBuyRecordService;
import com.photo.api.service.photo.PhotoGroupService;
import com.photo.api.service.photo.PhotoService;
import com.photo.api.service.photo.PhotosApiService;

@RunWith(SpringJUnit4ClassRunner.class)//表示整合JUnit4进行测试
@ContextConfiguration(locations={"classpath:conf/spring.xml"})//加载spring配置文件
public class PhotoServiceTest {


	@Resource(name="photoBuyRecordService")
	private PhotoBuyRecordService photoBuyRecordService;
	@Resource(name="photoGroupService")
	private PhotoGroupService photoGroupService;
	@Resource(name="photoService")
	private PhotoService photoService;
	@Resource(name="photosApiService")
	private PhotosApiService photosApiService;
	
//	@Test
	public void findFansByPage(){
		String buyerId ="0018f5dac5a647bbaf1a387aea40f1f4";
		Page page = new Page();
		page.setPageNo(1);
		page.getParams().put("userId", "00004c879b3d4697add98d47cde5f1d0");
		page.getParams().put("isHot", 1);
		page.getParams().put("status", 1);
		page.getParams().put("flag", 0);
		page.getParams().put("abroad", 1);
		page.setOrderBy(" pg.create_time desc");
		photoGroupService.findByPage(page);
		Iterator<PhotoGroup> it = (Iterator<PhotoGroup>) page.getRecords().iterator();
		System.out.println("########################################");
		System.out.println("PhotoGroup totalPage = "+page.getPageCount());
		System.out.println("PhotoGroup totalRows = "+page.getRowCount());
		while(it.hasNext()){
			PhotoGroup pg = it.next();
			System.out.println(pg);
			Page photoPage = new Page();
			photoPage.getParams().put("groupId", pg.getGroupId());
			photoPage.getParams().put("status", 1);
			photoPage.getParams().put("flag", 0);
			photoPage.setOrderBy(" sort asc");
			photoService.findByPage(photoPage);
			Iterator<Photo> photoIt = (Iterator<Photo>) photoPage.getRecords().iterator();
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("Photo totalPage = "+photoPage.getPageCount());
			System.out.println("Photo totalRows = "+photoPage.getRowCount());
			while(photoIt.hasNext()){
				Photo p = photoIt.next();
				System.out.println(p);
				Boolean isBuy = photoBuyRecordService.isBuy(buyerId, p.getPhotoId());
				System.out.println("Is Buy? ====>"+isBuy);
			}
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
			
		}
		System.out.println("########################################");
	}
	
//	@Test
	public void updateUserLike(){
		String groupId ="0004c8748gtf434697add98d47cde5f";
		String[] photoIds =new String[]{
				"0000012e4e0c4f3c8c18a88ca55f26fa",
				"0000014a8cc34799b2cf5af38009ed19",
				"0000041cfd6f49a8bbb80e586d581de1",
				"0000043ac9714e66a81fd9205c1e4a88",
				"00000474f66c43d68a41a7b566526e02",
				"00000704ab6d4f58a187e7b740b1fdea",
				"00000be7d62c46f3974227ae260927fd",
				"00000ff5656e403bbd482b428caf6184",
				"00001048094341c2988ab814b80e1c9c"
				};
		String[] photoId =new String[]{
//				"0000012e4e0c4f3c8c18a88ca55f26fa"
		};
		Integer groupCoins = photoService.findCoinsByGroupId(groupId);
		Integer photosCoins = photoService.findCoinsByPhotoIds(photoIds);
		Integer photoCoins = photoService.findCoinsByPhotoIds(photoId);
		System.out.println("########################################");
		System.out.println("套图价格 = "+groupCoins);
		System.out.println("多图图价格 = "+photosCoins);
		System.out.println("单图图价格 = "+photoCoins);
		System.out.println("########################################");
	}
	
//	@Test
	public void findPhotoGroupByPage(){
		String uid = "00004c879b3d4697add98d47cde5f1d0";
		Page page = new Page();
		page.setPageNo(1);
		page.setPageSize(4);
		page.getParams().put("isHot", 1);
		page.getParams().put("status", 1);
		page.getParams().put("flag", 0);
//		page.getParams().put("abroad", 1);
		page.getParams().put("uid", uid);
		page.setOrderBy(" create_time desc");
		Map<String, Object> map = photosApiService.findPhotoGroupsByPage(page);
		JSONObject json = new JSONObject();
		json.putAll(map);
		System.out.println("########################################");
		System.out.println(json);
		System.out.println("########################################");
	}
	
//	@Test
	public void findPhotosByPage(){
		String uid = "00004c879b3d4697add98d47cde5f1d0";
		Page page = new Page();
		page.setPageNo(1);
//		page.getParams().put("isHot", 1);
		page.getParams().put("status", 1);
		page.getParams().put("flag", 0);
		page.getParams().put("groupId", "0004c8748gtf434697add98d47cde5f");
		page.getParams().put("uid", uid);
		page.setOrderBy(" create_time desc");
		Map<String, Object> map = photosApiService.findPhotosByPage(page);
		JSONObject json = new JSONObject();
		json.putAll(map);
		System.out.println("########################################");
		System.out.println(json);
		System.out.println("########################################");
	}
	@Test
	public void findChoices(){
		String photoId = "00001b536889420f9daea97f7e23981e";
		Map<String, Object> map = photosApiService.findChoices(photoId);
		JSONObject json = new JSONObject();
		json.putAll(map);
		System.out.println("########################################");
		System.out.println(json);
		System.out.println("########################################");
	}
}
