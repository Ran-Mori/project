# Project

用来存放个人项目的仓库

## java-project

### FOR_CRUD

> #### 功能
>
> * 实现一个基本的CRUD操作，让前端调用，用于VUE的学习
>
> #### 接口
>
> ```json
> # ip = localhost 
> # port = 80
> ```
>
> ```json
> func: 查询所有
> method： GET
> url:http://ip:port/songs
> request_param: null
> request_body: null
> respond_body: 
> {
>     "code": 200,
>     "message": "访问成功",
>     "data": {
>         "songs": [
>             {
>                 "id": 2,
>                 "name": "Forever You",
>                 "singer": "Zard",
>                 "feeling": "soft"
>             },
>             {
>                 "id": 3,
>                 "name": "枫",
>                 "singer": "周杰伦",
>                 "feeling": "像秋天的感觉"
>             }
>         ]
>     }
> }
> ```
>
> *************************
>
> ```json  
> func: 根据id查询一个
> method: GET
> url: http://ip:port/songs/1
> request_param: 1
> request_body: null
> responde_body:
> {
>     "code": 200,
>     "message": "访问成功",
>     "data": {
>         "song": {
>             "id": 3,
>             "name": "枫",
>             "singer": "周杰伦",
>             "feeling": "像秋天的感觉"
>         }
>     }
> }
> ```
>
> ```json
> func:新增一个
> method: POST
> url: http://ip:port/songs
> request_param: null
> request_body: 
> {
>     "id": 10, //id值任意无所谓，因为是自增的
>     "name": "等你下课",
>     "singer": "周杰伦",
>     "feeling": "高中的感觉"
> }
> respond_body:
> {
>     "code": 200,
>     "message": "访问成功",
>     "data": {}
> }
> ```
>
> ****************
>
> ```json
> func: 根据id删除
> method: DELETE
> url: http://ip:port/songs/1
> request_param: 1
> request_body: null
> responde_body:
> {
>     "code": 200,
>     "message": "访问成功",
>     "data": {}
> }
> ```
>
> *****************
>
> ```json
> func: 修改一个
> method: PUT
> url: http://ip:port/songs
> request_param: null
> request_body:
> {
>     "id": 4, //id值是最重要的，是根据id来删除
>     "name": "等你下课",
>     "singer": "周杰伦",
>     "feeling": "高中的感觉"
> }
> respond_body:
> {
>     "code": 200,
>     "message": "访问成功",
>     "data": {}
> }
> ```
>
> #### 坑
>
> ```json
> [
>     {
>         "question": "MySQL 插入数据中含有中文时抱错插入失败",
>         "answer"："mysql的console中执行如下语句 'alter table for_crud.song CONVERT TO CHARACTER SET utf8;'"
>     },
>     {
>         "question": "springboot如何读取 '/songs/{id}'中的id变量",
>         "answer": "使用@PathVirable注解"
>     },
>     {
>         "question":  "postman测试含有@RequestBody接口时总是报415错",
>         "answer" "postman测试工具要将 'text' 改成 'json'"
>     },
>     {
>         "question": "Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:compile (default-compile) on project izumisakai-zy: Fatal error compiling",
>         "answer":
>         {
>         	"措施": "在pom中添加一个依赖",
>             "依赖内容": "//<plugin>
>             		//		<groupId>org.apache.maven.plugins</groupId>
>             		//		<artifactId>maven-compiler-plugin</artifactId>
>             		//		<version>3.8.1</version>
>             		//		<configuration>
>                 	//			<source>1.8</source>
>                 	//			<target>1.8</target>
>             		//		</configuration>
>         			//	</plugin>"
>         }
>     }
> ]
> ```
>
> ***

### netease_music

> #### 功能
>
> * 客户端上传音乐到服务器
> * 服务器提供音乐给客户端播放
> * 服务器存储音乐的相关信息
>
> #### 接口
>
> ```json
> func: 上传音乐
> method: POST
> url: http://ip:port/song/upload
> request_param: null
> request_body: 
> {
>     "file": "周杰伦 - 半岛铁盒.mp3" #上传的是一个文件，写在requestBody体主要是为了方便
> }
> responde_body:
> {
>     "code": 200,
>     "message": "访问成功",
>     "data": {}
> }
> ```
>
> ***
>
> ```json
> func: 下载音乐
> method: GET
> url: http://ip:port/song/download
> request_param: 
> {
>     "singer": "周杰伦",
>     "songname": "半岛铁盒" #此方法是将音乐流传给浏览器播放，并不一定要下载
> }
> responde_body:
> {
>     "code": 200,
>     "message": "访问成功",
>     "data": {}
> }
> ```
>
> #### 坑
>
> ```json
> [
>     {
>         "question":"springboot的依赖总是不对"，
>         "answer":"不要自己配，去之前的GitHub抄"
>     },
>     {
>         "question":"springboot默认只能传输1M大小的文件",
>         "anwser":"修改配置"
>             spring:
>               servlet:
>                 multipart:
>                   max-file-size: 30MB
>                   max-request-size: 30MB
>     }，
>     {
>     	"question":"docker装MySQL启动不了，或者初始化困难"，
>     	"answer":"看docker.md文档，照着文档抄"
>     },
> 	{
>         "question":"显示某个组件没有注入容器",
>         "anwser":"在主类里面加@BasePackageScan注解"
>     },
> 	{
>         "question":"数据库insert操作提示miss type match",
>         "anwser":"直接原因是插入的类没有设置主键，根本原因是实体类没加mybatis plus的@TableId注解"
>     }
> ]
> ```
>
> ***

