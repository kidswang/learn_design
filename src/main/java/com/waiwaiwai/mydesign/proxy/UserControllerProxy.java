package com.waiwaiwai.mydesign.proxy;

import com.waiwaiwai.mydesign.proxy.entity.UserVo;
import org.junit.Test;

/**
 * @Author: wangzhenglei
 * @DateTime: 2020/12/21 16:17
 * @Description: 代理类
 */
public class UserControllerProxy implements IUserController{
    private UserController userController;

    public UserControllerProxy(UserController userController) {
        this.userController = userController;
    }

    @Override
    public UserVo login(String telephone, String password) {
        // 调用前的逻辑处理
        // 委托
        userController.login(telephone, password);

        // 调用后的逻辑处理
        return null;
    }

    @Override
    public UserVo register(String telephone, String password) {
        // 调用前的逻辑处理
        // 委托
        userController.register(telephone, password);

        // 调用后的逻辑处理
        return null;
    }

    // 使用
    @Test
    public void test() {
        // UserControllerProxy使用举例
        // 因为原始类和代理类实现相同的接口，是基于接口而非实现编程
        // 将UserController类对象替换为UserControllerProxy类对象，不需要改动太多代码
        IUserController userController = new UserControllerProxy(new UserController());

    }

}
