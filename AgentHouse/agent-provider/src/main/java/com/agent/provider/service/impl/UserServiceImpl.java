package com.agent.provider.service.impl;

import com.agent.api.model.UserVO;
import com.agent.common.constants.BizCodeEnum;
import com.agent.common.model.BaseResponse;
import com.agent.provider.mapper.UserMapper;
import com.agent.provider.model.db.UserDO;
import com.agent.provider.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 出入金
 *
 * @author lll
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseResponse<UserVO> addUser(UserVO user) {
        final LambdaQueryWrapper<UserDO> queryWrapper = new LambdaQueryWrapper<>();
        final UserDO userDO = userMapper.selectOne(queryWrapper);
        if (userDO != null) {
            return BaseResponse.failed(BizCodeEnum.BIZ_ERROR_EXISTING_USER.getCode(), BizCodeEnum.BIZ_ERROR_EXISTING_USER.getMessage());
        }
        UserDO newUser = new UserDO();
        newUser.setAddress(user.getAddress());
        newUser.setName(user.getName());
        userMapper.insert(newUser);
        return BaseResponse.success();
    }

    @Override
    public BaseResponse<UserVO> getUserByAddress(String address) {
        final LambdaQueryWrapper<UserDO> queryWrapper = new LambdaQueryWrapper<>();
        final UserDO userDO = userMapper.selectOne(queryWrapper);
        if (userDO == null) {
            return BaseResponse.failed(BizCodeEnum.BIZ_ERROR_NOT_EXISTING_USER.getCode(), BizCodeEnum.BIZ_ERROR_NOT_EXISTING_USER.getMessage());
        }
        UserVO userVO = new UserVO(userDO.getAddress(), userDO.getName());
        return BaseResponse.of(userVO);

    }


}