## vue-project

> ### my_api_test
>
> * 建立
>
> > * 第一步：执行 'vue ui' 指令进入图像化界面
> >
> > * 第二步：新建项目
> >
> > * 第三步：安装axios插件
> >
> > * 第四步：完成创建
>
> * 界面路由跳转
>
> > * 第一步：在路由index.js中添加新建的vue component
> >
> >   ```json
> >   {
> >       path: '/pathparapass',
> >       name: "第一条结果",
> >       component: PathParaPass //这个vue元素要在index.js的最上面引入
> >   }
> >   ```
> >
> > * 第二步：执行跳转函数
> >
> >   ```bash
> >   this.$router.push({
> >   	path: '/destination'
> >   })
> >   ```
>
> * 路径参数传递
>
> > * 参数发送
> >
> >   ```bash
> >   this.$router.push({
> >   	path: '/destination',
> >   	query: {
> >   		id: 3,
> >   		name: "IzumiSakai"
> >   	}
> >   })
> >   ```
> >
> > * 参数读取
> >
> >   ```bash
> >   this.$route.query.id
> >   this.$route.query.name
> >   ```
> >
> > * 参数格式(?拼接格式)
> >
> >   ```bash
> >   api?id=3&name=IzumiSakai
> >   ```
>
> * 通过$store传递json对象
>
> > * 传递思想
> >
> >   ```bash
> >   使用vue全局的$store对象，先将对象存入$store中，在从中读取出来
> >   ```
> >
> > * 第一步： 在$store的index.js中声明如下
> >
> >   ```bash
> >   person: {
> >   }
> >   ```
> >
> > * 第二步：发送对象
> >
> >   ```bash
> >   this.$store.person=this.person //其中this.person为定义好的json对象
> >   ```
> >
> > * 第三步：接收对象
> >
> >   ```bash
> >   this.$store.person
> >   ```
> >
> > * 缺点
> >
> >   ```bash
> >   每次刷新后就没了，$store一定要页面跳转才有效
> >   ```
>
> * 通过router传递寄送对象
>
> > * 传递思想
> >
> >   ```bash
> >   在传送时对对象进行编码，编码成字符串传送
> >   在接收时对对象进行解码，解码成对象接收
> >   ```
> >
> > * 发送
> >
> >   ```bash
> >   this.$router.push({
> >   	path: '/objectpassbyrouter',
> >   	query: {
> >   		person: JSON.stringify(this.person)
> >   	}
> >   })
> >   ```
> >
> > * 接收
> >
> >   ```bash
> >   person: JSON.parse(this.$route.query.person)
> >   ```
>
> * axios发送请求
>
> > * GET
> >
> >   ```bash
> >   axios.get("/song/sonngs").then(response => {
> >   	this.songs=response.data.data.sonngs
> >   });
> >   ```
> >
> > * DELETE
> >
> >   ```bash
> >   axios.delete('/song/songs/'+id).then(response => {
> >   	console.log(response);
> >   });
> >   ```
> >
> > * POST
> >
> >   ```json
> >   // data中song的格式
> >   song: {
> >           id: null,
> >           name: 'song_name',
> >           singer: 'song_singer',
> >           feeling: 'song_feeling'
> >   }
> >   
> >   axios.post('/song/songs',this.song).then(response => {
> >       console.log(response)
> >   })
> >   ```
> >
> > * PUT
> >
> >   ```json
> >   // data中song的格式
> >   song: {
> >           id: 10, //根据id删除，id十分重要
> >           name: 'song_name',
> >           singer: 'song_singer',
> >           feeling: 'song_feeling'
> >   }
> >   
> >   axios.put('/song/songs',this.song).then(response => {
> >       console.log(response)
> >   })
> >   ```
>
> * 跨域问题解决
>
> > * 解决思路：使用代理
> >
> > * 第一步： 因为vue-cli 3没有配置选项，因此要在根目录下新建 'vue.config.js' 配置文件
> >
> > * 修改配置文件内容
> >
> >   ```bash
> >   module.exports = {
> >       devServer: {
> >           open: true, //是否自动弹出浏览器页面
> >           host: "localhost",
> >           port: '8081',
> >           https: false,   //是否使用https协议
> >           hotOnly: false, //是否开启热更新
> >           proxy: {
> >               '/song': {
> >                   target: 'http://47.108.63.126',
> >                   ws: true,
> >                   changeOrigin: true,
> >                   pathRewrite: {
> >                       '^/song':''
> >                   }
> >               }
> >           }
> >       }
> >   }
> >   
> >   ```
> >
> > * 结果
> >
> >   ```bash
> >   发出的api地址： '/song/songs'
> >   实际访问的api地址： 'http://47.108.63.126/songs'
> >   
> >   原理：所有以 '^/song' 开头的请求都走代理路径，将 '^/song' 变成 '' , 在拼上IP地址形成最终访问的api地址
> >   ```
> >
> >   

