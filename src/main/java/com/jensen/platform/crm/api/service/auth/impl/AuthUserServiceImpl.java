/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  AuthUserServiceImpl.java
 * @Package com.jensen.platform.crm.api.service.auth.impl
 * @author: jensen
 * @date:   2020/10/18 17:16
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.service.auth.impl;

import com.jensen.platform.crm.api.mapper.auth.AuthUserMapper;
import com.jensen.platform.crm.api.entity.auth.AuthUser;
import com.jensen.platform.crm.api.service.auth.AuthUserService;
import com.jensen.platform.crm.api.common.bean.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @Description: AuthUserService接口实现类
* @author jensen
* @date 2020/10/18 17:16
*/
@Service
public class AuthUserServiceImpl extends AbstractService<AuthUser> implements AuthUserService {

    @Resource
    private AuthUserMapper authUserMapper;

}