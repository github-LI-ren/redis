package com.jedis;

import redis.clients.jedis.Jedis;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.Random;


public class PhoneCode {
    public static void main(String[] args) {
        verifyCode("xiaomi");
    }
    public static void getRedisCode(String phone,String code){
        Jedis localhost = new Jedis("localhost", 6379);
        String codekey=phone+"code";
        String rediscode = localhost.get(codekey);
        if (rediscode.equals(code)){
            System.out.println("成功");
        }else {
            System.out.println("失败");
        }
        localhost.close();
    }
//    每个手机只能发送三次,验证码放到redis中,设置过期时间
    public static void verifyCode(String phone){
        Jedis localhost = new Jedis("localhost", 6379);
        String countkey=phone+"count";
        String codekey=phone+"code";
        String s = localhost.get(countkey);
        if (s == null){
//            第一次发送
            localhost.setex(countkey,24*60*60, "1");
        }else if (Integer.parseInt(s)<=2){
            localhost.incr(countkey);
        }else if (Integer.parseInt(s)>2){
            System.out.println("今天发送次数超过三次");
            localhost.close();
            return;
        }

        String code1 = getCode();
        localhost.setex(codekey,120,code1);
        localhost.close();
    }
    //生成6位验证码
    public static String getCode(){
        Random random = new Random();
        StringBuffer code=new StringBuffer();
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }
}

