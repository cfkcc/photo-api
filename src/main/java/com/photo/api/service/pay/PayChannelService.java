package com.photo.api.service.pay;

import java.util.List;

import com.photo.api.common.util.Page;
import com.photo.api.model.pay.PayChannel;

public interface PayChannelService {
	
	public PayChannel findById(String cId);
	
	public Page findByPage(Page page);
	
	public List<PayChannel> findList();

}
