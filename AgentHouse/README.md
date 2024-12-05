1. 持久层框架

   - 使用mybatis-plus 
   - 可以使用common工程中AutoGen自动生成DO、Mapper、Service等代码
   - service和mapper有基础的增删改查方法 
   - 乐观锁统一使用mybatis-plus @Version 注解

2. 对象拷贝

   - 使用MapStruct

3. 定时任务

   - 使用xxl-job

4. VO/DTO/DO

   - 对外请求响应统一使用VO（字段修改和接口文档同步），如果DTO和VO字段一致可以复用，否则重新定义DTO 
   - 枚举字段VO中使用String+枚举验证，DTO中使用枚举

5. SpringCloud支持

   - Api中需要加Feign的相关注解, 减少后期项目改SpringCloud的改动

6. 事务使用编程式事务，不使用声明式事务(@Transactional)

7. 缓存同步更新

   - redis操作放在事务内(最后面)，如果redis操作失败，事务回滚；如果事务提交失败，则记录到失败记录表中

8. 代码格式化

   - 使用alibaba代码格式标准（https://github.com/alibaba/p3c/blob/master/p3c-formatter/eclipse-codestyle.xml）
   - IDEA插件安装见 https://www.cnblogs.com/barry-blog/articles/13447580.html 步骤1-3 
   - alibaba代码格式化配置文件：dev-support/code-style/alibaba_codestyle.xml