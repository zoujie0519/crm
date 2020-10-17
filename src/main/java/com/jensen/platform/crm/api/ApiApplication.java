/*
 * All rights Reserved, Designed By www.jensen.com
 * @title:  ApiApplication.java
 * @package com.jensen.platform.crm.api
 * @author  Jensen
 * @date    2020/10/17 18:19
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName:  ApiApplication
 * @Description: 项目启动类
 * @author: Jensen
 * @date:  2020/10/17 18:19
 */
@SpringBootApplication
public class ApiApplication {

	/**
	 * @Title:  main
	 * @Description 启动入口函数
	 * @Author  Jensen
	 * @Date  2020/10/17 18:20
	 * @Param args
	 * @Return void
	 * @Exception 
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
