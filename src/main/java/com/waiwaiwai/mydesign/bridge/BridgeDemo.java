package com.waiwaiwai.mydesign.bridge;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/23 13:17
 * @Description: 桥接模式
 */
public class BridgeDemo {

    public void test() throws ClassNotFoundException, SQLException {
        // 加载及注册MySQL的驱动,(里面有个静态代码块,注册到DriverManager里面)
        // 把他注册进了DriverManager里面
//        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver"); // com.mysql.jdbc.Driver 被弃用了
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        // DriverManager有个静态代码块,加载注册进来的驱动对象.
        String url = "jdbc:mysql://192.168.0.142:3306/smart?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&user=root&password=root";
        Connection con = DriverManager.getConnection(url);
        Statement statement = con.createStatement();
    }


    @Test
    public void test1() {
        method1();
        System.out.println("方法都执行完后会返回这里");
    }

    private Integer method1() {
        int i = 123;
        method2();

        System.out.println("第一个方法");
        return i;
    }

    private void method2() {
        System.out.println("第二个方法");
    }

    // che
    // niao
    // lu
    // jiao tong gong ju
    // travel
    // 根据紧急程度使用不同的交通工具





}
