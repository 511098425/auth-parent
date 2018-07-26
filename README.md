**springboot OAuth2.0 认证**


使用框架：

   springboot 2.0.3
   
   springcloud Finchley.RELEASE
   
   阿里druid数据源
   
   mybatis通用mapper
   
   lombok


模块说明：
   
   auth-server  用户权限认证中心
   
   auth-resource 资源认证中心
   
   auth-code-client 授权码认证客户端
   
   auth-common 通用模块
   
   

**`认证方式  是否支持  是否实现`**

password    是    否

authorization_code   是  是

client_credentials   是  否

                                                                                                                      

备注：目前只实现了授权码模式客户端，后续会更新password模式、client模块两个客户端，当然现在这两种模式也可以在postman中调试




使用说明：

1.创建数据库执行sql目录下的脚本

2.修改auth-server中resource目录的application.yml的数据源部分

3.分别启动auth-server、auth-resource、auth-code-client三个模块

4.启动完成后在浏览器访问地址 http://localhost:6060/ui/user  会跳转到登录，输入用户名密码登录后会返回所有用户信息，目前没有过滤敏感字段，如果想加上同意授权的界面，将oauth_client_details表中的autoapprove值改为false

**password模式调试说明：**

![Image text](https://github.com/511098425/auth-parent/blob/master/postman-shootscreen/20180726114711.png)

获取到的token：

![Image text](https://github.com/511098425/auth-parent/blob/master/postman-shootscreen/20180726134513.png)
