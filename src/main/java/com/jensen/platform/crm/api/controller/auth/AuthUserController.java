/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  AuthUserController.java
 * @Package com.jensen.platform.crm.api.controller.auth
 * @author: jensen
 * @date:   2020/10/18 17:16
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.controller.auth;

import com.jensen.platform.crm.api.common.aop.AnnotationLog;
import com.jensen.platform.crm.api.common.bean.Message;
import com.jensen.platform.crm.api.common.bean.ResponseModel;
import com.jensen.platform.crm.api.entity.auth.AuthUser;
import com.jensen.platform.crm.api.service.auth.AuthUserService;
import com.jensen.platform.crm.api.pojo.vo.auth.AuthUserVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* @Description: AuthUserController类
* @author jensen
* @date 2020/10/18 17:16
*/
@Api(tags = "AuthUser控制器")
@RestController
@RequestMapping("/authUser")
public class AuthUserController {

    @Resource
    private AuthUserService authUserService;

    /**
    * @Title:  insert
    * @Description 添加数据
    * @Param authUser 添加的数据
    * @Return com.jensen.platform.crm.api.common.bean.ResponseModel<java.lang.Integer>
    * @Author  jensen
    * @Date  2020/10/18 17:16
    */
    @PostMapping("/insert")
	@ApiOperation("添加的数据")
    @AnnotationLog(desc = "添加的数据", path = "/authUser/insert")
    public ResponseModel<Integer> insert(AuthUserVO model) {
        AuthUser authUser = new AuthUser();
        BeanUtils.copyProperties(model, authUser);
        authUser.builder();
        Integer state = authUserService.insert(authUser);
        return Message.success(state);
    }

    /**
    * @Title:  deleteById
    * @Description 根据Id删除数据
    * @Param id 主键
    * @Return com.jensen.platform.crm.api.common.bean.ResponseModel<java.lang.Integer>
    * @Author  jensen
    * @Date  2020/10/18 17:16
    */
    @PostMapping("/deleteById")
	@ApiOperation("根据Id删除数据")
    @AnnotationLog(desc = "根据Id删除数据", path = "/authUser/deleteById")
    public ResponseModel<Integer> deleteById(@RequestParam String id) {
        Integer state = authUserService.deleteById(id);
        return Message.success(state);
    }

    /**
    * @Title:  update
    * @Description 更新数据
    * @Param authUser 更新的数据
    * @Return com.jensen.platform.crm.api.common.bean.ResponseModel<java.lang.Integer>
    * @Author  jensen
    * @Date  2020/10/18 17:16
    */
    @PostMapping("/update")
	@ApiOperation("更新数据")
    @AnnotationLog(desc = "更新数据", path = "/authUser/update")
    public ResponseModel<Integer> update(AuthUser authUser) {
        Integer state = authUserService.update(authUser);
        return Message.success(state);
    }

    /**
    * @Title:  selectById
    * @Description 根据Id查询数据
    * @Param id 主键
    * @Return com.jensen.platform.crm.api.common.bean.ResponseModel<AuthUser>
    * @Author  jensen
    * @Date  2020/10/18 17:16
    */
    @PostMapping("/selectById")
	@ApiOperation("根据主键获取数据")
    @AnnotationLog(desc = "根据主键获取数据", path = "/authUser/selectById")
    public ResponseModel<AuthUser> selectById(@RequestParam String id) {
        AuthUser authUser = authUserService.selectById(id);
        return Message.success(authUser);
    }

    /**
    * @Title:  list
    * @Description 分页获取数据
    * @Param page 页码
    * @Param size 每页条数
    * @Return com.jensen.platform.crm.api.common.bean.ResponseModel<PageInfo<AuthUser>>
    * @Author  jensen
    * @Date  2020/10/18 17:16
    */
    @PostMapping("/list")
	@ApiOperation("分页获取数据")
    @AnnotationLog(desc = "分页获取数据", path = "/authUser/list")
    public ResponseModel<PageInfo<AuthUser>> list(@RequestParam(defaultValue = "0") Integer page,
					@RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<AuthUser> list = authUserService.selectAll();
        PageInfo<AuthUser> pageInfo = new PageInfo<AuthUser>(list);
        return Message.success(pageInfo);
    }
}