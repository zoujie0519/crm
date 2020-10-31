/*
 * All rights Reserved, Designed By www.jensen.com
 * @title:  JWTTokenUtils.java
 * @package com.jensen.platform.crm.api.common.security
 * @author: Jensen
 * @date:   2020/10/31 19:04
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.common.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: JWTTokenUtils
 * @Description: JWT工具类
 * JWT是由三段组成的，分别是header（头）、payload（负载）和signature（签名）
 * 其中header中放{ "alg": "HS512", "typ": "JWT" } 表明使用的加密算法，和token的类型==>默认是JWT
 * @Author: Jensen
 * @Date: 2020/9/29 9:53
 * @Version: V1.0
 **/
public class JWTTokenUtils {

    /** token键名 */
    public static final String TOKEN_HEADER = "token";

    /** token 前缀 */
    public static final String TOKEN_PREFIX = "Bearer ";

    /** 密钥，用于signature（签名）部分解密 */
    private static final String PRIMARY_KEY = "jwtsecretdemo";

    /** 签发者 */
    private static final String ISS = "Gent.Ni";

    /** 添加角色的key */
    private static final String ROLE_CLAIMS = "role";

    /** 过期时间是3600秒，既是1个小时 */
    private static final long EXPIRATION = 3600L;

    /** 选择了记住我之后的过期时间为7天 */
    private static final long EXPIRATION_REMEMBER = 604800L;

    /**
     * @Title:  createToken
     * @Description 创建Token
     * @Author  Jensen
     * @Date  2020/9/29 9:54
     * @param username
     * @param roles
     * @param isRememberMe
     * @Return {@link java.lang.String}
     * @Exception
    */
    public static String createToken(String username, List<String> roles, boolean isRememberMe) {
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        HashMap<String, Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, roles);
        return Jwts.builder()
                //采用HS512算法对JWT进行的签名,PRIMARY_KEY是我们的密钥
                .signWith(SignatureAlgorithm.HS512, PRIMARY_KEY)
                //设置角色名
                .setClaims(map)
                //设置发证人
                .setIssuer(ISS)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }

    /**
     * @Title:  getUsername
     * @Description 从token中获取用户名
     * @Author  Jensen
     * @Date  2020/9/29 9:57
     * @param token
     * @Return {@link java.lang.String}
     * @Exception
    */
    public static String getUsername(String token){
        return getTokenBody(token).getSubject();
    }

    /**
     * @Title:  getUserRole
     * @Description 获取用户角色
     * @Author  Jensen
     * @Date  2020/9/29 9:57
     * @param token
     * @Return {@link java.util.List<java.lang.String>}
     * @Exception
    */
    public static List<String> getUserRole(String token){
        return (List<String>) getTokenBody(token).get(ROLE_CLAIMS);
    }

    /**
     * @Title:  isExpiration
     * @Description 判断Token是否过期
     * @Author  Jensen
     * @Date  2020/9/29 9:57
     * @param token
     * @Return {@link boolean}
     * @Exception
    */
    public static boolean isExpiration(String token){
        return getTokenBody(token).getExpiration().before(new Date());
    }

    /**
     * @Title:  getTokenBody
     * @Description 获取token中的数据
     * @Author  Jensen
     * @Date  2020/9/29 9:57
     * @param token
     * @Return {@link io.jsonwebtoken.Claims}
     * @Exception
    */
    private static Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(PRIMARY_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
