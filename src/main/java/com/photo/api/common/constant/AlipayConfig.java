package com.photo.api.common.constant;

public class AlipayConfig {
	// 商户appid
	public static String APPID = "2016082500310229";
	// 私钥 pkcs8格式的
	public static String RSA_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCi8tEfiAKkeDoaT9J/OU3uBrtCEzbtbz9bfm0QwUuDFVusjqp41zaYUE/Y+o4G8MnOsIo95GodwkGnjBnTKhkBb0GbwcPCEfosyJrD2/KpjlQxk0Uq2cFUczPKkATTcdXmfbztnPJ5v6MPqdCDABmMsgvgTlpjAnEa2N+Ag2m9A/ZMmDLKDPZY0ws6peYx9kvLeNoKU8o5rO61T9h/J0QYmoNvzl9I7mZS57ANIWbVrnD0rGxPBGeMgO6JNKPxhHgc3jyLLzwJp3MpD6YoKSk2Zb4S8WSJlJ9fcOiEtUSnb5GpH9Cu/ZG4GzJyNShpFNvOvts8FTeve2Pi1Om5yFYLAgMBAAECggEAbkR86aaDi0LkpruPiZKAvuuFOsB/8grPSB30tlrnu6vvXoUorR+ODWKDtiRPijjVh+4Fjr6MZNj4zuXK8A/T3hmovztPOnwp3LZfrNyhiMYNCYNMUiQLZReUW6Y77c5h3lkGSZFksKjTMp1KweuGXqR5zu+W0NttKKrMDE5hdossO5x8tDqsihwvEKArKkyaijuoCaz1steO1ot9x3QIfliGVMmWJbUglGnpQURayOhHoOoPcSEHh5rSAgsq+bUivu7Nf32h0J8IKmGo8ct/sNHhWRF/umQ1ttZL1yvRwyhtvgwOjl2io81pLQZvDARPOkbGvurMTfvtRwumj/TDAQKBgQDT3s+1VJgy8DvcSCQZfTfxEIfGTbr5cZAkaz5oQc4FaeX3hGZCJ5olInXOV1/9Dli6WvVeOxXxBnLI28VGM4Nw2+S5BwqIhNjb5S1e1LI0GfbS9oyjjTx7dBzN1HCFt/AhgaNy6vXaT5caZdzmvTyaftI3sstH6iH3gHz9ea/diwKBgQDE426WxbyFdGWOnUPD0Fe9TPWIJY5yOC0gv2hRIHKCzOCh2NrM1Z9f/NS93MSDPrUlLOPOazUYkwGp6Y2zNAgUX8Lu6PCwsFfh/gKSw0puoq8y5wpKP4eSTHpXq9TfFQdg7IWdrlkRGOQp/3OTrVZ2HV0qcF88R4zWK1PAbbl5gQKBgCS1dzGIzbINs6FwEmkkK/wljBDMyPeNOJdGyZJQzHxdo82l7/3IVMnYVacBtozm3nKzbc382Mlg/6RTh7OpGz4Dq3ZvhOCwe85lv92tZo7mbMCSnT19LByLuq3i//f7LazwhDxV/3Dq11hoi8SixwxRquShotz78RSr+3wDiHgPAoGAbnmC4vy+xE9EcNpTXLs5kBFvr94K6JIW2EouWV8Qgc49uPK6CrdO/wgZwxLt/Qqhj+N5S6iiGVDbXSCcjtICOpRlB7MS4sF//mGNxhXoXN3R2okDIBg3lOJuv2S+RBJDmqH8czaR6WMDG4Gns1ROsGXXkemNtoAbSUz0aIPoz4ECgYAjwaNNDQbL8wxKfQbEtST0oTztwCU9A4f6Qa6cp0igZgTKjeOWKX9VC3aTo3LgyygxMXfUxV+nYM8D3cvqWkE3OL2MCNHXe+udMpEhtGgut/mUBxYcCR1/nHaJMCwrG00ZoNq1xtws2IvE/xC3yIILZn5SWQW84OGjXSNrcMMI6Q==";;
	// 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://127.0.0.1:8099/pay/notify_url.jsp";
	// 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
	public static String return_url = "http://127.0.0.1:8099/pay/return_url.jsp";
	// 请求网关地址
	public static String URL = "https://openapi.alipay.com/gateway.do";
	// 编码
	public static String CHARSET = "UTF-8";
	// 返回格式
	public static String FORMAT = "json";
	// 支付宝公钥
	public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnrdcu33h0s0JfevcnIPOY2Dk3iOHoEi6aKBF4u/Rk+83G0oVvOgZQkApuj9ZZzuedJx6KgOubqbf/ED1jkKV3e+e0+pwpEmMAHmO4hNUaOD5lT5H6yq7zOxOEpc+trVnU2vEYqB67Ywd5pDrbKqHV3wLD+Wj126eZR6zqyd7bDxgLf/zylobGGFvJimLfp7h7sDvPFqMkdgpiV+4ZkLlpbZ8LGOPqYTPmo/5GX/McwWmlthz3Xyhv5Ap9pS+cBDFMAzieUsLhlbB2jFsAn27QdXGtXVdf2KsA50rR1nCoLd/Tu0wQFg6qphagBXUXTcyIH4Co1OfcLzuZYZX5aV4cQIDAQAB";
	// 日志记录目录
	public static String log_path = "/log";
	// RSA2
	public static String SIGNTYPE = "RSA2";
}
