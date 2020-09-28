/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  ${modelNameUpperCamel}Controller.java
 * @Package ${basePackageController}
 * @author: Jensen
 * @date:   ${date} 10:44
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package ${basePackageController};

import ${basePackage}.common.aop.AnnotationLog;
import ${basePackage}.common.bean.Message;
import ${basePackage}.common.bean.ResponseModel;
import ${basePackage}.common.utils.ApplicationUtils;
import ${basePackageModel}.${modelNameUpperCamel};
import ${basePackageService}.${modelNameUpperCamel}Service;
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
* @Description: ${modelNameUpperCamel}Controller类
* @author ${author}
* @date ${date}
*/
@Api(tags = "${modelNameUpperCamel}控制器")
@RestController
@RequestMapping("/${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {

    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    /**
	* @Description: 添加数据
	* @param ${modelNameLowerCamel} 添加的数据
	* @Reutrn ResponseModel<Integer>>
	*/
    @PostMapping("/insert")
	@ApiOperation("添加的数据")
    @AnnotationLog(desc = "添加的数据", path = "/${baseRequestMapping}/insert")
    public ResponseModel<Integer> insert(${modelNameUpperCamel} ${modelNameLowerCamel}) throws Exception{
		${modelNameLowerCamel}.setId(ApplicationUtils.getUUID());
    	Integer state = ${modelNameLowerCamel}Service.insert(${modelNameLowerCamel});
        return Message.success(state);
    }

    /**
	* @Description: 根据Id删除数据
	* @param id 主键
	* @Reutrn ResponseModel<Integer>>
	*/
    @PostMapping("/deleteById")
	@ApiOperation("根据Id删除数据")
    @AnnotationLog(desc = "根据Id删除数据", path = "/${baseRequestMapping}/deleteById")
    public ResponseModel<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = ${modelNameLowerCamel}Service.deleteById(id);
        return Message.success(state);
    }

    /**
	* @Description: 更新数据
	* @param ${modelNameLowerCamel} 更新的数据
	* @Reutrn ResponseModel<${modelNameUpperCamel}>
	*/
    @PostMapping("/update")
	@ApiOperation("更新数据")
    @AnnotationLog(desc = "更新数据", path = "/${baseRequestMapping}/update")
    public ResponseModel<Integer> update(${modelNameUpperCamel} ${modelNameLowerCamel}) throws Exception {
        Integer state = ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return Message.success(state);
    }

    /**
	* @Description: 根据Id查询数据
	* @param id 主键
	* @Reutrn ResponseModel<${modelNameUpperCamel}>
	*/
    @PostMapping("/selectById")
	@ApiOperation("根据主键获取数据")
    @AnnotationLog(desc = "根据主键获取数据", path = "/${baseRequestMapping}/selectById")
    public ResponseModel<${modelNameUpperCamel}> selectById(@RequestParam String id) throws Exception {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.selectById(id);
        return Message.success(${modelNameLowerCamel});
    }

    /**
	* @Description: 分页查询
	* @param page 页码
	* @param size 每页条数
	* @Reutrn ResponseModel<PageInfo<${modelNameUpperCamel}>>
	*/
    @PostMapping("/list")
	@ApiOperation("分页获取数据")
    @AnnotationLog(desc = "分页获取数据", path = "/${baseRequestMapping}/list")
    public ResponseModel<PageInfo<${modelNameUpperCamel}>> list(@RequestParam(defaultValue = "0") Integer page,
					@RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.selectAll();
        PageInfo<${modelNameUpperCamel}> pageInfo = new PageInfo<${modelNameUpperCamel}>(list);
        return Message.success(pageInfo);
    }
}