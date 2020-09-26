/*
 * All rights Reserved, Designed By www.jensen.com
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.controller.auth;

import com.jensen.platform.crm.api.common.aop.AnnotationLog;
import com.jensen.platform.crm.api.common.bean.Message;
import com.jensen.platform.crm.api.common.bean.ResponseModel;
import com.jensen.platform.crm.api.common.utils.ApplicationUtils;
import com.jensen.platform.crm.api.entity.auth.AuthUser;
import com.jensen.platform.crm.api.service.auth.AuthUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* @Description: AuthUserController类
* @author jensen
* @date 2020/09/21 15:55
*/
@Api(tags = "AuthUser控制器")
@RestController
@RequestMapping("/authUser")
public class AuthUserController {

    @Resource
    private AuthUserService authUserService;

    /**
	* @Description: 添加数据
	* @param authUser 添加的数据
	* @Reutrn ResponseModel<Integer>>
	*/
    @PostMapping("/insert")
	@ApiOperation("添加的数据")
    @AnnotationLog(desc = "添加的数据", path = "/authUser/insert")
    public ResponseModel<Integer> insert(AuthUser authUser) throws Exception{
		authUser.setId(ApplicationUtils.getUUID());
    	Integer state = authUserService.insert(authUser);
        return Message.success(state);
    }

    /**
	* @Description: 根据Id删除数据
	* @param id 主键
	* @Reutrn ResponseModel<Integer>>
	*/
    @PostMapping("/deleteById")
	@ApiOperation("根据Id删除数据")
    @AnnotationLog(desc = "根据Id删除数据", path = "/authUser/deleteById")
    public ResponseModel<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = authUserService.deleteById(id);
        return Message.success(state);
    }

    /**
	* @Description: 更新数据
	* @param authUser 更新的数据
	* @Reutrn ResponseModel<AuthUser>
	*/
    @PostMapping("/update")
	@ApiOperation("更新数据")
    @AnnotationLog(desc = "更新数据", path = "/authUser/update")
    public ResponseModel<Integer> update(AuthUser authUser) throws Exception {
        Integer state = authUserService.update(authUser);
        return Message.success(state);
    }

    /**
	* @Description: 根据Id查询数据
	* @param id 主键
	* @Reutrn ResponseModel<AuthUser>
	*/
    @PostMapping("/selectById")
	@ApiOperation("根据主键获取数据")
    @AnnotationLog(desc = "根据主键获取数据", path = "/authUser/selectById")
    public ResponseModel<AuthUser> selectById(@RequestParam String id) throws Exception {
        AuthUser authUser = authUserService.selectById(id);
        return Message.success(authUser);
    }

    /**
	* @Description: 分页查询
	* @param page 页码
	* @param size 每页条数
	* @Reutrn ResponseModel<PageInfo<AuthUser>>
	*/
    @PostMapping("/list")
	@ApiOperation("分页获取数据")
    @AnnotationLog(desc = "分页获取数据", path = "/authUser/list")
    public ResponseModel<PageInfo<AuthUser>> list(@RequestParam(defaultValue = "0") Integer page,
					@RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<AuthUser> list = authUserService.selectAll();
        PageInfo<AuthUser> pageInfo = new PageInfo<AuthUser>(list);
        return Message.success(pageInfo);
    }
}