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
     * 优惠比例。大于或等于100%的比率值；
     */
	private BigDecimal discountRatio;
    /**
     * 发行渠道，即支付渠道；不同平台的终端获取的数据有差异；如iOS只获取applePay渠道的商品列表；
     */
	private String payChannelId;
    /**
     * 币种
     */
	private String currency;
    /**
     * 0:表示下架,1:表示正常,2:表示促销中；
     */
	private Integer productState;
    /**
     * 创建时间
     */
	private Date createTime;
	private String discountDesc;
	private Date discountStartTime;
	private Date discountEndTime;
	/**
	 * 首充比例
	 */
	private BigDecimal firstDiscountRatio;

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

    public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getProductState() {
    	return productState;
    }

    public void setProductState(Integer productState) {
    	this.productState = productState;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getDiscountRatio() {
		return discountRatio;
	}

	public void setDiscountRatio(BigDecimal discountRatio) {
		this.discountRatio = discountRatio;
	}

	public String getPayChannelId() {
		return payChannelId;
	}

	public void setPayChannelId(String payChannelId) {
		this.payChannelId = payChannelId;
	}

	public BigDecimal getFirstDiscountRatio() {
		return firstDiscountRatio;
	}

	public void setFirstDiscountRatio(BigDecimal firstDiscountRatio) {
		this.firstDiscountRatio = firstDiscountRatio;
	}

	public BigDecimal getCoins() {
		return coins;
	}

	public void setCoins(BigDecimal coins) {
		this.coins = coins;
	}

	@Override
	public String toString() {
		return "ProductInfo [productId=" + productId + ", productName=" + productName + ", productDesc=" + productDesc
				+ ", productPrice=" + productPrice + ", coins=" + coins + ", discountRatio=" + discountRatio
				+ ", payChannelId=" + payChannelId + ", currency=" + currency + ", productState=" + productState
				+ ", createTime=" + createTime + ", discountDesc=" + discountDesc + ", discountStartTime="
				+ discountStartTime + ", discountEndTime=" + discountEndTime + ", firstDiscountRatio="
				+ firstDiscountRatio + "]";
	}

	/*@Override
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
        builder.append("\", \"discountRatio\":\"");
        builder.append(discountRatio);
        builder.append("\", \"payChannelId\":\"");
        builder.append(payChannelId);
        builder.append("\", \"currency\":\"");
        builder.append(currency);
        builder.append("\", \"productState\":\"");
        builder.append(productState);
        builder.append("\", \"createTime\":\"");
        builder.append(createTime);
        builder.append("\", \"discountDesc\":\"");
        builder.append(discountDesc);
        builder.append("\", \"discountStartTime\":\"");
        builder.append(discountStartTime);
        builder.append("\", \"discountEndTime\":\"");
        builder.append(discountEndTime);
        builder.append("\", \"firstDiscountRatio\":\"");
        builder.append(firstDiscountRatio);
        builder.append("\"}");
        return builder.toString();
    }*/
	
	
}
