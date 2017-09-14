# 羽毛球场馆预定程序说明

## 程序编写相关技术栈
 - 面向对象的抽象和封装
 - 基于配置文件的动态加载
 - Java
 - Maven构建
 - Json数据传输，解析，持久化
 - 工厂设计模式
 - MVC模式借鉴

## 程序测试结果

    u001 2018-09-15 16:00~18:00 D
    > Success: the booking is accepted!
    u001 2018-09-15 16:00~18:00 D
    > Error: the booking conflicts with existing bookings!
    u001 2018-09-15 16:00~18:00 D C
    > Success: the booking is accepted!

    > 收入汇总
    > ---
    > 场地:A
    > 2017-09-17 12:00~14:00 违约金 25.0元
    > 2017-09-17 16:00~18:00 100元
    > 小计: 125.0元

    > 场地:B
    > 2017-09-18 09:00~13:00 140元
    > 2017-09-18 13:00~22:00 530元
    > 小计: 670.0元

    > 场地:C
    > 小计: 0.0元

    > 场地:D
    > 2018-09-15 16:00~18:00 违约金 25.0元
    > 2018-09-15 16:00~18:00 违约金 25.0元
    > 小计: 50.0元

    > ---
    > 总计: 845.0元

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
### 命令格式的校验？
- 主要构思如下
 - 首先编写正则工具类，包括命令长度，日期，时间**格式校验**
 - 其次，格式检验通过后，进行合法检查，比如
	 - 日期是否是将来
	 - 场馆是否存在
	 - 时间是否不再场馆开放时间内
	 - .....
 -  随后根据命令长度构建订单或者取消订单
	 - 订单过程中要注意
		 - 是否有场馆预定冲突
		 - 是否重复下单
	 - 取消订单要注意
		 - 订单是否存在

### 没有DB，程序数据的持久化？
在没有DB或者cache的情况下，伴随着程序的启动，数据会被清空丢失，所以要实现动态配置，并维护订单数据，就要进行非DB的数据传输和存储。
本程序经过考虑，暂时采用**Json**来进行**配置文件**和**数据文件**的**传输，解析**，并**持久化**订单数据，方便查看交易数据。
### 程序层次架构如何设计？

- 比较重要的点
	- 更合理的抽象
	- 动态配置，动态加载
	- 持久化数据传输方式的选择
	- 业务结构，设计模式的优化
