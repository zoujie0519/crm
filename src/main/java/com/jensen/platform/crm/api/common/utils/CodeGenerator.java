/*
 * All rights Reserved, Designed By www.jensen.com
 * @title:  CodeGenerator.java
 * @package com.jensen.platform.crm.api.common.utils
 * @author: Jensen
 * @date:   2020/10/25 10:56
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.CaseFormat;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @className:  CodeGenerator
 * @description: 代码生成器，根据数据表名称生成对应的Model、Mapper、Service、Controller简化开发。
 * @author: Jensen
 * @date:  2020/10/25 10:56
 */
@Log4j2
public class CodeGenerator {

    // JDBC配置，请修改为你项目的实际配置
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/yidao?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=true";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "Aa123456";
    private static final String JDBC_DIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    // 模板位置
    private static final String TEMPLATE_FILE_PATH = "src/main/resources/templates";
    private static final String JAVA_PATH = "src/main/java"; // java文件路径
    private static final String RESOURCES_PATH = "src/main/resources";// 资源文件路径

    // 项目基础包名称，根据自己公司的项目修改
    public static final String BASE_PACKAGE = "com.jensen.platform.crm.api";

    // Model所在包
    public static String MODEL_PACKAGE = BASE_PACKAGE + ".entity";

    // Mapper所在包
    public static String MAPPER_PACKAGE = BASE_PACKAGE + ".mapper";

    // Service所在包
    public static String SERVICE_PACKAGE = BASE_PACKAGE + ".service";

    // ServiceImpl所在包
    public static String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";

    // Controller所在包
    public static String CONTROLLER_PACKAGE = BASE_PACKAGE + ".controller";

    // viewObject所在包
    public static String VIEW_OBJECT_PACKAGE = BASE_PACKAGE + ".pojo.vo";

    // Mapper xml所在包
    public static String SQL_MAP_PACKAGE = "mapper";

    // Mapper插件基础接口的完全限定名
    public static final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".common.bean.Mapper";

    // Entity插件基础接口的完全限定名
    public static final String ENTITY_INTERFACE_REFERENCE = BASE_PACKAGE + ".common.bean.Entity";

    // @author
    private static final String AUTHOR = "jensen";

