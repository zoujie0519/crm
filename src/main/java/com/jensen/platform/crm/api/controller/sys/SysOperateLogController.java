/*
 * All rights Reserved, Designed By www.jensen.com
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.controller.sys;

import com.jensen.platform.crm.api.common.aop.AnnotationLog;
import com.jensen.platform.crm.api.common.bean.Message;
import com.jensen.platform.crm.api.common.bean.ResponseModel;
import com.jensen.platform.crm.api.common.utils.ApplicationUtils;
import com.jensen.platform.crm.api.entity.sys.SysOperateLog;
import com.jensen.platform.crm.api.service.sys.SysOperateLogService;
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
 * @ClassName:  SysOperateLogController类
 * @Description: TODO
 * @author: Jensen
 * @date:  2020/10/18 16:29
 */
@RestController
@Api(tags = "SysOperateLog控制器")
@RequestMapping("/sysOperateLog")
public class SysOperateLogController {

    @Resource
    private SysOperateLogService sysOperateLogService;

    /**
     * @Title:  insert
     * @Description 添加数据
     * @Param sysOperateLog 添加的数据
     * @Return com.jensen.platform.crm.api.common.bean.ResponseModel<java.lang.Integer>
     * @Exception
     * @Author  Jensen
     * @Date  2020/10/18 16:22
     */
    @PostMapping("/insert")
	@ApiOperation("添加的数据")
    @AnnotationLog(desc = "添加的数据", path = "/sysOperateLog/insert")
    public ResponseModel<Integer> insert(SysOperateLog sysOperateLog) throws Exception{
		sysOperateLog.setId(ApplicationUtils.getUUID());
    	Integer state = sysOperateLogService.insert(sysOperateLog);
        return Message.success(state);
    }

    /**
     * @Title:  deleteById
     * @Description 根据Id删除数据
     * @Param id 主键
     * @Return com.jensen.platform.crm.api.common.bean.ResponseModel<java.lang.Integer>
     * @Exception
     * @Author  Jensen
     * @Date  2020/10/18 16:37
     */
    @PostMapping("/deleteById")
	@ApiOperation("根据Id删除数据")
    @AnnotationLog(desc = "根据Id删除数据", path = "/sysOperateLog/deleteById")
    public ResponseModel<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = sysOperateLogService.deleteById(id);
        return Message.success(state);
    }

    /**
     * @Title:  update
     * @Description 更新数据
     * @Param sysOperateLog 更新的数据
     * @Return com.jensen.platform.crm.api.common.bean.ResponseModel<java.lang.Integer>
     * @Exception
     * @Author  Jensen
     * @Date  2020/10/18 16:39
     */
    @PostMapping("/update")
	@ApiOperation("更新数据")
    @AnnotationLog(desc = "更新数据", path = "/sysOperateLog/update")
    public ResponseModel<Integer> update(SysOperateLog sysOperateLog) throws Exception {
        Integer state = sysOperateLogService.update(sysOperateLog);
        return Message.success(state);
    }

    /**
     * @Title:  selectById
     * @Description 根据Id查询数据
     * @Param id 主键
     * @Return com.jensen.platform.crm.api.common.bean.ResponseModel<com.jensen.platform.crm.api.entity.sys.SysOperateLog>
     * @Exception
     * @Author  Jensen
     * @Date  2020/10/18 16:39
     */
    @PostMapping("/selectById")
    @ApiOperation("根据主键获取数据")
    @AnnotationLog(desc = "根据主键获取数据", path = "/sysOperateLog/selectById")
    public ResponseModel<SysOperateLog> selectById(@RequestParam String id) throws Exception {
        SysOperateLog sysOperateLog = sysOperateLogService.selectById(id);
        return Message.success(sysOperateLog);
    }

    /**
     * @Title:  list
     * @Description 分页查询
     * @Param page 页码
     * @Param size 每页条数
     * @Return com.jensen.platform.crm.api.common.bean.ResponseModel<com.github.pagehelper.PageInfo<com.jensen.platform.crm.api.entity.sys.SysOperateLog>>
     * @Exception
     * @Author  Jensen
     * @Date  2020/10/18 16:40
     */
    @PostMapping("/list")
	@ApiOperation("分页获取数据")
    @AnnotationLog(desc = "分页获取数据", path = "/sysOperateLog/list")
    public ResponseModel<PageInfo<SysOperateLog>> list(@RequestParam(defaultValue = "0") Integer page,
					@RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<SysOperateLog> list = sysOperateLogService.selectAll();
        PageInfo<SysOperateLog> pageInfo = new PageInfo<SysOperateLog>(list);
        return Message.success(pageInfo);
    }
}