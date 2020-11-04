/*
 * All rights Reserved, Designed By www.jensen.com
 * @title: ResubmitLock
 * @projectName api
 * @author Jensen
 * @date 2020/11/1 9:34
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.common.lock;

import com.jensen.platform.crm.api.common.constant.Constant;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ResubmitLock
 * @Description: 重复提交锁
 * @author: Jensen
 * @date: 2020/11/1 9:34
 */
public class ResubmitLock {

    /** 本地緩存 */
    private static final ConcurrentHashMap LOCK_CACHE = new ConcurrentHashMap<>(200);

    /** 线程池 */
    private static final ScheduledThreadPoolExecutor EXECUTOR = new ScheduledThreadPoolExecutor(5, new ThreadPoolExecutor.DiscardPolicy());

    private ResubmitLock() {
    }

    /**
     * @className:  ResubmitLock
     * @description: 静态内部类 单例模式
     * @author: Jensen
     * @date:  2020/11/1 9:44
     */
    private static class SingletonInstance {
        private static final ResubmitLock INSTANCE = new ResubmitLock();
    }

    /**
     * @title:  getInstance
     * @description 获得单例对象
     * @return com.jensen.platform.crm.api.common.lock.ResubmitLock
     * @exception
     * @author  Jensen
     * @date  2020/11/1 9:44
     */
    public static ResubmitLock getInstance() {
        return SingletonInstance.INSTANCE;
    }


    /**
     * @title:  handleKey
     * @description 计算哈希值
     * @param param: 明文参数
     * @return java.lang.String
     * @exception
     * @author  Jensen
     * @date  2020/11/1 9:42
     */
    public static String handleKey(String param) throws UnsupportedEncodingException {
        return DigestUtils.md5DigestAsHex((param == null ? "" : param).getBytes(Constant.UTF_8));
    }

    /**
     * @title:  lock
     * @description 加锁 putIfAbsent 是原子操作保证线程安全
     * @param key: 对应的key
     * @param value:
     * @return boolean
     * @exception
     * @author  Jensen
     * @date  2020/11/1 9:41
     */
    public boolean lock(final String key, Object value) {
        return Objects.isNull(LOCK_CACHE.putIfAbsent(key, value));
    }

    /**
     * @title:  unLock
     * @description 延时释放锁 用以控制短时间内的重复提交
     * @param lock: 是否需要解锁
     * @param key: 对应的key
     * @param delaySeconds: 延时时间
     * @return void
     * @exception
     * @author  Jensen
     * @date  2020/11/1 9:41
     */
    public void unLock(final boolean lock, final String key, final int delaySeconds) {
        if (lock) {
            EXECUTOR.schedule(() -> {
                LOCK_CACHE.remove(key);
            }, delaySeconds, TimeUnit.SECONDS);
        }
    }
}
