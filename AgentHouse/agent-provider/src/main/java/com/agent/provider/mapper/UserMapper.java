package com.agent.provider.mapper;

import com.agent.provider.model.db.UserDO;
import com.agent.common.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* 用户表 Mapper 接口
*
* @author lll
*/
    @Mapper
    @Repository
    public interface UserMapper extends BaseMapperX<UserDO> {

    }

