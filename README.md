# 关于考核

## 笔者：irving
----------

## 一、 关于作品
### 1、 描述
  该软件名为ZhihuDaily，可以实时地查看知乎上的一些热点新闻地点评文档，以及大家在评论区地激烈讨论，还能通过该软件注册专为本软件设计的、单独的ZhihuDaily的账号
### 2、 感受
  安卓真是片神奇的大海，我太渺小了。上大学以前，从未接触过这些东西，红岩为我打开了一扇新世界的大门。从从未接触过代码到能稍微写出一些带着我思想的代码，虽然这个成长可能是渺小的，但我依然为自己骄傲。不过安卓的水也是真的深，无数的大坑博客真是让人只能打碎了牙齿往肚子里吞。“海到无边天作岸，山登绝顶我为峰”，敬自己。
### 3、 体会
  每一行代码都得亲手敲过，每一个方法都得独立算过，每一个类都得亲自想过，方能锻炼自己，让自己成长的更加强大。一个月从无知小白到略知一二的经历告诉我，所有的强大都是在黑与红中捶打、磨砺而来。太阳背后不是光，想要成为和红岩各位大神一样水平，我还有很长的路要走，希望自己能够在这慢慢修行之途中坚持下来吧。
## 二、 功能及操作方式
1、 主界面的新闻总览+新闻具体页面浏览  
![comment](https://github.com/157304lemon/ZhihuDaily/blob/master/ExplanatoryPictures/news.gif)  

2、 新闻的短评论和长评论的查看  
![comment](https://github.com/157304lemon/ZhihuDaily/blob/master/ExplanatoryPictures/comment.gif)

3、 其他日期的新闻查看  
![comment](https://github.com/157304lemon/ZhihuDaily/blob/master/ExplanatoryPictures/pastnews.gif)

4、 在加载一次以后，如果没有网络连接，可以优先加载本地缓存  
![comment](https://github.com/157304lemon/ZhihuDaily/blob/master/ExplanatoryPictures/lixian.gif)

5、 注册自己的专属账号（自行实现api）  
![comment](https://github.com/157304lemon/ZhihuDaily/blob/master/ExplanatoryPictures/register.gif)  

 登陆自己的专属账号（自行实现api）  
![comment](https://github.com/157304lemon/ZhihuDaily/blob/master/ExplanatoryPictures/login.gif)    


6、 两个小东西，侧滑栏和自定义弹窗   
<img src="https://github.com/157304lemon/ZhihuDaily/blob/master/ExplanatoryPictures/cehualan.jpg" width=350 height=750/>
<img src="https://github.com/157304lemon/ZhihuDaily/blob/master/ExplanatoryPictures/dialog.jpg" width=350 height=750/>

## 三、技术实现/知识点  

1、 主界面的新闻列表总览使用Fragment，在这个Fragment中使用RecyclerView来加载每一天的新闻总览表

2、详细文章页面使用CoordinatorLayout加上CollapsingToolbarLayout组成了文章页面的布局

3、评论页面使用NestedScrollview内嵌两个RecyclerView来分别加载长评论与短评论

4、 自行设计后台api。通过tomcat建立我的服务器，再通过ideau设计简单的登陆和注册逻辑，连接我的mysql数据库进行数据存储与登陆的验证，还要使用natapp在公网和本地运行的 Web 服务器之间建立一个安全的通道。（该api的逻辑代码仓库地址https://github.com/157304lemon/Simpleapi.git）

5、 本地缓存的加载，使用的SharedPerenfences轻量级储存实现，通过判断是否有网络连接决定是否加载本地缓存（ps：至少要有一次在有网络的情况下打开过软件才可以有缓存记录）
