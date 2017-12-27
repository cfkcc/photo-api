package com.photo.api.common.util;

import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;

public class AlipayUtil {
	public static final String ALIPAY_APPID = "2017120700424973"; // appid

    public static String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC2Jg14iueUT7rSuL1HpLV3owq6M6/eUsI3N/m48UbTZm2kTHi1X1oo2QpSGWOct3GGPLRppT1/OtPvUeWrAg1+14CtQQPcFER8BehkN70vj6YjTlZer0f8L0vHR+lQHsRK+bKM4Y6aNMpkfZuFzDXT0JkDZNVR1hpo9YcRFK5FgMmkpCoCwAHy72gNKIQc90lAnQJLBkHihjfKQLiAcvO4ovZJGofucFcCz3TCGhyp1eTbl9NdqJwwV9Mjim/SVFGzm9yrumhmsK4YCRIAhEls3rU4WcWbsIQmuCknWUotGBcqGkEY56P7ObQRa9KpiHiZjkgdDWHu6ptdYTnUP6aTAgMBAAECggEAWJ8fAr0ieyhnj0rO3OKoX/L1ry6irJq7X75eQ7swaH53be6+9A/Wf/VJbjRoIMGn/FXguV3qE9L9dnz0L0WqZ0JWmvT8+cDqqST+snPkGhXcEWV15E+HyI0CY4z7WT3+5zOhyaWcii6RIl9yoaB/t/TP3bTbjRuMcfCzrH3RZIK3ClPZc1A82orJNrbXtHHBPrubK+UDqqj/1pAlH1PEsPvLcmQdIuTxpB3f74VM/PKk8/SWIO7LGQEg/VN8nLC4djkvrxRLdZkACrpPab45qx7YVTpX3H1KbXxVAdYVKWSFtLEFMoHGRpxQwaXCzzvQYY3rA6EJzMb/U7YoWQkocQKBgQDyo0eCMGaZXXUg0MMhVevScssERS2/WI6WYAEAkO0K10lsUj312u0RI+u8xU/mIHnVVlBEp17tas2ROSgPtlFh/bRzlyhWi+FsnwCbpjXjRvmS5taltl9rs2kldZdNi9n6Zc6lbxrcdhppOe9rAkhSXF69S4ZfTU3dH61R4o+pHQKBgQDALf5q6EkwfWUNd1NVjVbrW/vi4+IdmyCKT+5oR7O8nt55Gk2qUINx/MKDEVloNy6KDF9ag5JfmF6MwSlfcxxxJ/omZIUCyz8Y2amYYKbYySDjJhaNl4bEcu7oed60hWsD+xOiL7FxmEYTWS0DsHJEWfEXWtDo/Urh9m3Q3D0vbwKBgQC7QzEpVVmZ+/st/HDdBYcfwODWxt1r1FkXzbh4S2HnAAj2xPJ1ESXuGJ8T7eSgY2gkMp14nwQwkEFfV5GHrtpYmonuvDaxtPuh7YV2m2zzoA92RRpcgshaQvlfo8XGX604B+LY40sD41K267PQDs7qYcfsqKFoZC03//6wN/9QMQKBgE26jJua5fdZ0GR47di9ePlq0986AnutAaVPnQLKdXUF4L+kluMa+39AF0BB5SKUpxso0+xdKPfHPf66Jcy4VzmhTLj4kR/HKvcXGlRKS1GFT+rvvdF5BwYv4lde62eiSt1qUM+ZcLCcQUOUHvPOCvmAU1X54mNKB16LeHEwkwK7AoGBANx1WTcqySl9zrsgYpT7DDTocCjgXIHpyWH/dJOezRi1hb9VqouUo2R6oc4jyVNrrdNlqa8eZer/0vt5Vau8Gbq3juJEj0Qq3Oq/bNRiD9G4dCHp8hZaHqxAcUhwvQ/tgTogIimOPlGBmwgqUEXny/NTR+GyzZlqhspS0ym2bkoH"; // app支付私钥

    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApWaynAZ6lu7mF4kMljs5fUQCl4xnyPDCq9JiL4h2ryRY/c5Bk2QRqqn2aBAjBDdSVhCDs8uVA4rCbHuXs8rTjSVfGP/fSvweegDwoIVZxkj2BNvaPRc4bxDLAhuvtTxYobs2kdlEudkyPdc1lMkk9IqLImqFDyRjbDdYZse+aMwW5rVS0jriq/bs6quR2WTA2uQTpYQyk9KcQvqAzv9oaMOiTf6g6bDyVlN3apDsiZvIXaqeVs+7PcJU7fpPMMp6ISV8SmkO3ZMFQZ6P7nPTC7BRYKOmbJyu1zu0QJdr4VsRAOQWg0aeZKPoIdp5QPP5kZTpmdghSGhKKL6N6/T7iwIDAQAB"; // 支付宝公钥

    // 统一收单交易创建接口
    private static AlipayClient alipayClient = null;

    public static AlipayClient getAlipayClient() {
        if (alipayClient == null) {
            synchronized (AlipayUtil.class) {
                if (null == alipayClient) {
                    alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", ALIPAY_APPID,
                            APP_PRIVATE_KEY, AlipayConstants.FORMAT_JSON, AlipayConstants.CHARSET_UTF8,
                            ALIPAY_PUBLIC_KEY);
                }
            }
        }
        return alipayClient;
    }
}
