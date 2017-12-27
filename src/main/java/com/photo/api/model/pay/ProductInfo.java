package com.photo.api.model.pay;

import java.io.Serializable;
import java.math.BigDecimal;
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
	private BigDecimal productPrice;
	/**
	 * 商品默认可购买的金币数量
	 */
	private BigDecimal coins;
    /**
     * 优惠比例。小于或等于99%的比率值；
     */
	private BigDecimal discountRatio;
	/**
	 * 商品类型。0表示金币，目前需求只提供钻石一种类型，预留扩展；
	 */
	private Integer productType;
    /**
     * 发行渠道，即支付渠道；不同平台的终端获取的数据有差异；如iOS只获取applePay渠道的商品列表；
     */
	private String channelId;
    /**
     * 币种
     */
	private String currency;
	/**
	 * APP产品ID
	 */
	private String appId;
	/**
	 * APP包名
	 */
	private String packageName;
    /**
     * 0:表示下架,1:表示正常,2:表示促销中；
     */
	private Integer productState;
    /**
     * 创建时间
     */
	private Date createTime;
	/**
	 * 商品图片
	 */
	private String productImg;
	/**
	 * 优惠描述
	 */
	private String discountDesc;
	/**
	 * 优惠活动开始时间
	 */
	private Date discountStartTime;
	/**
	 * 优惠活动截止时间
	 */
	private Date discountEndTime;
	private Date updateTime;
	private String createUser;
	private String updateUser;
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
	public BigDecimal getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
	public BigDecimal getCoins() {
		return coins;
	}
	public void setCoins(BigDecimal coins) {
		this.coins = coins;
	}
	public BigDecimal getDiscountRatio() {
		return discountRatio;
	}
	public void setDiscountRatio(BigDecimal discountRatio) {
		this.discountRatio = discountRatio;
	}
	public Integer getProductType() {
		return productType;
	}
	public void setProductType(Integer productType) {
		this.productType = productType;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
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
	public Integer getProductState() {
		return productState;
	}
	public void setProductState(Integer productState) {
		this.productState = productState;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	@Override
	public String toString() {
		return "ProductInfo [productId=" + productId + ", productName=" + productName + ", productDesc=" + productDesc
				+ ", productPrice=" + productPrice + ", coins=" + coins + ", discountRatio=" + discountRatio
				+ ", productType=" + productType + ", channelId=" + channelId + ", currency=" + currency + ", appId="
				+ appId + ", packageName=" + packageName + ", productState=" + productState + ", createTime="
				+ createTime + ", productImg=" + productImg + ", discountDesc=" + discountDesc + ", discountStartTime="
				+ discountStartTime + ", discountEndTime=" + discountEndTime + ", updateTime=" + updateTime
				+ ", createUser=" + createUser + ", updateUser=" + updateUser + "]";
	}
}
