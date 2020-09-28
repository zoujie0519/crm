/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  Service.java
 * @Package com.jensen.platform.crm.api.common.bean
 * @author: Jensen
 * @date:   2020/9/28 10:15
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.common.bean;

import org.apache.ibatis.exceptions.TooManyResultsException;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * @ClassName:  Service
 * @Description: Service层基础接口，其他Service接口请继承该接口
 * @author: Jensen
 * @date:  2020/9/28 10:15
 */ 
public interface Service<T> {

    /**
     * @Title:  insert
     * @Description 持久化
     * @Author  Jensen
     * @Date  2020/9/28 10:19
     * @param model
     * @Return {@link java.lang.Integer}
     * @Exception
    */
    Integer insert(T model);

    /**
     * @Title:  deleteById
     * @Description 通过主鍵刪除
     * @Author  Jensen
     * @Date  2020/9/28 10:19
     * @param id
     * @Return {@link java.lang.Integer}
     * @Exception
    */
    Integer deleteById(String id);

    /**
     * @Title:  deleteByIds
     * @Description 批量刪除
     * @Author  Jensen
     * @Date  2020/9/28 10:18
     * @param ids ids -> “1,2,3,4”
     * @Return {@link java.lang.Integer}
     * @Exception
    */
    Integer deleteByIds(String ids);

    /**
     * @Title:  update
     * @Description 更新
     * @Author  Jensen
     * @Date  2020/9/28 10:18
     * @param model
     * @Return {@link java.lang.Integer}
     * @Exception
    */
    Integer update(T model);

    /**
     * @Title:  selectById
     * @Description 通过ID查找
     * @Author  Jensen
     * @Date  2020/9/28 10:18
     * @param id
     * @Return {@link T}
     * @Exception
    */
    T selectById(String id);

    /**
     * @Title:  selectBy
     * @Description 通过Model中某个成员变量名称（非数据表中column的名称）查找,value需符合unique约束
     * @Author  Jensen
     * @Date  2020/9/28 10:17
     * @param fieldName
     * @param value
     * @Return {@link T}
     * @Exception  TooManyResultsException
    */
    T selectBy(String fieldName, Object value) throws TooManyResultsException;

    /**
     * @Title:  selectListBy
     * @Description 通过Model中某个成员变量名称（非数据表中column的名称）查找
     * @Author  Jensen
     * @Date  2020/9/28 10:17
     * @param fieldName javabean定义的属性名，不是数据库里的属性名
     * @param value
     * @Return {@link java.util.List<T>}
     * @Exception
    */
    List<T> selectListBy(String fieldName, Object value);

    /**
     * @Title:  selectListByIds
     * @Description 通过多个ID查找
     * @Author  Jensen
     * @Date  2020/9/28 10:16
     * @param ids ids -> “1,2,3,4”
     * @Return {@link java.util.List<T>}
     * @Exception
    */
    List<T> selectListByIds(String ids);

    /**
     * @Title:  selectByCondition
     * @Description 根据条件查找
     * @Author  Jensen
     * @Date  2020/9/28 10:16
     * @param condition
     * @Return {@link java.util.List<T>}
     * @Exception
    */
    List<T> selectByCondition(Condition condition);

    /**
     * @Title:  selectAll
     * @Description 获取所有
     * @Author  Jensen
     * @Date  2020/9/28 10:16
     * @param
     * @Return {@link java.util.List<T>}
     * @Exception
    */
    List<T> selectAll();

    /**
     * @Title:  select
     * @Description 根据实体中的属性值进行查询，查询条件使用等号
     * @Author  Jensen
     * @Date  2020/9/28 10:16
     * @param record
     * @Return {@link java.util.List<T>}
     * @Exception
    */
    List<T> select(T record);

    /**
     * @Title:  selectOne
     * @Description 根据实体中的属性值进行查询，查询条件使用等号
     * @Author  Jensen
     * @Date  2020/9/28 10:16
     * @param record
     * @Return {@link T}
     * @Exception
    */
    T selectOne(T record);
}
