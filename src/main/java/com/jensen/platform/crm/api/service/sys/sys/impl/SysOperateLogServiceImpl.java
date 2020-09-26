/*
 * All rights Reserved, Designed By www.jensen.com
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.service.sys.sys.impl;

import com.jensen.platform.crm.api.entity.sys.SysOperateLog;
import com.jensen.platform.crm.api.mapper.sys.SysOperateLogMapper;
import com.jensen.platform.crm.api.service.sys.SysOperateLogService;
import com.jensen.platform.crm.api.common.bean.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @Description: SysOperateLogService接口实现类
* @author jensen
* @date 2020/09/19 10:48
*/
@Service
public class SysOperateLogServiceImpl extends AbstractService<SysOperateLog> implements SysOperateLogService {

    @Resource
    private SysOperateLogMapper sysOperateLogMapper;

    @Override
    public Integer insertByBatch(List<SysOperateLog> list) {
        return sysOperateLogMapper.insertByBatch(list);
    }
}