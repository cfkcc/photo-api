package com.photo.api.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class GenerateCodeUtil {
    private static Random random= new Random();
    private static String[] chars36 = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

    public static String generateCode32(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
    public static String generateCode16() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = generateCode32();
        int x = 0;
        for (int i = 0; i < 16; i++)
        {
            String str = uuid.substring(i * 2, i * 2 + 2);
            x = Integer.parseInt(str, 16);
            shortBuffer.append(chars36[x % 36]);
        }
        return shortBuffer.toString();
    }


    /**
     * 获取多少位的随机数
     * @param len
     * @return
     */
    public static String getRandomNum(int len){
        return String.format("%0"+len+"d",(random.nextInt(new Double(Math.pow(10,len)).intValue())));
    }

    public static String createOrderNo(){
        Date dateTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        StringBuffer sb = new StringBuffer(sdf.format(dateTime));
        sb.append(getRandomNum(4));
        return sb.toString();
    }
    
    public static void main(String[] args) {
		String no = GenerateCodeUtil.createOrderNo();
		System.out.println(" NO. = "+no);
	}

}
