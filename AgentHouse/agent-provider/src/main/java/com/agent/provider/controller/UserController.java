package com.agent.provider.controller;

import com.agent.api.UserApi;
import com.agent.api.model.UserVO;
import com.agent.common.model.BaseResponse;
import com.agent.provider.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * inner api controller
 *
 * @author lll
 **/
@Slf4j
@RestController
public class UserController implements UserApi {

    @Autowired
    private UserService userService;

    @Override
    public BaseResponse<UserVO> addUser(UserVO userVO) {
        return userService.addUser(userVO);
    }

    @Override
    public BaseResponse<UserVO> getUser(String address) {
        return userService.getUserByAddress(address);
    }
}
