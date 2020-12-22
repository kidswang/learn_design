package com.waiwaiwai.mydesign.proxy;

import com.waiwaiwai.mydesign.proxy.entity.UserVo;

public interface IUserController {

    UserVo login(String telephone, String password);

    UserVo register(String telephone, String password);

}
