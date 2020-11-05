/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  ${modelNameUpperCamel}Controller.java
 * @Package ${basePackageController}
 * @author: ${author}
 * @date:   ${date}
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package ${basePackageController};

import ${basePackage}.common.aop.AnnotationLog;
import ${basePackage}.common.bean.Message;
import ${basePackage}.common.bean.ResponseModel;
import ${basePackageModel}.${modelNameUpperCamel};
import ${basePackageService}.${modelNameUpperCamel}Service;
import ${basePackagePojoViewObject}.${modelNameUpperCamel}DTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
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
@RequestMapping("/${modelName}/${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {

    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    /**
    * @Title:  insert
    * @Description 添加数据
    * @Param ${modelNameLowerCamel} 添加的数据
    * @Return ${basePackage}.common.bean.ResponseModel<java.lang.Integer>
    * @Author  ${author}
    * @Date  ${date}
    */
    @PostMapping("/insert")
	@ApiOperation("添加的数据")
    @AnnotationLog(desc = "添加的数据", path = "/${baseRequestMapping}/insert")
    public ResponseModel<Integer> insert(${modelNameUpperCamel}DTO model) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = new ${modelNameUpperCamel}();
        BeanUtils.copyProperties(model, ${modelNameLowerCamel});
        ${modelNameLowerCamel}.builder();
        Integer state = authUserService.insert(${modelNameLowerCamel});
        return Message.success(state);
    }

    /**
    * @Title:  deleteById
    * @Description 根据Id删除数据
    * @Param id 主键
    * @Return ${basePackage}.common.bean.ResponseModel<java.lang.Integer>
    * @Author  ${author}
    * @Date  ${date}
    */
    @PostMapping("/deleteById")
	@ApiOperation("根据Id删除数据")
    @AnnotationLog(desc = "根据Id删除数据", path = "/${baseRequestMapping}/deleteById") 
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "主键", required = true, paramType = "query")})
    public ResponseModel<Integer> deleteById(@RequestParam String id) {
        Integer state = ${modelNameLowerCamel}Service.deleteById(id);
        return Message.success(state);
    }

    /**
    * @Title:  update
    * @Description 更新数据
    * @Param ${modelNameLowerCamel} 更新的数据
    * @Return ${basePackage}.common.bean.ResponseModel<java.lang.Integer>
    * @Author  ${author}
    * @Date  ${date}
    */
    @PostMapping("/update")
	@ApiOperation("更新数据")
    @AnnotationLog(desc = "更新数据", path = "/${baseRequestMapping}/update")
    public ResponseModel<Integer> update(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        Integer state = ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return Message.success(state);
    }

    /**
    * @Title:  selectById
    * @Description 根据Id查询数据
    * @Param id 主键
    * @Return ${basePackage}.common.bean.ResponseModel<${modelNameUpperCamel}>
    * @Author  ${author}
    * @Date  ${date}
    */
    @PostMapping("/selectById")
	@ApiOperation("根据主键获取数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "主键", required = true, paramType = "query")})
    @AnnotationLog(desc = "根据主键获取数据", path = "/${baseRequestMapping}/selectById")
    public ResponseModel<${modelNameUpperCamel}> selectById(@RequestParam String id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.selectById(id);
        return Message.success(${modelNameLowerCamel});
    }

    /**
    * @Title:  list
    * @Description 分页获取数据
    * @Param page 页码
    * @Param size 每页条数
    * @Return ${basePackage}.common.bean.ResponseModel<PageInfo<${modelNameUpperCamel}>>
    * @Author  ${author}
    * @Date  ${date}
    */
    @PostMapping("/list")
	@ApiOperation("分页获取数据")
    @AnnotationLog(desc = "分页获取数据", path = "/${baseRequestMapping}/list")
            @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页条数", paramType = "query")
    })
    public ResponseModel<PageInfo<${modelNameUpperCamel}>> list(@RequestParam(defaultValue = "0") Integer page,
					@RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.selectAll();
        PageInfo<${modelNameUpperCamel}> pageInfo = new PageInfo<${modelNameUpperCamel}>(list);
        return Message.success(pageInfo);
    }
}