package com.example.demo.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuliang on 2018/9/25.
 */

public class MallConstant {

    /**
     * 脱敏替换字符
     */
    public static final String INSENSITIVE_STRING = "******";

    /**
     * 分页默认从第一页开始
     */
    public static final Integer PAGECURRENT = 1;

    /**
     * 分页默认每页显示20条
     */
    public static final Integer PAGESIZE = 20;

    public static final String FORBIDDEN="1";

    /**
     * 敏感属性列表
     */
    public static List<String> INSENSITIVE_LIST = new ArrayList<>();

    static {
        INSENSITIVE_LIST.add("passwd");
        INSENSITIVE_LIST.add("password");
        INSENSITIVE_LIST.add("initPasswd");
    }

    /**
     * OSS
     */
    public static String ENDPOINT = "http://oss-cn-shanghai.aliyuncs.com";
    public static String ACCESSKEYID = "LTAIZ8BK3eEgOASS";
    public static String ACCESSKEYSECRET = "ktjIwQpJlgbMa1HNatR9hGe42P5X0X";
    public static String BUCKETNAME = "dev-m-bucket";
    public static String FILEDIR = "imgupload/";

    /**
     * JWT
     */
    public static final String JWT_SECERT = "8677df7fc3a34e26a61uq124534747hc034d5ec8245d";			//密匙
    public static final long JWT_TTL = 30 * 24 * 60 * 60 * 1000;							//token有效时间

    //Redis默认过期时间（单位：秒）
    public static final long REDIS_DEFAULT_EXPIRE = 365 * 24 * 60 * 60;
    //Session默认过期时间（单位：秒）
    public static final long SESSION_DEFAULT_EXPIRE =24 * 60 * 60;


    //Redis中存放RedisSession的名称前缀（格式为：前缀_sessionId）
    public static final String REDIS_SESSION = "REDIS_SESSION_";
    //RedisSession中存放登陆用户信息的key
    public static final String SESSION_LOGIN_USER = "SESSION_LOGIN_USER";
    //RedisSession中存放登陆用户角色列表的key
    public static final String SESSION_LOGIN_USER_ROLE_LIST = "SESSION_LOGIN_USER_ROLE_LIST";
    //RedisSession中存放登陆用户所有菜单列表的key
    public static final String SESSION_LOGIN_USER_MENU_LIST = "SESSION_LOGIN_USER_MENU_LIST";

    //Redis中存放ShiroSession的名称前缀（格式为：前缀_sessionId）
    public static final String SHIRO_SESSION = "SHIRO_SESSION:";
    //Shiro框架默认的是否验证成功的key，键对应的值为boolean（该键为框架定义，不要修改）
    public static final String SHIRO_DEFAULT_AUTHENTICATED_KEY = "org.apache.shiro.subject.support.DefaultSubjectContext_AUTHENTICATED_SESSION_KEY";
    //Shiro框架默认的用户名key，键对应的值为String（该键为框架定义，不要修改）
    public static final String SHIRO_DEFAULT_PRINCIPALS = "org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY";
    //ShiroSession中用户拥有的功能权限URL信息的key
    public static final String SHIRO_FUNCTION_URL = "SHIRO_FUNCTION_URL";

    /**
     * 返回值
     */
    public static final String SYS_LOGIN_SUCCESS = "登录成功";
    public static final String SYS_LOGOUT_SUCCESS = "退出登录成功";
    public static final String USER_INFO_SUCCESS = "该手机未注册";
    public static final String CREATE_USER_SUCCESS = "创建用户成功";
    public static final String UPDATE_USER_SUCCESS = "更新用户信息成功";
    public static final String DELETE_USER_SUCCESS = "删除用户成功";
    public static final String EXPORT_EXCEL_SUCCESS = "导出excel成功";
    public static final String SYS_SENDMSG_SUCCESS = "验证码发送成功";
    public static final String SYS_VETIFYMSG_SUCCESS = "验证码验证成功";
    public static final String QUIT_USER_SUCCESS = "用户退出登录成功";
    public static final String FORBIDEN_USER_SUCCESS = "禁用用户成功";
    public static final String UPDATE_PERSON_SUCCESS = "更新用户信息成功";
    public static final String UPLOAD_ICON_SUCCESS = "上传头像成功";
    public static final String UPLOAD_FRONT_IDCARD_SUCCESS = "上传头像成功";
    public static final String UPLOAD_BACK_IDCARD_SUCCESS = "上传头像成功";
    public static final String ADD_USER_ADDR_SUCCESS = "添加用户地址成功";
    public static final String DEL_USER_ADDR_SUCCESS = "删除用户地址成功";
    public static final String UPDATE_USER_ADDR_SUCCESS = "更新用户地址成功";
    public static final String GET_DEFAULT_USER_ADDR_SUCCESS = "获取用户默认地址成功";
    public static final String GET_USER_ADDR_LIST_SUCCESS = "获取用户地址列表成功";
    public static final String ADD_ITEM_INFO_SUCCESS = "添加商品信息成功";
    public static final String UPLOAD_ITEM_IMAGES_SUCCESS = "上传图片成功";
    public static final String GET_ITEM_INFO_SUCCESS = "获取商品信息成功";
    public static final String GUIDE_AREAS_SUCCESS = "获取行政区域成功";

