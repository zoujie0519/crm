package com.jensen.platform.crm.api.mapper.sys;

import com.jensen.platform.crm.api.common.bean.Mapper;
import com.jensen.platform.crm.api.entity.sys.SysOperateLog;

import java.util.List;

public interface SysOperateLogMapper extends Mapper<SysOperateLog> {

    Integer insertByBatch(List<SysOperateLog> list);
}