package com.agent.common.generate;


import com.agent.common.model.BaseDO;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成
 *
 * @author lll
 */
public class AutoClientGen {

    private static final String DATE_TIME = "datetime";
    private static final String MYSQL_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    public static void main(String[] args) {

        // 目标表名
        String tableName = "t_chat";
        // 对应类名前缀
        String prefix = "Chat";
        // 作者名称
        String authorName = "lll";
        // 数据库配置
        String url = "jdbc:mysql://127.0.0.1:3306/agent_house?&useSSL=false&serverTimezone=GMT%2B8";
        String username = "root";
        String password = "root";
        // 初始化生成器
        AutoGenerator autoGenerator = autoGeneratorInit(authorName, tableName, url, username, password);
        // 执行
        generateForService(autoGenerator, prefix);
        System.out.println("auto generate success!");
    }

    /**
     * 初始化代码生成器引擎
     */
    private static AutoGenerator autoGeneratorInit(String authorName, String tableName, String url, String username,
                                                   String password) {
        AutoGenerator mpg = new AutoGenerator();
        // 选择 freemarker 引擎，默认 Veloctiy
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setAuthor(authorName);
        // 是否覆盖
        gc.setFileOverride(true);
        // 不需要ActiveRecord特性的请改为false
        gc.setActiveRecord(false);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columnList
        gc.setBaseColumnList(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                System.out.println("转换类型：" + fieldType);
                // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
                if (DATE_TIME.equals(fieldType)) {
                    globalConfig.setDateType(DateType.ONLY_DATE);
                }
                return super.processTypeConvert(globalConfig, fieldType);
            }
        });
        dsc.setDriverName(MYSQL_DRIVER_NAME);
        dsc.setUsername(username);
        dsc.setPassword(password);
        dsc.setUrl(url);
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setEntityLombokModel(true);
        strategy.setEntitySerialVersionUID(false);
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 需要生成的表
        strategy.setInclude(tableName);
        // 是否生成实体时，生成字段注解
        strategy.setEntityTableFieldAnnotationEnable(true);
        // 策略配置公共字段
        mpg.setStrategy(strategy);

        // 模板参数配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>(20);
                this.setMap(map);
            }
        };
        mpg.setCfg(cfg);

        // 配置模块生成
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
        TemplateConfig tc = new TemplateConfig();
        // 关闭默认 xml 生成，
        tc.setXml(null);
        tc.setController(null);
        tc.setEntity(null);
        tc.setMapper(null);
        tc.setService(null);
        tc.setServiceImpl(null);
        mpg.setTemplate(tc);

        return mpg;
    }

    /**
     * 生成service里面的代码 AutoGenerator 里面做通用配置，此方法中做特殊配置
     */
    public static void generateForService(AutoGenerator mpg, String prefix) {
        String filePathPrefix = System.getProperty("user.dir") + "\\agent-provider";
        String classPath = filePathPrefix + "\\src\\main\\java";
        mpg.getGlobalConfig().setOutputDir(classPath);
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        mpg.getGlobalConfig().setMapperName(prefix + "Mapper");
        mpg.getGlobalConfig().setXmlName(prefix + "Mapper");
        mpg.getGlobalConfig().setEntityName(prefix + "DO");

        // 策略配置特殊
        // 自定义实体父类
        mpg.getStrategy().setSuperEntityClass(BaseDO.class);
        // 自定义 mapper 父类
        mpg.getStrategy().setSuperMapperClass("com.agent.common.mapper.BaseMapperX");
        // 自定义公共字段
        mpg.getStrategy().setSuperEntityColumns("id", "create_time", "update_time", "version");

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.agent.provider");
        pc.setController("controller");
        pc.setEntity("model.db");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 模块生成模板指定
        mpg.getTemplate().setEntity("/templates/entity.java");
        mpg.getTemplate().setMapper("/templates/mapper.java");

        // 自定义XML
        List<FileOutConfig> focList = new ArrayList<>();
        String xmlPath = filePathPrefix + "\\src\\main\\resources\\mapper\\";
        // 调整 xml 生成目录演示
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return xmlPath + prefix + "Mapper.xml";
            }
        });
        mpg.getCfg().setFileOutConfigList(focList);

        // 执行生成
        mpg.execute();
    }
}
