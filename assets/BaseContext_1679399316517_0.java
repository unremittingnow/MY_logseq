package com.itheima.reggie.common;

/**
 * @Author: Jiaqi Lv
 * @Date: 2023/3/21 19:46
 * @UpdateTime: 2023/3/21 19:46
 */
/**
 * 基于ThreadLocal封装工具类，用户保存和获取当前登录用户id
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();
//Long泛型：保存id
    //同一个线程为作用域
    /**
     * 设置值
     * @param id
     */
    public static void setCurrentId(Long id){
        //静态：因为这是一个工具方法
        threadLocal.set(id);
    }

    /**
     * 获取值
     * @return
     */
    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