    // @date
    private static final String DATE = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date());

    private static Map<String, Object> templateData = new HashMap<>();

    /**
     * @Title:  main
     * @Description 生成代码入口
     * @Author  Jensen
     * @Date  2020/9/26 16:58
     * @param args
     * @Return
     * @Exception
    */
    public static void main(String[] args) {
        genCode("auth", "auth_user");
    }

    /**
     * 通过数据表名称生成代码，下划线转大驼峰的形式。 如输入表名称 "t_user_detail" 将生成
     * TUserDetail、TUserDetailMapper、TUserDetailService ...
     * @param modelName 模块名称，为空默认不按模块管理
     * @param tableNames 数据表名称...
     */
    public static void genCode(String modelName, String... tableNames) {
        for (String tableName : tableNames) {
            genCode(modelName, tableName);
        }
    }

    /**
     * 通过数据表名称生成代码 如输入表名称 "user_info"
     * 将生成 UserInfo、UserInfoMapper、UserInfoService ...
     *
     * @param tableName 数据表名称
     */
    public static void genCode(String modelName, String tableName) {
        initTemplateData(modelName, tableName);
        genModelAndMapper(tableName);
        genService(tableName);
        genController(tableName);
    }

    /**
     * @Title:  genModelAndMapper
     * @Description 生成实体文件、映射文件
     * @Author  Jensen
     * @Date  2020/9/26 16:58
     * @param tableName
     * @Return
     * @Exception
    */
    public static void genModelAndMapper(String tableName) {
        Context context = getContext();

        JDBCConnectionConfiguration jdbcConnectionConfiguration = getJDBCConnectionConfiguration();
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

        PluginConfiguration pluginConfiguration = getPluginConfiguration();
        context.addPluginConfiguration(pluginConfiguration);

        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = getJavaModelGeneratorConfiguration();
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = getSqlMapGeneratorConfiguration();
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = getJavaClientGeneratorConfiguration();
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);

        TableConfiguration tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setTableName(tableName);
        tableConfiguration.setDomainObjectName(null);
        tableConfiguration.addProperty("useActualColumnNames","true");
        context.addTableConfiguration(tableConfiguration);

        List<String> warnings;
        MyBatisGenerator generator;
        try {
            Configuration config = new Configuration();
            config.addContext(context);
            config.validate();
            DefaultShellCallback callback = new DefaultShellCallback(true);
            warnings = new ArrayList<>();
            generator = new MyBatisGenerator(config, callback, warnings);
            generator.generate(null);

            List<GeneratedJavaFile> generatedJavaFiles = generator.getGeneratedJavaFiles();
            if (generatedJavaFiles.isEmpty() || generator.getGeneratedXmlFiles().isEmpty()) {
                log.error("生成Model和Mapper失败：", warnings);
            }

            if(!generatedJavaFiles.isEmpty()) {
                GeneratedJavaFile generatedJavaFile = generatedJavaFiles.get(0);
                TopLevelClass topLevelClass = (TopLevelClass)generatedJavaFile.getCompilationUnit();

                if(topLevelClass != null) {
                    List<Field> fields = topLevelClass.getFields();
                    if(fields != null && !fields.isEmpty()) {
                        genViewObject(tableName, fields);
                    }
                }
            }

            String modelName = tableNameConvertUpperCamel(tableName);
            log.info("{}.java 生成成功", modelName);
            log.info("{}Mapper.java 生成成功", modelName);
            log.info("{}Mapper.xml 生成成功", modelName);
        } catch (Exception e) {
            log.error("生成Model和Mapper失败", e);
        }
    }

    /**
     * @Title:  genService
     * @Description 生成service文件
     * @Author  Jensen
     * @Date  2020/9/26 16:59
     * @param tableName
     * @Return
     * @Exception
    */
    public static void genService(String tableName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();
            String modelNameUpperCamel = tableNameConvertUpperCamel(tableName);

            File file = new File(JAVA_PATH + packageConvertPath(SERVICE_PACKAGE) + modelNameUpperCamel + "Service.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("service.ftl").process(templateData, new FileWriter(file));
            log.info("{}Service.java 生成成功", modelNameUpperCamel);

            File file1 = new File(JAVA_PATH + packageConvertPath(SERVICE_IMPL_PACKAGE) + modelNameUpperCamel + "ServiceImpl.java");
            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }
            cfg.getTemplate("service-impl.ftl").process(templateData, new FileWriter(file1));
            log.info("{}ServiceImpl.java 生成成功", modelNameUpperCamel);
        } catch (Exception e) {
            log.error("生成Service失败", e);
        }
    }

    private static void genViewObject(String tableName, List<Field> fields) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();
            String modelNameUpperCamel = tableNameConvertUpperCamel(tableName);
            log.info("fields: {}" , JSONObject.toJSONString(fields));
            templateData.put("fields", fields);

            File file = new File(JAVA_PATH + packageConvertPath(VIEW_OBJECT_PACKAGE) + modelNameUpperCamel + "VO.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("pojo-vo.ftl").process(templateData, new FileWriter(file));

            log.info("{}VO.java 生成成功", modelNameUpperCamel);
        } catch (Exception e) {
            log.error("生成ViewObject失败", e);
        }
    }

    /**
     * @Title:  genController
     * @Description 生成控制器文件
     * @Author  Jensen
     * @Date  2020/9/26 16:59
     * @param tableName
     * @Return
     * @Exception
    */
    public static void genController(String tableName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();
            String modelNameUpperCamel = tableNameConvertUpperCamel(tableName);
            File file = new File(JAVA_PATH + packageConvertPath(CONTROLLER_PACKAGE) + modelNameUpperCamel + "Controller.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("controller.ftl").process(templateData, new FileWriter(file));

            log.info("{}Controller.java 生成成功", modelNameUpperCamel);
        } catch (Exception e) {
            log.error("生成Controller失败", e);
        }
    }

    /**
     * @Title:  getContext
     * @Description 获取上下文
     * @Author  Jensen
     * @Date  2020/9/26 17:00
     * @param
     * @Return {@link org.mybatis.generator.config.Context}
     * @Exception
    */
    private static Context getContext() {
        Context context = new Context(ModelType.FLAT);
        context.setId("Potato");
        context.setTargetRuntime("MyBatis3Simple");
        context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER, "`");
        context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, "`");
        return context;
    }

    /**
     * @Title:  getJDBCConnectionConfiguration
     * @Description 获取数据库连接
     * @Author  Jensen
     * @Date  2020/9/26 17:00
     * @param
     * @Return {@link org.mybatis.generator.config.JDBCConnectionConfiguration}
     * @Exception
    */
    private static JDBCConnectionConfiguration getJDBCConnectionConfiguration() {
        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setConnectionURL(JDBC_URL);
        jdbcConnectionConfiguration.setUserId(JDBC_USERNAME);
        jdbcConnectionConfiguration.setPassword(JDBC_PASSWORD);
        jdbcConnectionConfiguration.setDriverClass(JDBC_DIVER_CLASS_NAME);
        return jdbcConnectionConfiguration;
    }

    /**
     * @Title:  getPluginConfiguration
     * @Description 获取插件配置
     * @Author  Jensen
     * @Date  2020/9/26 17:00
     * @param
     * @Return {@link org.mybatis.generator.config.PluginConfiguration}
     * @Exception
    */
    private static PluginConfiguration getPluginConfiguration() {
        PluginConfiguration pluginConfiguration = new PluginConfiguration();
        pluginConfiguration.setConfigurationType("tk.mybatis.mapper.generator.MapperPlugin");
        pluginConfiguration.addProperty("mappers", MAPPER_INTERFACE_REFERENCE);
        return pluginConfiguration;
    }

    /**
     * @Title:  getJavaModelGeneratorConfiguration
     * @Description 获取模型生成配置
     * @Author  Jensen
     * @Date  2020/9/26 17:00
     * @param
     * @Return {@link org.mybatis.generator.config.JavaModelGeneratorConfiguration}
     * @Exception
    */
    private static JavaModelGeneratorConfiguration getJavaModelGeneratorConfiguration() {
        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        javaModelGeneratorConfiguration.setTargetProject(JAVA_PATH);
        javaModelGeneratorConfiguration.setTargetPackage(MODEL_PACKAGE);
        javaModelGeneratorConfiguration.addProperty("rootClass", ENTITY_INTERFACE_REFERENCE);
        javaModelGeneratorConfiguration.addProperty("enableSubPackages", "true");
        javaModelGeneratorConfiguration.addProperty("trimStrings", "true");
        return javaModelGeneratorConfiguration;
    }

    /**
     * @Title:  getSqlMapGeneratorConfiguration
     * @Description 获取数据库映射文件配置
     * @Author  Jensen
     * @Date  2020/9/26 17:01
     * @param
     * @Return {@link org.mybatis.generator.config.SqlMapGeneratorConfiguration}
     * @Exception
    */
    private static SqlMapGeneratorConfiguration getSqlMapGeneratorConfiguration() {
        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        sqlMapGeneratorConfiguration.setTargetProject(RESOURCES_PATH);
        sqlMapGeneratorConfiguration.setTargetPackage(SQL_MAP_PACKAGE);
        return sqlMapGeneratorConfiguration;
    }

    /**
     * @Title:  getJavaClientGeneratorConfiguration
     * @Description 获取数据模型类配置
     * @Author  Jensen
     * @Date  2020/9/26 17:01
     * @param
     * @Return {@link org.mybatis.generator.config.JavaClientGeneratorConfiguration}
     * @Exception
    */
    private static JavaClientGeneratorConfiguration getJavaClientGeneratorConfiguration() {
        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        javaClientGeneratorConfiguration.setTargetProject(JAVA_PATH);
        javaClientGeneratorConfiguration.setTargetPackage(MAPPER_PACKAGE);
        javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
        return javaClientGeneratorConfiguration;
    }

    /**
     * @Title:  getConfiguration
     * @Description 获取freemarker模板文件
     * @Author  Jensen
     * @Date  2020/9/26 17:01
     * @param
     * @Return {@link freemarker.template.Configuration}
     * @Exception
    */
    private static freemarker.template.Configuration getConfiguration() throws IOException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    /**
     * @title:  initTemplateData
     * @description 模板所需要的参数
     * @param modelName: 模块名称
     * @param tableName: 表名称
     * @return void
     * @author  Jensen
     * @date  2020/10/25 10:49
     */
    private static void initTemplateData(String modelName, String tableName) {
        if(StringUtils.isNotBlank(modelName)) {
            MODEL_PACKAGE = MODEL_PACKAGE + "." + modelName;
            MAPPER_PACKAGE = MAPPER_PACKAGE + "." + modelName;
            SERVICE_PACKAGE = SERVICE_PACKAGE + "." + modelName;
            SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";
            CONTROLLER_PACKAGE = CONTROLLER_PACKAGE + "." + modelName;
            SQL_MAP_PACKAGE = SQL_MAP_PACKAGE + "." + modelName;
            VIEW_OBJECT_PACKAGE = VIEW_OBJECT_PACKAGE + "." + modelName;
        }

        String modelNameUpperCamel = tableNameConvertUpperCamel(tableName);
        templateData.clear();
        templateData.put("date", DATE);
        templateData.put("author", AUTHOR);
        templateData.put("basePackage", BASE_PACKAGE);
        templateData.put("modelName", modelName);
        templateData.put("modelNameUpperCamel", modelNameUpperCamel);
        templateData.put("modelNameLowerCamel", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel));
        templateData.put("basePackageModel", MODEL_PACKAGE);
        templateData.put("basePackagePojoViewObject", VIEW_OBJECT_PACKAGE);
        templateData.put("basePackageController", CONTROLLER_PACKAGE);
        templateData.put("basePackageService", SERVICE_PACKAGE);
        templateData.put("basePackageServiceImpl", SERVICE_IMPL_PACKAGE);
        templateData.put("basePackageDao", MAPPER_PACKAGE);
        templateData.put("baseRequestMapping", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel));
    }

    /**
     * @Title:  tableNameConvertUpperCamel
     * @Description 表名小驼峰
     * @Author  Jensen
     * @Date  2020/9/26 17:02
     * @param tableName
     * @Return {@link java.lang.String}
     * @Exception
    */
    private static String tableNameConvertUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());
    }

    /**
     * @Title:  packageConvertPath
     * @Description 包名称转文件路径
     * @Author  Jensen
     * @Date  2020/9/26 17:02
     * @param packageName
     * @Return {@link java.lang.String}
     * @Exception
    */
    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }
}
