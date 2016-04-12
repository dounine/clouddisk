CloudDisk 360云盘存储SDK
====
[![yunpan]](http://yunpan.360.cn)
[yunpan]:https://github.com/dounine/clouddisk/raw/master/logo.png "360云盘"
快速向导
----
**1. 如何得到 `CloudDisk`**
  *  从 `maven` 仓库下载
```maven 
<dependency>
	<groupId>com.dounine</groupId>
	<artifactId>clouddisk</artifactId>
	<version>x.x.x</version>
</dependency>
 ```
  * 从`github`中下载并编译
```
git clone https://github.com/dounine/clouddisk.git
mvn clean package
```
**2. 如何使用 `CloudDisk`**
 *  获取文件列表
```java
LoginUserToken loginUserToken = new LoginUserToken("account","passport",false);

FileListParser fileListParser = new FileListParser(loginUserToken);
FileListParameter fileListParameter = new FileListParameter();
fileListParameter.setPath("/");
FileList fileList = fileListParser.parse(fileListParameter);
```
**3. `CloudDisk` 已支持功能列表**
 *  登录
 *  获取授权token令牌
 *  用户签到领取空间
 *  获取用户信息
 *  检查用户是否登录
 *  操作历史
 *  验证码
 *  服务器分压转发
 *  文件功能
 	*  获取文件列表
 	*  获取文件上传地址
 	*  文件上传
 	*  文件搜索
 	*  文件版本回滚
 	*  文件更名
 	*  文件删除
 	*  文件移动
 	*  获取文件详细信息
 	*  文件历史版本信息
 	*  判断文件是否存在
 	*  获取文件下载信息
 	*  下载文件
 	*  创建文件夹