    public static final String PHONE_BINDING_SUCCESS = "用户手机绑定成功";
    public static final String QEERY_STATTUS_SUCCESS = "获取用户成功";
    public static final String GET_ITEM_SKU_INFO_SUCCESS = "获取商品列表信息成功";
    public static final String UPDATA_ITEM_INFO_SUCCESS = "修改商品信息成功";
    public static final String DELETE_ITEM_INFO_SUCCESS = "删除商品信息成功";
    public static final String ADD_CART_INFO_SUCCESS = "添加购物车成功";
    public static final String UPDATE_CART_INFO_SUCCESS = "更新购物车成功";
    public static final String SUBMIT_ORDER_INFO_SUCCESS = "提交订单成功";
    public static final String GET_ROLE_PERMISSION_SUCCESS = "获取角色权限成功";
    public static final String MOTIFY_ROLE_PERMISSION_SUCCESS = "更新角色权限成功";
    public static final String DELETE_ROLE_PERMISSION_SUCCESS = "删除角色权限成功";
    public static final String ADD_ROLE_PERMISSION_SUCCESS = "添加角色权限成功";
    public static final String ADD_ROLE_SUCCESS = "添加角色成功";
    public static final String DELETE_ROLE_SUCCESS = "删除角色成功";
    public static final String MOTIFY_ROLE_SUCCESS = "更新角色成功";
    public static final String GET_ALL_ROLE_SUCCESS = "获取所有角色成功";
    public static final String GET_ROLE_SUCCESS = "获取角色成功";
    public static final String GET_PERMISSION_LIST_SUCCESS = "获取权限列表成功";
    public static final String MOTIFY_PERMISSION_SUCCESS = "编辑权限成功";
    public static final String GET_ID_PERMISSION_SUCCESS = "获取权限信息成功";
    public static final String ADD_PERMISSION_SUCCESS = "新增权限成功";
    public static final String DELETE_PERMISSION_SUCCESS = "删除权限成功";
    public static final String QEERY_ROLE_SUCCESS = "获取用户角色成功";
    public static final String MOTIFY_USER_ROLE_SUCCESS = "编辑用户角色成功";
    public static final String DELETE_USER_ROLE_SUCCESS = "删除用户角色成功";
    public static final String CHOOSE_USER_ROLE_SUCCESS = "分配用户角色成功";
    public static final String ORDER_PAY_SUCCESS = "支付成功";
    public static final String ALL_QUERY_SUCCESS = "查询成功";
    public static final String ALL_UPDATE_SUCCESS = "编辑成功";
    public static final String ALL_CHECK_SUCCESS = "验证成功";
    public static final String SUBMIT_RECHARGE_SUCC = "提交充值记录成功";
    public static final String ALL_DELETE_SUCCESS = "删除成功";
    public static final String ALL_CLOSE_SUCCESS = "取消成功";
    public static final String QUERY_JOURNAL__SUCCESS = "获取流水列表成功";
    public static final String WECHAT_LOGIN_SUCCESS = "用户微信登录成功";
    public static final String QUERY_RECHARGEINFO_SUCCESS="获取用户充值列表成功";
    public static final String SYS_CREATEVERFY_SUCCESS = "生成验证码成功";
    public static final String UPDATE_RECHARGE_SUCCESS = "审核用户充值成功";
    public static final String QUERY_USERACCOUNT_SUCCESS = "获取用户账户信息成功";
    public static final String SUMMIT_BANKCARD_SUCCESS = "提交银行卡信息成功";
    public static final String QUERY_USER_BANKCARD_SUCCESS = "获取用户银行卡列表成功";
    public static final String UPDATE_BANKCARD_SUCCESS = "编辑银行卡成功";
    public static final String QUERY_USER_JOURNAL_SUCCESS="获取用户流水列表成功";
    public static final String QUERY_POSTFEE_SUCCESS="获取运费成功";
    public static final String QUERY_WITHDRAWINFO_SUCCESS="获取用户提现列表成功";
    public static final String UPDATE_WITHDRAW_SUCCESS = "审核用户提现成功";
    public static final String CLOUD_PICKUP_SUCCESS = "云库存提货下单成功";
    public static final String CLOUD_EXCHANGE_SUCCESS = "云库存换货下单成功";
    public static final String UPDATE_ACCOUNT_SUCCESS = "更新账户成功";
    public static final String TRANSFER_GOODS_SUCCESS = "云库存转货成功";
    public static final String REALNAME_AUTH_SUCC = "实名制认证资料获取成功";
    public static final String SUBMIT_REALAUTH_SUCC = "提交实名制认证请求成功";
    public static final String SUBMIT_REALAUTH_STATUS_SUCC = "获取实名制状态成功";
    public static final String ORDER_VERIFY_SUCCESS = "审核成功";
    public static final String CREATE_APPVERSION_MANAGE_SUCC = "创建APP版本配置成功";
    public static final String UPDATE_APPVERSION_MANAGE_SUCC = "更新APP版本配置成功";
    public static final String REGULATE_GOODS_SUCCESS = "云调剂成功";
    public static final String ACCEPET_SUCCESS = "提交成功";
    public static final String UPDATE_ACCOUNT_PWD_SUCC = "更改账户密码成功";
    public static final String ROLE_UP = "升级总代";
    public static final String ROLE_UP_NAME = "上级总代姓名";
    public static final String ROLE_UP_PHONE = "上级总代电话";
//    public static final String ROLE = "下级总代";
    public static final String ROLE_NAME = "总代";
    public static final String ROLE_PHONE = "电话";

