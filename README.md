# Project
用来存放个人项目的仓库

## java-project

> ### FOR_CRUD
>
> * 功能：实现一个基本的CRUD操作，让V前端调用用于VUE的学习
>
> * 接口
>
>   ```json
>   # ip = localhost 
>   # port = 80
>   ```
>   **********************
>   ```json
>   func: 查询所有
>   method： GET
>   url:http://ip:port/songs
>   request_param: null
>   request_body: null
>   respond_body: 
>   {
>       "code": 200,
>       "message": "访问成功",
>       "data": {
>           "songs": [
>               {
>                   "id": 2,
>                   "name": "Forever You",
>                   "singer": "Zard",
>                   "feeling": "soft"
>               },
>               {
>                   "id": 3,
>                   "name": "枫",
>                   "singer": "周杰伦",
>                   "feeling": "像秋天的感觉"
>               }
>           ]
>       }
>   }
>   ```
>   *************************
>   ```json  
>   func: 根据id查询一个
>   method: GET
>   url: http://ip:port/songs/1
>   request_param: 1
>   request_body: null
>   responde_body:
>   {
>       "code": 200,
>       "message": "访问成功",
>       "data": {
>           "song": {
>               "id": 3,
>               "name": "枫",
>               "singer": "周杰伦",
>               "feeling": "像秋天的感觉"
>           }
>       }
>   }
>   ```
>
>   ```json
>   func:新增一个
>   method: POST
>   url: http://ip:port/songs
>   request_param: null
>   request_body: 
>   {
>       "id": 10, //id值任意无所谓，因为是自增的
>       "name": "等你下课",
>       "singer": "周杰伦",
>       "feeling": "高中的感觉"
>   }
>   respond_body:
>   {
>       "code": 200,
>       "message": "访问成功",
>       "data": {}
>   }
>   ```
>   
>   ****************
>   
>   ```json
>   func: 根据id删除
>   method: DELETE
>   url: http://ip:port/songs/1
>   request_param: 1
>   request_body: null
>   responde_body:
>   {
>       "code": 200,
>       "message": "访问成功",
>       "data": {}
>   }
>   ```
>   
>   *****************
>   
>   ```json
>   func: 修改一个
>   method: PUT
>   url: http://ip:port/songs
>   request_param: null
>   request_body:
>   {
>       "id": 4, //id值是最重要的，是根据id来删除
>       "name": "等你下课",
>       "singer": "周杰伦",
>       "feeling": "高中的感觉"
>   }
>   respond_body:
>   {
>       "code": 200,
>       "message": "访问成功",
>       "data": {}
>   }
>   ```
>   
> * 坑
>
>   ```json
>   [
>       {
>           "question": "MySQL 插入数据中含有中文时抱错插入失败",
>           "answer"："mysql的console中执行如下语句 'alter table for_crud.song CONVERT TO CHARACTER SET utf8;'"
>       },
>       {
>           "question": "springboot如何读取 '/songs/{id}'中的id变量",
>           "answer": "使用@PathVirable注解"
>       },
>       {
>           "question":  "postman测试含有@RequestBody接口时总是报415错",
>           "answer" "postman测试工具要将 'text' 改成 'json'"
>       },
>       {
>           "question": "Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:compile (default-compile) on project izumisakai-zy: Fatal error compiling",
>           "answer":
>           {
>           	"措施": "在pom中添加一个依赖",
>               "依赖内容": "//<plugin>
>               		//		<groupId>org.apache.maven.plugins</groupId>
>               		//		<artifactId>maven-compiler-plugin</artifactId>
>               		//		<version>3.8.1</version>
>               		//		<configuration>
>                   	//			<source>1.8</source>
>                   	//			<target>1.8</target>
>               		//		</configuration>
>           			//	</plugin>"
>           }
>       }
>   ]
>   
>   
>   
>   ```
>
>   