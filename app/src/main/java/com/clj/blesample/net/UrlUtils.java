package com.clj.blesample.net;


public class UrlUtils {

    public static final String APIHTTP = "https://shuangchuang.pro1.lnkj1.com";
    //账号相关 登陆注册修改密码找回密码
    public static final String login = APIHTTP + "/Api/PublicApi/login";//登录
    public static final String register = APIHTTP + "/Api/PublicApi/register";//注册
    public static final String smsCode = APIHTTP + "/Api/PublicApi/smsCode";//发送验证码接口
    public static final String checkSmsCode = APIHTTP + "/Api/PublicApi/checkSmsCode";//找回密码_验证验证码
    public static final String findpwdSave = APIHTTP + "/Api/PublicApi/findpwdSave";//找回密码_修改密码
    public static final String thirdPartyLogin = APIHTTP + "/Api/PublicApi/thirdPartyLogin";//三方登录
    /*首页相关*/
    public static final String recommendArticle = APIHTTP + "/Api/IndexApi/recommendArticle";//首页推荐文章
    public static final String goodsCategoryList = APIHTTP + "/Api/IndexApi/goodsCategoryList"; //首页产品分类
    public static final String adBanner = APIHTTP + "/Api/IndexApi/adChangeBanner";//首页轮播
    public static final String navList = APIHTTP + "/Api/IndexApi/navList";    //轮播下面的分类
    public static final String indexAd = APIHTTP + "/Api/IndexApi/indexAd";    //产品下广告
    public static final String indexGoods = APIHTTP + "/Api/IndexApi/indexGoods";  //首页商品
    public static final String shareGoods = APIHTTP + "/Api/UserCenterApi/shareGoods";//商品分享
    public static final String goodsInfo = APIHTTP + "/Api/GoodsApi/goodsInfo";//商品详情
    public static final String buyGoods = APIHTTP + "/Api/OrderApi/buyGoods";//立即购买
    public static final String goodsCentent = APIHTTP + "/Api/GoodsApi/goodsCentent";//商品详情web页面
    public static final String goodsCommentList = APIHTTP + "/Api/GoodsApi/goodsCommentList";//商品全部评论列表
    public static final String commentInfo = APIHTTP + "/Api/CommentApi/commentInfo";//评论详情
    public static final String remainingOrder = APIHTTP + "/Api/OrderApi/remainingOrder";//余额支付
    public static final String payment = APIHTTP + "/Api/Payment/payment";//支付宝支付
    public static final String goodsCategory = APIHTTP + "/Api/GoodsApi/goodsCategory";//分类+商品
    public static final String goodsList = APIHTTP + "/Api/GoodsApi/goodsList";//商品列表
    public static final String userSearch = APIHTTP + "/Api/GoodsApi/userSearch";//用户搜索记录
    public static final String deleteUserSearch = APIHTTP + "/Api/GoodsApi/deleteUserSearch";//删除搜索记录
    public static final String goodsListOfIndex = APIHTTP + "/Api/GoodsApi/goodsListOfIndex";//走量 上新 推荐榜商品列表
    public static final String commentZan = APIHTTP + "/Api/CommentApi/commentZan";//评论点赞，取消点赞
    public static final String payment_wx = APIHTTP + "/Api/Wxpay/payment";
    public static final String addReplyComment = APIHTTP + "/Api/CommentApi/addReplyComment";//回复评论
    public static final String privilege = APIHTTP + "/Api/AboutApi/privilege";//会员特权
    /*w文章相关*/
    public static final String articleCategoryList = APIHTTP + "/Api/ArticleApi/articleCategoryList";//文章分类（学习赚钱）
    public static final String getArticleList = APIHTTP + "/Api/ArticleApi/getArticleList";//文章列表
    public static final String articleInfo = APIHTTP + "/Api/ArticleApi/articleInfo"; //文章web详情页
    public static final String userArticleComment = APIHTTP + "/Api/UserCenterApi/userArticleComment";//文章评论
    public static final String articleCommentList = APIHTTP + "/Api/ArticleApi/articleCommentList";//文章评论列表

    /*购物车模块*/
    public static final String cartList = APIHTTP + "/Api/CartApi/cartList";//购物车列表
    public static final String figureCartGoods = APIHTTP + "/Api/CartApi/figureCartGoods";  //计算购物车选择商品的总价
    public static final String addCartGoodsOrder = APIHTTP + "/Api/CartApi/addCartGoodsOrder";//批量下单
    public static final String addUserFavorite = APIHTTP + "/Api/CartApi/addUserFavorite";//加入收藏
    public static final String addCart = APIHTTP + "/Api/CartApi/addCart";  //加入、移除购物车
    public static final String deleteCartGoods = APIHTTP + "/Api/CartApi/deleteCartGoods";//删除购物车商品
    public static final String setCartNumber = APIHTTP + "/Api/CartApi/setCartNumber";//修改购物车商品数量
    public static final String deleteUserFavoriteGoods = APIHTTP + "/Api/UserCenterApi/deleteUserFavoriteGoods";//取消收藏

