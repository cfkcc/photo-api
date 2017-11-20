package service.test;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.photo.api.common.constant.AlipayConfig;

public class AliPayTest {
	private final static String PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCi8tEfiAKkeDoaT9J/OU3uBrtCEzbtbz9bfm0QwUuDFVusjqp41zaYUE/Y+o4G8MnOsIo95GodwkGnjBnTKhkBb0GbwcPCEfosyJrD2/KpjlQxk0Uq2cFUczPKkATTcdXmfbztnPJ5v6MPqdCDABmMsgvgTlpjAnEa2N+Ag2m9A/ZMmDLKDPZY0ws6peYx9kvLeNoKU8o5rO61T9h/J0QYmoNvzl9I7mZS57ANIWbVrnD0rGxPBGeMgO6JNKPxhHgc3jyLLzwJp3MpD6YoKSk2Zb4S8WSJlJ9fcOiEtUSnb5GpH9Cu/ZG4GzJyNShpFNvOvts8FTeve2Pi1Om5yFYLAgMBAAECggEAbkR86aaDi0LkpruPiZKAvuuFOsB/8grPSB30tlrnu6vvXoUorR+ODWKDtiRPijjVh+4Fjr6MZNj4zuXK8A/T3hmovztPOnwp3LZfrNyhiMYNCYNMUiQLZReUW6Y77c5h3lkGSZFksKjTMp1KweuGXqR5zu+W0NttKKrMDE5hdossO5x8tDqsihwvEKArKkyaijuoCaz1steO1ot9x3QIfliGVMmWJbUglGnpQURayOhHoOoPcSEHh5rSAgsq+bUivu7Nf32h0J8IKmGo8ct/sNHhWRF/umQ1ttZL1yvRwyhtvgwOjl2io81pLQZvDARPOkbGvurMTfvtRwumj/TDAQKBgQDT3s+1VJgy8DvcSCQZfTfxEIfGTbr5cZAkaz5oQc4FaeX3hGZCJ5olInXOV1/9Dli6WvVeOxXxBnLI28VGM4Nw2+S5BwqIhNjb5S1e1LI0GfbS9oyjjTx7dBzN1HCFt/AhgaNy6vXaT5caZdzmvTyaftI3sstH6iH3gHz9ea/diwKBgQDE426WxbyFdGWOnUPD0Fe9TPWIJY5yOC0gv2hRIHKCzOCh2NrM1Z9f/NS93MSDPrUlLOPOazUYkwGp6Y2zNAgUX8Lu6PCwsFfh/gKSw0puoq8y5wpKP4eSTHpXq9TfFQdg7IWdrlkRGOQp/3OTrVZ2HV0qcF88R4zWK1PAbbl5gQKBgCS1dzGIzbINs6FwEmkkK/wljBDMyPeNOJdGyZJQzHxdo82l7/3IVMnYVacBtozm3nKzbc382Mlg/6RTh7OpGz4Dq3ZvhOCwe85lv92tZo7mbMCSnT19LByLuq3i//f7LazwhDxV/3Dq11hoi8SixwxRquShotz78RSr+3wDiHgPAoGAbnmC4vy+xE9EcNpTXLs5kBFvr94K6JIW2EouWV8Qgc49uPK6CrdO/wgZwxLt/Qqhj+N5S6iiGVDbXSCcjtICOpRlB7MS4sF//mGNxhXoXN3R2okDIBg3lOJuv2S+RBJDmqH8czaR6WMDG4Gns1ROsGXXkemNtoAbSUz0aIPoz4ECgYAjwaNNDQbL8wxKfQbEtST0oTztwCU9A4f6Qa6cp0igZgTKjeOWKX9VC3aTo3LgyygxMXfUxV+nYM8D3cvqWkE3OL2MCNHXe+udMpEhtGgut/mUBxYcCR1/nHaJMCwrG00ZoNq1xtws2IvE/xC3yIILZn5SWQW84OGjXSNrcMMI6Q==";
	private final static String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnrdcu33h0s0JfevcnIPOY2Dk3iOHoEi6aKBF4u/Rk+83G0oVvOgZQkApuj9ZZzuedJx6KgOubqbf/ED1jkKV3e+e0+pwpEmMAHmO4hNUaOD5lT5H6yq7zOxOEpc+trVnU2vEYqB67Ywd5pDrbKqHV3wLD+Wj126eZR6zqyd7bDxgLf/zylobGGFvJimLfp7h7sDvPFqMkdgpiV+4ZkLlpbZ8LGOPqYTPmo/5GX/McwWmlthz3Xyhv5Ap9pS+cBDFMAzieUsLhlbB2jFsAn27QdXGtXVdf2KsA50rR1nCoLd/Tu0wQFg6qphagBXUXTcyIH4Co1OfcLzuZYZX5aV4cQIDAQAB";
	private final static String ALI_PAYGATEWAY = "https://openapi.alipaydev.com/gateway.do";
	private final static String ALI_APPID = "2016082500310229";

	public static void main(String[] args) {
		AlipayClient client = new DefaultAlipayClient(ALI_PAYGATEWAY,ALI_APPID,PRIVATE_KEY,"json","utf-8",PUBLIC_KEY);
		
		 // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签     
	    //调用RSA签名方式
	    AlipayTradeWapPayRequest alipay_request=new AlipayTradeWapPayRequest();
	    
	    // 封装请求支付信息
	    AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
	  /*  model.setOutTradeNo(out_trade_no);
	    model.setSubject(subject);
	    model.setTotalAmount(total_amount);
	    model.setBody(body);
	    model.setTimeoutExpress(timeout_express);
	    model.setProductCode(product_code);
	    alipay_request.setBizModel(model);*/
	    // 设置异步通知地址
	    alipay_request.setNotifyUrl(AlipayConfig.notify_url);
	    // 设置同步地址
	    alipay_request.setReturnUrl(AlipayConfig.return_url);   
	}

}
