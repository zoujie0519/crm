/*
 * All rights Reserved, Designed By www.jensen.com
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.service.sys;

import com.jensen.platform.crm.api.common.bean.Service;
import com.jensen.platform.crm.api.entity.sys.SysOperateLog;

import java.util.List;

/**
* @Description: SysOperateLogService接口
* @author jensen
* @date 2020/09/19 10:48
*/
public interface SysOperateLogService extends Service<SysOperateLog> {

    Integer insertByBatch(List<SysOperateLog> list);
}