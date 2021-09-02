# dispatcher
该项目问接受ESB报文请求转发给模型工厂的前置，项目使用springboot形式启动，功能完好。其中接受请求处理比较具体，有时间可以改造成抽象的。思路：利用反射写成统一处理逻辑。
涉及：xml格式的字符串如何转成对应的对象 @RequestBody
测试访问路径:http://localhost:8088/user/hello
功能完好,正常访问
###其他
包括文件下载功能
查看服务是否正常
http://localhost:8088/hsmodels/heartbeats
查看所有的日志名称
http://localhost:8088/hsmodels/findAll
下载所有的日志
http://localhost:8088/hsmodels/download1
下载特定的日志
http://localhost:8088/hsmodels/load?beginDate=2021-08-10&endDate=2021-08-11






