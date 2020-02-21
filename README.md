1、系统管理后台基本框架SPPanAdmin，包括用户管理，角色管理，资源链接管理模块等
2、使用springboot、springdata jpa、shiro等服务端技术，使用freemarker模版渲染页面。
3、系统中对springdata的查询条件Specification做了简单的封装，更加方便查询条件的灵活使用。
4、前端技术：使用Hadmin系统模版，数据表格使用bootstrap table插件，弹窗使用layer插件，日期选择使用laydate插件。表单验证使用jQuery validate插件等等。
5、系统部署：
    1）使用mysql数据库，先建立一个空数据库studentbase，最好编码使用utf-8字符集，不然会乱码。
    2）把application.properties中的数据库连接信息修改成自己数据库的连接信息。
    3）系统时会自动创建表并且导入相关数据，修改spring.jpa.hibernate.ddl-auto默认为create，目的是让系统自动建表同时初始化相关集成数据。如果不需要自动初始化数据，可以修改/base2/src/main/resources/application.properties   18行 spring.jpa.hibernate.ddl-auto=update
6、系统启动后，访问：127.0.0.1/admin/会自动跳转到后台登录页面。
7、初始用户名和密码为：admin/111111。  教师：xujian/111111   学生： 10000/111111

除了成绩管理的查询 默认为姓名


成绩查询的信息为 学生id

    
权限可以及自己配置


注意事项：
	需要安装lombok插件：
		eclipse安装教程 ：https://jingyan.baidu.com/article/22fe7cede363d83002617f3a.html    安装完了记得clean项目
		idea安装教程 ： https://www.cnblogs.com/coderjinjian/p/9103755.html   
		