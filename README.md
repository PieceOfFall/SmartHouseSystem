<<<<<<< HEAD
#		智 能 家 居
=======
#		 智能家居   ( Vue3  +  SpringBoot  +  Node.js )
>>>>>>> bbb848c4e2fc2e02a7022debd05b3c3067e57dc7

---



##	项目简介

 ![image-20230213204222461](https://github.com/PieceOfFall/projectImg/blob/main/SmartHouseSystem/homepage4.jpg?raw=true) 



###		      技术栈 

```
前端:
	HTML
	CSS
	Less
	JavaScript
	TypeScript
	Vue3
	Vite
	vue-router
	Pinia
	Axios
	element-plus
	vue-echarts
	animate.css
	gsap

后端:
	Java8
	Spring5
	SpringMVC
	SpringBoot2
	Validation (JSR303)
	Mysql
	Mybatis
	Druid
	Swagger
	java-jwt
	pageHelper
	mail
	
虚拟传感器端:
	Node.js
	TypeScript
	Axios
	Cron
```
<<<<<<< HEAD


###	描述

>
> 此项目是一个 ```无物理传感器设备``` 的物联网实践项目，旨在模拟智能家居系统中对感知层数据的应用。
>
> 为了模拟物联网三层模型中感知层的数据采集，开发人员采用了开发简便的 Node.js 编写了虚拟的传感器网关，用于每秒随机生成伪造的不同传感器数据并上传给Java后端服务器。
>
> ---
>
> 
>
> 前端 采用了最新的Vue框架的3.0版本。
>
> ``Vue``组件化的开发模式 以及`` MVVM`` 的渲染模型让前端开发逻辑更为清晰透明，也避免了单个源码文件过于冗长导致的后期代码难以维护  ~~其实并没有进行太多维护~~。vue重点强调了数据驱动性，以便在视图和模型的变化时处理双向绑定。支持响应式系统，用于视图更新和重新渲染。
>
> 使用```TypeScript```代替``JavaScript``进行 数据处理 以及 逻辑控制。TypeScript 引入了类型系统，规范了对象以及其它变量的定义的使用。它可以帮助确保代码不会出现意料之外的错误，从而更容易调试和维护。与此同时，TypeScript 可以将 JavaScript 代码编译成不同的浏览器支持的版本，以便在所有的浏览器中正确的运行。
>
> ---
>
> 
>
> 后端 是经典的 SpringBoot 框架搭建的Web服务器，
>
> **控制器层面** 采用了``JSR303``验证用户输入，减小了因为输入参数缺失导致的空指针异常等业务问题；
>
> **服务层面** 引入了```拦截器```、```JWT```等进行鉴权。增加了全局异常处理组件，在开发环境中便于追踪错误的同事，减小了生产环境中的出错损失。引入了```Swagger```作为后端接口文档，在项目启动时自动为前端生成API调用说明  ~~避免了前后端开发者间因沟通不足而大打出手~~。
>
> **数据库层面** 利用ORM框架```MyBatis```进行数据库对象关系映射，其编辑xml的方式便捷了数据库记录与Java对象之间数据类型的转化，其动态sql的使用使得sql语句得以拼接复用。引入了```Druid数据库连接池```，通过池化技术使得复数个连接在程序运行时直接建立并由连持池进行维护，减少了程序建立以及释放数据库连接的 ~~高昂的~~ 代价。
>
> 因此，本项目基本架构为 **Vue3** + **SpringBoot2** + **Node.js** 
>
> ![login](https://github.com/PieceOfFall/projectImg/blob/main/SmartHouseSystem/login.jpg?raw=true)

##	项目运行

###			环境需求

- JDK8以上       [Java安装教程](https://blog.csdn.net/web15870359587/article/details/123949106)
- Maven            [Maven安装教程](https://blog.csdn.net/qq_40625778/article/details/125229241)
- Mysql8以上    [Mysql安装教程](https://zhuanlan.zhihu.com/p/46905335?ivk_sa=1024320u)
- Node.js          [Node.js安装教程](https://blog.csdn.net/qq_57210034/article/details/124823972)
=======
>>>>>>> bbb848c4e2fc2e02a7022debd05b3c3067e57dc7
