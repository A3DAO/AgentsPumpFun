package com.agent.api;

import com.agent.api.model.UserVO;
import com.agent.common.model.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * user api
 *
 * @author lll
 **/
public interface UserApi {

    /**
     * 增加用户信息
     */
    @PostMapping(value = "/api/v1/user")
    public BaseResponse<UserVO> addUser(@RequestBody UserVO userVO);

    /**
     * 查询用户信息
     *
     * @param address
     * @return
     */
    @GetMapping(value = "/api/v1/user")
    public BaseResponse<UserVO> getUser(@RequestParam String address);

}