    public static final String ROLE_SEX = "性别";
    public static final String ROLE_PROVINCE = "省份";
    public static final String ROLE_CITY = "城市";
    public static final String ROLE_AREA = "地区";
    public static final String ROLE_FULL_TIME = "是否兼职";

    public static final String ROLE_CAUSE = "从事的事业";
    public static final String ROLE_UP_TYPE = "为下级升级的方式";
    public static final String ROLE_AGE = "年龄段";

    public static final String ROLE_IF_OTHER = "是否从事其他微商";
    public static final String ROLE_TIME_UP= "升级成总代花费的时间 (月)";
    public static final String ROLE_TRAIN = "是否参加过大型培训";






    /**
     * RedisKey
     */
    public static final String MMALL_REDIS_TOKEN_USER_KEY="userOauth:mmall_redis_token_user_key_";
    public static final String MMALL_REDIS_USER_MUID_KEY="userMuid:mmall_redis_muid_user_key_";
    public static final String MMALL_REDIS_MSGID_KEY="MessageJpush:mmall_redis_msgid_key_";
    public static final int MAX_TOKEN_SIZE = 2;
    public static final String MMALL_ADMIN_REDIS_TOKEN_USER_KEY="admin:admin_redis_token_user_key_";
    public static final String SHRIO_SESSION_KEY="SHIRO_SESSION:";

    /**
     * SF地址分词
     */
    public static final String sfUrl = "https://ucmp.sf-express.com/cx-wechat-order/order/address/intelAddressResolution";

//    public static final String sfUrl = "http://ucmp.sf-express.com/cx-wechat-order/order/address/intelAddressResolution";

    public static final String APIKey ="RcGcWs8uNhKstcFNgKhBD1kz";

    public static final String AppID ="11779979";

    public static final String SerectKey ="U3uWNMKnbX4fZFZjE8ya4NVoO2gUf8aE";

    public static final String MoneyPattern ="\\-{0,}[1-9][0-9]{0,}\\.[0-9]{2}";

    public static final String DatePattern1="(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";

    public static final  String DatePattern2="(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})/(((0[13578]|1[02])/(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)/(0[1-9]|[12][0-9]|30))|(02/(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))/02/29)";

    /**
     * 请求 token
     */
    public static final String GRANT_TYPE = "password";

    public static final String CLIENT_ID = "mft_password";

    public static final String CLIENT_SECRET ="K2QzPPz3fqQNEnsbwupD1b1IDPPg0RfkdWalXysL7w2";

}

