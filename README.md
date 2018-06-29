### 初衷：
电脑里存了一些电影，想通过手机来观看，并且多个人都可以用，传统的方法稍微麻烦一点，于是就想写一个网页，借助局域网的高速下载，在手机上用浏览器就能看。

---
### 现实：
我的手机是iphone6s，用safari观看，然而html的video标签支持的格式中适用的只有mp4格式的，并且编码还有要求，是h264格式的才行，试了下xvid格式也能看，只是画面比较模糊，于是找了一款叫ffmpeg的软件帮助转码。用户界面模拟了windows的文件系统，后来发现很多其他格式的文件也列了出来，但是不能点击很难受，顺便写了个文件静态资源下载的功能。最终发现此软件系统酷似一个小型的文件共享系统。

---
### 技术：
后端，spring boot：大部分的逻辑，文件的读取，视频转码命令调用等。  
java模板引擎，thymeleaf：不是胸腺，而是把java的动态变量，写到页面上去。  
样式，bootstrap3：让页面更好看，许多样式都做了对手机的适应。  
插件，ffmpeg：仅用java调用了命令，无编程。

---
### 安装与使用：
* 安装[java](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)环境，版本号是jdk-8xxxxx，可以百度一下怎么[安装](https://jingyan.baidu.com/article/0202781175839b1bcc9ce529.html)。
* 安装maven，可以百度一下怎么[安装](https://jingyan.baidu.com/article/6c67b1d646ae842786bb1e7a.html)。
* 安装[ffmpeg](https://ffmpeg.zeranoe.com/builds/)，下载并解压到任意目录。
* 安装[文件共享系统](https://github.com/gitadmini/relax)。
 1. [下载](https://github.com/gitadmini/relax/archive/master.zip)文件，并解压到任意目录。
 2. 修改配置参数：打开文件\relax-master\src\main\resources\application-dev.properties，修改ffmpeg.path=F:\\\\relax\\\\ffmpeg-20180619-830695b-win64-static\\\\bin\\\\，等号后面写你的ffmpeg的bin目录。
 3. 编译并运行：运行cmd窗口，cd到relax-master目录（例如：cd F:\relax-master），切换磁盘（例如：F:），执行打包命令mvn clean package -Dmaven.test.skip=true，成功后cd到target目录（cd target），运行程序java -jar relax-0.0.1-SNAPSHOT.jar。
* 将手机连入电脑所在的局域网中，打开浏览器输入共享电脑的局域网地址+/collect，例如：192.168.11.101/collect，后面的操作都比较傻瓜式。

---
### 界面：
![iphone6s safari](https://upload-images.jianshu.io/upload_images/12861224-5dd738d6a349f289.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

如果有技术上的问题，请联系我，我的手机号是138 8了8了8了8。  

---
如果用的觉得好，可以赏个小钱儿，不胜感激。  
![打赏](https://github.com/gitadmini/common/blob/master/IMG_2634.JPG)
