/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  SystemLogQueue.java
 * @Package com.jensen.platform.crm.api.common.queue
 * @author: Jensen
 * @date:   2020/9/28 10:42
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.common.queue;

import com.jensen.platform.crm.api.entity.sys.SysOperateLog;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName:  SystemLogQueue
 * @Description: 日志队列
 * @author: Jensen
 * @date:  2020/9/28 10:42
 */ 
@Component
public class SystemLogQueue {

    /** 消息队列 */
    private BlockingQueue<SysOperateLog> blockingQueue = new LinkedBlockingQueue<>();

    /***
     * @Title:  add
     * @Description 添加消息到队列
     * @Author  Jensen
     * @Date  2020/9/28 10:43
     * @param sysOperateLog
     * @Return
     * @Exception
    */
    public void add(SysOperateLog sysOperateLog) {
        blockingQueue.add(sysOperateLog);
    }

    /***
     * @Title:  poll
     * @Description 从队列中拉取值
     * @Author  Jensen
     * @Date  2020/9/28 10:43
     * @param
     * @Return {@link com.jensen.platform.crm.api.entity.sys.SysOperateLog}
     * @Exception
    */
    public SysOperateLog poll() throws InterruptedException {
        return blockingQueue.poll(1, TimeUnit.SECONDS);
    }
}
