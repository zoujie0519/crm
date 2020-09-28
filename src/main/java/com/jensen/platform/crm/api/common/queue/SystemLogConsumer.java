/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  SystemLogConsumer.java
 * @Package com.jensen.platform.crm.api.common.queue
 * @author: Jensen
 * @date:   2020/9/28 10:41
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.common.queue;

import com.jensen.platform.crm.api.entity.sys.SysOperateLog;
import com.jensen.platform.crm.api.service.sys.SysOperateLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:  SystemLogConsumer
 * @Description: 系统日志队列消费者
 * @author: Jensen
 * @date:  2020/9/28 10:41
 */
@Component
public class SystemLogConsumer implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(SystemLogConsumer.class);

    public static final int DEFAULT_BATCH_SIZE = 64;

    private SystemLogQueue auditLogQueue;

    private SysOperateLogService sysOperateLogService;

    private int batchSize = DEFAULT_BATCH_SIZE;

    private boolean active = true;

    private Thread thread;

    /***
     * @Title:  init
     * @Description 初始化启动线程
     * @Author  Jensen
     * @Date  2020/9/28 10:41
     * @param
     * @Return
     * @Exception
    */
    @PostConstruct
    public void init() {
        thread = new Thread(this);
        thread.start();
    }

    @PreDestroy
    public void close() {
        active = false;
    }

    @Override
    public void run() {
        while (active) {
            execute();
        }
    }

    /***
     * @Title:  execute
     * @Description 执行线程
     * @Author  Jensen
     * @Date  2020/9/28 10:41
     * @param
     * @Return
     * @Exception
    */
    public void execute() {
        List<SysOperateLog> sysOperateLogs = new ArrayList<>();
        try {
            int size = 0;
            while (size < batchSize) {
                SysOperateLog sysOperateLog = auditLogQueue.poll();
                if (sysOperateLog == null) {
                    break;
                }
                sysOperateLogs.add(sysOperateLog);
                size++;
            }
        } catch (Exception ex) {
            logger.info(ex.getMessage(), ex);
        }

        if (!sysOperateLogs.isEmpty()) {
            try {
                //Thread.sleep(10000);
                sysOperateLogService.insertByBatch(sysOperateLogs);
            }catch (Exception e){
                logger.error("异常信息:{}", e.getMessage());
            }
        }
    }

    @Resource
    public void setAuditLogQueue(SystemLogQueue auditLogQueue) {
        this.auditLogQueue = auditLogQueue;
    }

    @Resource
    public void setSysOperateLogService(SysOperateLogService sysOperateLogService) {
        this.sysOperateLogService = sysOperateLogService;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }
}
