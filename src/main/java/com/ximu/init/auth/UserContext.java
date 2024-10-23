package com.ximu.init.auth;

import com.ximu.init.model.entity.User;

/**
 * @author: 析木
 * @description: 使用 ThreadLocal 将 token中解析的信息进行保存，
 *  方便后续操作从ThreadLocal中获取信息并使用
 */
public class UserContext {

    public static ThreadLocal<User> threadLocal = new ThreadLocal<>();

    public static void setUserInfo(User user){
        threadLocal.set(user);
    }

    public static User getUserInfo(){
        return threadLocal.get();
    }

    public static void clear(){
        threadLocal.remove();
    }
}
