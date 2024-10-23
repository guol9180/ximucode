package com.ximu.init.constant;

/**
 * @author: 析木
 * @description: 通用常量，接口中属性默认是 public static final
 */
public interface CommonConstant {

    /**
     * 升序
     */
    String SORT_ORDER_ASC = "asc";

    /**
     * 降序
     */
    String SORT_ORDER_DESC = "desc";

    /**
     * 盐值
     */
    String SALT = "ximu";

    /**
     * 默认头像
     */
    String DEFAULT_AVATAR = "https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png";

    /**
     * jwt 过期时间（默认 1小时过期）
     */
    Long TOKEN_EXPIRE_TIME = 60 * 60 * 1000L;

    /**
     * token 签名秘钥
     */
    String  TOKEN_SIGN_KEY = "LhcSm28oL4SFLbejQunGAAvIRE7rSWmM";
}
