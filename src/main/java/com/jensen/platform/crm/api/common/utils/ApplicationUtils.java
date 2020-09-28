/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  ApplicationUtils.java
 * @Package com.jensen.platform.crm.api.common.utils
 * @author: Jensen
 * @date:   2020/9/28 10:45
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.common.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @ClassName:
 * @Description:(描述这个类的作用)
 * @author: jensen
 * @date:
 * @Copyright:
 */
public class ApplicationUtils {

    /**
     * 产生一个36个字符的UUID
     *
     * @return UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 产生一个32个字符的UUID
     *
     * @return UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * md5加密
     *
     * @param value 要加密的值
     * @return md5加密后的值
     */
    /*public static String md5Hex(String value) {
        return DigestUtils.md5Hex(value);
    }

    *//**
     * sha1加密
     *
     * @param value 要加密的值
     * @return sha1加密后的值
     *//*
    public static String sha1Hex(String value) {
        return DigestUtils.sha1Hex(value);
    }

    *//**
     * sha256加密
     *
     * @param value 要加密的值
     * @return sha256加密后的值
     *//*
    public static String sha256Hex(String value) {
        return DigestUtils.sha256Hex(value);
    }*/

    /**
     * 获取多少位随机数
     * @param num
     * @return
     */
    public static String getNumStringRandom(int num){
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        //随机生成数字，并添加到字符串
        for(int i = 0;i<num;i++){
            str.append(random.nextInt(10));
        }
        return  str.toString();
    }

    /**
     * 获取区间内的随机数
     * @param min
     * @param max
     * @return
     */
    public static int getRandomBetween(int min, int max){
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        return s;
    }
}
