# wx-tools & wx-hack

基于原有的项目进行部分修改和增强:

1. 修改源代码的警告
2. 去除不必要的泛型代码
3. 增加HTTP浏览器操作方式，以便个人订阅号可以绕过微信API的限制

---

### 注意： 有兴趣可以看源码或进群讨论交流学习~

Wx-tools是基于微信公众平台API的轻量级框架。
基于Wx-tools你可以开速开发一个订阅号/服务号的web应用后台。

## 特性：
* 统一、简单的API，可以快速上手。
* 链式赋值编程，更加容易理解和使用。
* 对于微信服务器发来的消息，提供匹配器（Matcher），拦截器（interceptor），处理器（Handler）接口，定制实现，具有可扩展性。

## 超级详细的实例教程，基于SpringBoot+Wx-tools
* [音乐爬虫推送公众号](https://blog.csdn.net/antgan/article/details/80288061)

## w3cschool官方文档
* [wx-tools文档](https://www.w3cschool.cn/wxtools/)

## 讨论：
* BUG反馈及建议：https://github.com/antgan/wx-tools/issues
* 微信开发交流QQ群：570937047

## 博主有话说：
* 哟哟哟，终于有时间更新啦！这次教程写得浅显易懂。希望喜欢的给个star咯！！略略略~

## maven依赖
```
<dependency>
  <groupId>com.soecode.wx-tools</groupId>
  <artifactId>wx-tools</artifactId>
  <version>2.1.4-RELEASE</version>
</dependency>
```

