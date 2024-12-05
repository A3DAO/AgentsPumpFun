package com.agent.provider.service;

import com.agent.api.model.UserVO;
import com.agent.common.model.BaseResponse;

/**
 * user 服务
 *
 * @author lll
 */
public interface UserService {

    /**
     * 增加用户信息
     *
     * @param user
     * @return
     */
    BaseResponse<UserVO> addUser(UserVO user);

    /**
     * 查询用户信息
     *
     * @param address
     * @return
     */
    BaseResponse<UserVO> getUserByAddress(String address);

}
