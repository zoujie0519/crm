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

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

/**
 * @Description: 项目工具类
 * @author jensen
 * @date 2020/10/18 17:16
 */
public class ApplicationUtils {

    private ApplicationUtils() {}

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
     * 获取多少位随机数
     * @param num
     * @return
     */
    public static String getNumStringRandom(int num) throws NoSuchAlgorithmException {
        StringBuilder str = new StringBuilder();
        Random random = SecureRandom.getInstanceStrong();
        // 随机生成数字，并添加到字符串
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
    public static int getRandomBetween(int min, int max) throws NoSuchAlgorithmException {
        Random random = SecureRandom.getInstanceStrong();
        return random.nextInt(max)%(max-min+1) + min;
    }
}
