package com.photo.api.model.pay;

import java.io.Serializable;
import java.util.Date;


public class ProductInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
	private String productId;
    /**
     * 商品名称
     */
	private String productName;
    /**
     * 商品描述
     */
	private String productDesc;
    /**
     * 商品默认价格
     */
	private String productPrice;
    /**
     * 基础商品价值。如500金币/钻石。
     */
	private String basicGold;
    /**
     * 溢价金币；如+100金币/钻石；
     */
	private String premiumGold;
    /**
     * 优惠比例。大于或等于100%的比率值；
     */
	private String discountRatio;
    /**
     * 商品类型。0表示钻石，目前需求只提供钻石一种类型，预留扩展；
     */
	private Integer productType;
    /**
     * 发行渠道，即支付渠道；不同平台的终端获取的数据有差异；如iOS只获取applePay渠道的商品列表；
     */
	private String payChannel;

	private String areacode;
    /**
     * 币种
     */
	private String currency;
    /**
     * APP产品ID
     */
	private String appId;
    /**
     * cp用户id
     */
	private Long cpId;
    /**
     * 0:表示下架,1:表示正常,2:表示促销中；
     */
	private Integer productState;
    /**
     * 创建时间
     */
	private Date createTime;
	private String productImg;
	private String discountDesc;
	private Date discountStartTime;
	private Date discountEndTime;
	private Boolean discountable; 

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getBasicGold() {
		return basicGold;
	}

	public void setBasicGold(String basicGold) {
		this.basicGold = basicGold;
	}

	public String getPremiumGold() {
		return premiumGold;
	}

	public void setPremiumGold(String premiumGold) {
		this.premiumGold = premiumGold;
	}

	public String getDiscountRatio() {
		return discountRatio;
	}

	public void setDiscountRatio(String discountRatio) {
		this.discountRatio = discountRatio;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

    public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Long getCpId() {
		return cpId;
	}

	public void setCpId(Long cpId) {
		this.cpId = cpId;
	}

	public Integer getProductState() {
    	return productState;
    }

    public void setProductState(Integer productState) {
    	this.productState = productState;
    }

    public String getProductImg() {
    	return productImg;
    }

    public void setProductImg(String productImg) {
    	this.productImg = productImg;
    }

    public String getDiscountDesc() {
        return discountDesc;
    }

    public void setDiscountDesc(String discountDesc) {
        this.discountDesc = discountDesc;
    }

    public Date getDiscountStartTime() {
        return discountStartTime;
    }

    public void setDiscountStartTime(Date discountStartTime) {
        this.discountStartTime = discountStartTime;
    }

    public Date getDiscountEndTime() {
        return discountEndTime;
    }

    public void setDiscountEndTime(Date discountEndTime) {
        this.discountEndTime = discountEndTime;
    }

    public Boolean getDiscountable() {
        return discountable;
    }

    public void setDiscountable(Boolean discountable) {
        this.discountable = discountable;
    }

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\"productId\":\"");
        builder.append(productId);
        builder.append("\", \"productName\":\"");
        builder.append(productName);
        builder.append("\", \"productDesc\":\"");
        builder.append(productDesc);
        builder.append("\", \"productPrice\":\"");
        builder.append(productPrice);
        builder.append("\", \"basicGold\":\"");
        builder.append(basicGold);
        builder.append("\", \"premiumGold\":\"");
        builder.append(premiumGold);
        builder.append("\", \"discountRatio\":\"");
        builder.append(discountRatio);
        builder.append("\", \"productType\":\"");
        builder.append(productType);
        builder.append("\", \"payChannel\":\"");
        builder.append(payChannel);
        builder.append("\", \"areacode\":\"");
        builder.append(areacode);
        builder.append("\", \"currency\":\"");
        builder.append(currency);
        builder.append("\", \"appId\":\"");
        builder.append(appId);
        builder.append("\", \"cpId\":\"");
        builder.append(cpId);
        builder.append("\", \"productState\":\"");
        builder.append(productState);
        builder.append("\", \"createTime\":\"");
        builder.append(createTime);
        builder.append("\", \"productImg\":\"");
        builder.append(productImg);
        builder.append("\", \"discountDesc\":\"");
        builder.append(discountDesc);
        builder.append("\", \"discountStartTime\":\"");
        builder.append(discountStartTime);
        builder.append("\", \"discountEndTime\":\"");
        builder.append(discountEndTime);
        builder.append("\", \"discountable\":\"");
        builder.append(discountable);
        builder.append("\"}");
        return builder.toString();
    }
}
