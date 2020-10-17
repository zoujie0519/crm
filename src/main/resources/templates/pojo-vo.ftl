/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  ${modelNameUpperCamel}Controller.java
 * @Package ${basePackagePojoViewObject}
 * @author: Jensen
 * @date:   ${date} 10:44
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package ${basePackagePojoViewObject};

import java.io.Serializable;

/**
* @Description: ${modelNameUpperCamel}VO类
* @author ${author}
* @date ${date}
*/
public class ${modelNameUpperCamel}VO implements Serializable {

<#list fields as field>
    /** 属性${field.name} */
    private ${field.type.shortName} ${field.name};

</#list>

<#list fields as field>
    public ${field.type.shortName} get${field.name?cap_first}() {
        return ${field.name};
    }

    public void set${field.name?cap_first}(${field.type.shortName} ${field.name}) {
        this.${field.name} = ${field.name} == null ? null : ${field.name}.trim();
    }

</#list>
}