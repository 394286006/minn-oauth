# minn-oauth

  新增一篇入门配置文档
   原文地址： http://www.i84.xyz/多语言版基础管理系统展示es6版-入门配置篇
   
   
   写这篇文章的目的是想要提供一个可以让想要学习，借鉴或者使用这个多语言系统的个人或团体提供一个简单的入门文档。这是一个由传统的单应用架构根据技术的发展应用转为微服务架构的系统，简单的时候可以灵活的转回单应用架构

个人或团体应掌握或了解相关的基础知识主要包括但不限如下：nodejs，java，spring cloud，react，mysql，hadoop，spark，mybatis，redis，mongo，rabbitmq，zookeeper，ignite,dubbo，tensorflow等。

为了尽量的简单入门，这里介绍的当然也是最简单的入门启动流程，上面的开源技术不会全部用到，介绍的也是最简单的启动方式

1.下载相关的工程代码：git库

A. 后端必须[根据依赖可再自行下载]：minn-zuul，minn-config-server, minn-oauth , minn-admin

B. 前端：      minn-admin-web

C.国际化文件：minn-localization



2. 后台启动：

a. 数据库导入：minn-pojo  /src/main/resources/sql/sysbase.sql

b.   启动配置好的redis

c.   minn-eureka-server

d. minn-oauth

e. minn-admin

f.   minn-zuul

下图是在eureka注册的微服务：

http://i84.xyz/wp-content/uploads/2018/10/Screen-Shot-2018-10-25-at-8.44.48-AM.png
eureka
3.前端启动：minn-admin-web  :  npm run dev

登陆用户名： minn

密码：123456

效果参考：
http://www.i84.xyz/多语言版基础管理系统展示es6版-系统主界面风格3/

