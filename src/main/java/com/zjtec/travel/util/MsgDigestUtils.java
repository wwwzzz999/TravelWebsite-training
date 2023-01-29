package com.zjtec.travel.util;

import com.alibaba.druid.sql.visitor.functions.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MsgDigestUtils {
    /**
     * SHA256 加密
     * @param str 明文
     * @return 密文
     */
    public static String encodeSHA256(String str){
        MessageDigest messageDigest;
        String encdeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
            encdeStr = byte2Hex(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encdeStr;
    }

   public static String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length()==1){
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }


   public static String enpass(String pas, String sa){
       String salt=sa;
       String pass=pas;
        for (int i=0;i<3;i++){
            pass+=salt;
            pass=MsgDigestUtils.encodeSHA256(pass);

        }
        return pass;
   }


    public static void main(String[] args) {
      //  System.out.println(MsgDigestUtils.encodeSHA256(123456+"qqwer"));

        String salt="11sfdsfs";
        String res="111232132111";
//        for (int i=0; i<3; i++){
//            res+=salt;
//            res=MsgDigestUtils.encodeSHA256(res);
//
//        }
        //System.out.println(res);
       // System.out.println(res);
       // System.out.println(MsgDigestUtils.enpass("111232132111","11sfdsfs"));
        System.out.println(MsgDigestUtils.enpass("123456","qqwer"));
    }

}