    /*订单相关*/
    public static final String submitGoodsOrder = APIHTTP + "/Api/OrderApi/submitGoodsOrder";//提交订单最新
    public static final String submitOrder = APIHTTP + "/Api/OrderApi/submitOrder";//提交订单
    public static final String addOrder = APIHTTP + "/Api/OrderApi/addOrder";//商品下单
    public static final String userOrderList = APIHTTP + "/Api/OrderApi/userOrderList";//我的订单列表
    public static final String affirmOrder = APIHTTP + "/Api/OrderApi/affirmOrder";//确认订单数据展示
    public static final String addComment = APIHTTP + "/Api/CommentApi/addComment";//评论订单
    public static final String cancelOrder = APIHTTP + "/Api/OrderApi/cancelOrder";//取消订单
    public static final String affirmTake = APIHTTP + "/Api/OrderApi/affirmTake";//确认收货
    public static final String orderInfo = APIHTTP + "/Api/OrderApi/orderInfo";//订单详情
    //个人中心
    public static final String editUserPassword = APIHTTP + "/Api/UserCenterApi/editUserPassword";//修改密码
    public static final String realNameAuthentication = APIHTTP + "/Api/UserCenterApi/realNameAuthentication";//实名认证
    public static final String feedBack = APIHTTP + "/Api/UserCenterApi/feedBack";//意见反馈
    public static final String boundMobile = APIHTTP + "/Api/UserCenterApi/boundMobile";//绑定手机号
    public static final String getUserInfo = APIHTTP + "/Api/UserCenterApi/getUserInfo";//获取个人信息
    public static final String setPayPassword = APIHTTP + "/Api/UserCenterApi/setPayPassword";//设置修改支付密码
    public static final String checkOldPassword = APIHTTP + "/Api/UserCenterApi/checkOldPassword";//验证旧支付密码
    public static final String setCity = APIHTTP + "/Api/UserCenterApi/setCity";//设置城市
    public static final String setUserHeadPic = APIHTTP + "/Api/UserCenterApi/setUserHeadPic";//设置头像
    public static final String setUserWeixin = APIHTTP + "/Api/UserCenterApi/setUserWeixin";//设置微信
    public static final String setUserWeibo = APIHTTP + "/Api/UserCenterApi/setUserWeibo";//设置微博
    public static final String setUserSex = APIHTTP + "/Api/UserCenterApi/setUserSex";//设置性别
    public static final String setUserNickname = APIHTTP + "/Api/UserCenterApi/setUserNickname";//设置昵称
    public static final String setBirthday = APIHTTP + "/Api/UserCenterApi/setBirthday";//设置生日
    public static final String setUserSignature = APIHTTP + "/Api/UserCenterApi/setUserSignature";//设置签名
    public static final String setUserEmail = APIHTTP + "/Api/UserCenterApi/setUserEmail";//设置邮箱
    public static final String editUserAddress = APIHTTP + "/Api/UserCenterApi/editUserAddress";//修改地址
    public static final String deleteUserAddress = APIHTTP + "/Api/UserCenterApi/deleteUserAddress";//删除地址
    public static final String addUserAddress = APIHTTP + "/Api/UserCenterApi/addUserAddress";//添加收货地址
    public static final String deleteUserFavorite = APIHTTP + "/Api/UserCenterApi/deleteUserFavorite";//删除收藏
    public static final String userFavorite = APIHTTP + "/Api/UserCenterApi/userFavorite";//商品收藏列表
    public static final String userAddress = APIHTTP + "/Api/UserCenterApi/userAddress";//用户地址列表
    public static final String setDefaultAddress = APIHTTP + "/Api/UserCenterApi/setDefaultAddress";//设置默认地址
    public static final String regionList = APIHTTP + "/Api/IndexApi/regionList";//城市列表
    public static final String about = APIHTTP + "/Api/AboutApi/about";//关于商城web页面
    public static final String helpList = APIHTTP + "/Api/AboutApi/helpList";//帮助中心列表
    public static final String aboutInfo = APIHTTP + "/Api/AboutApi/aboutInfo/id/";//帮助中心文章详情web
    public static final String userOrderReturn = APIHTTP + "/Api/OrderReturnApi/userOrderReturn";//我的售后列表
    public static final String orderRefundInfo = APIHTTP + "/Api/OrderReturnApi/orderRefundInfo";//售后详情
    public static final String addOrderReturn = APIHTTP + "/Api/OrderReturnApi/addOrderReturn";//申请售后
    public static final String userMessages = APIHTTP + "/Api/UserCenterApi/userMessages";//消息中心
    public static final String userMoneyLog = APIHTTP + "/Api/UserCenterApi/userMoneyLog";//余额明细
    public static final String getUserBank = APIHTTP + "/Api/UserCenterApi/getUserBank";//用户账户列表
    public static final String addBank = APIHTTP + "/Api/UserCenterApi/addBank";//添加账户
    public static final String deleteUserBank = APIHTTP + "/Api/UserCenterApi/deleteUserBank";//删除账户信息
    public static final String getSwitch = APIHTTP + "/Api/UserCenterApi/getSwitch";//获取签到/分享系统开关
    public static final String bindUserParent = APIHTTP + "/Api/UserCenterApi/bindUserParent";//绑定邀请码
    public static final String myComment = APIHTTP + "/Api/CommentApi/myComment";//我的评价
    public static final String download = APIHTTP + "/Api/PublicApi/downLoad";//下载
    public static final String getMyUser = APIHTTP + "/Api/UserCenterApi/getMyUser";//我的会员
    public static final String goodsImages = APIHTTP + "/Api/GoodsApi/goodsImages";//获取微信朋友圈的分享图集
    public static final String getExpressTraces = APIHTTP + "/Api/OrderApi/getExpressTraces";//查看物流
    public static final String userRecharge = APIHTTP + "/Api/UserCenterApi/userRecharge";//用户充值
    public static final String withdrawUserMoney = APIHTTP + "/Api/UserCenterApi/withdrawUserMoney";//用户提现
    public static final String UserCenter = APIHTTP + "/Api/UserCenterApi/UserCenter";//会员中心
    public static final String protocol = APIHTTP + "/Api/PublicApi/protocol";//注册协议
    public static final String userCount = APIHTTP + "/Api/UserCenterApi/userCount";//订单和消息等数量
    public static final String findPaymentPassword = APIHTTP + "/Api/PublicApi/findPaymentPassword";//找回支付密码
    //个人中心
    /*签到相关*/
    public static final String signCentent = APIHTTP + "/Api/AboutApi/signCentent";//签到说明
    public static final String userShare = APIHTTP + "/Api/UserCenterApi/userShare";//分享
    public static final String userSign = APIHTTP + "/Api/UserCenterApi/userSign";//用户签到
    public static final String userSignStatus = APIHTTP + "/Api/UserCenterApi/userSignStatus";//用户签到
    public static final String retroactive = APIHTTP + "/Api/UserCenterApi/retroactive";//用户补签
    public static final String userWithdrawLog = APIHTTP + "/Api/UserCenterApi/userWithdrawLog";//用户提现滚动
    /*签到相关*/
    /*推广任务相关*/
    public static final String taskInfo = APIHTTP + "/Api/TaskApi/taskInfo";//任务详情
    public static final String taskList = APIHTTP + "/Api/TaskApi/taskList";//任务列表
    public static final String editUserTask = APIHTTP + "/Api/TaskApi/editUserTask";//修改任务
    public static final String delTaskImg = APIHTTP + "/Api/TaskApi/delTaskImg";//删除任务图片
    public static final String addUserTask = APIHTTP + "/Api/TaskApi/addUserTask";//提交任务
    public static final String userPromotion = APIHTTP + "/Api/AboutApi/userPromotion/user_id/";//我的推广Web
    /*推广任务相关*/
    /*微赚中心*/
    public static final String UserMake = APIHTTP + "/Api/UserCenterApi/UserMake";//微赚中心
    public static final String allUserMoney = APIHTTP + "/Api/UserCenterApi/allUserMoney";//全网会员盈利列表
    public static final String firstUserMoney = APIHTTP + "/Api/UserCenterApi/firstUserMoney";//下级会员盈利列表
    public static final String twoUserMoney = APIHTTP + "/Api/UserCenterApi/twoUserMoney";//次级会员盈利列表
    public static final String addressUserMoney = APIHTTP + "/Api/UserCenterApi/addressUserMoney";//本地区盈利列表
    public static final String promotion = APIHTTP + "/Api/AboutApi/promotion";//推广规则

    /*微赚中心*/
}
