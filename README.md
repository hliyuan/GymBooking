# 羽毛球场馆预定程序说明


----------
## 程序的运行方式


## 程序的架构设计
体育馆订票系统架构如下：

    ├── README.md
    ├── pom.xml
    ├── src
    │   ├── main
    │   │   ├── java
    │   │   │   ├── Starter.java
    │   │   │   ├── dao
    │   │   │   │   ├── OrderDao.java
    │   │   │   │   └── impl
    │   │   │   │       └── OrderDaoImpl.java
    │   │   │   ├── factory
    │   │   │   │   └── DaoFactory.java
    │   │   │   ├── pojo
    │   │   │   │   ├── Area.java
    │   │   │   │   ├── Config.java
    │   │   │   │   ├── FeeStandard.java
    │   │   │   │   ├── Gyms.java
    │   │   │   │   ├── Order.java
    │   │   │   │   ├── SegmentCost.java
    │   │   │   │   └── User.java
    │   │   │   └── utils
    │   │   │       ├── Const.java
    │   │   │       ├── DateKit.java
    │   │   │       ├── JsonUtils.java
    │   │   │       ├── OrderComparator.java
    │   │   │       ├── OrderKit.java
    │   │   │       └── PatternKit.java
    │   │   └── resources
    │   │       ├── booking.ini
    │   │       ├── config.json
    │   │       └── data
    │   │           ├── A.json
    │   │           └── B.json
    │   └── test
    │       └── java
    │           └── JsonTest.java
大体上来说，主要分为三层：

- 业务实现层
	- dao层
		- 实现订单数据的持久化，**提供curd接口**
	- facotry层
		- 采用**工厂设计模式**，隐藏对底层的封装细节
	- pojo层
		- 对系统的交互实体进行**抽象，面向对象**的封装
	- utils层
		- 提供一些工具类，包括日期，正则，Json，比较器等
- 资源配置层
	- data
		- 存放**订单**的数据，进行**持久化**
	- booking.ini
		- 提供各dao的配置，供factory加载
	- config.json
		- 程序的配置文件，可以进行程序参数的动态修改和读取，包括**价格变动，场馆变动，开放时间等**
- 测试层
## 程序的结果展示
## 需要思考的问题
### 命令格式的校验？

### 没有DB，程序数据的持久化？
### 程序层次架构如何设计？
##
