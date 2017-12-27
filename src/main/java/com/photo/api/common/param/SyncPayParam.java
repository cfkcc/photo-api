package com.photo.api.common.param;

import java.io.Serializable;

public class SyncPayParam implements Serializable{
	private static final long serialVersionUID = 1L;
	private String code;
	private String timestamp;
	private String charset;
	private String msg;
	private String seller_id;
	private Double total_amount;
	private String app_id;
	private String trade_no;
	private String out_trade_no;
	
}
