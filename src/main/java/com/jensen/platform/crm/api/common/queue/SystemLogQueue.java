/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:
 * @Package
 * @Description: todo
 * @author: jensen
 * @date:
 * @version V1.0
 * @Copyright:
 */
package com.jensen.platform.crm.api.common.queue;

import com.jensen.platform.crm.api.entity.sys.SysOperateLog;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName:
 * @Description:(日志队列)
 * @author: jensen
 * @date:
 * @Copyright:
 */
@Component
public class SystemLogQueue {

    private BlockingQueue<SysOperateLog> blockingQueue = new LinkedBlockingQueue<>();

    public void add(SysOperateLog sysOperateLog) {
        blockingQueue.add(sysOperateLog);
    }

    public SysOperateLog poll() throws InterruptedException {
        return blockingQueue.poll(1, TimeUnit.SECONDS);
    }
}
