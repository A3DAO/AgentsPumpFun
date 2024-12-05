package com.agent.provider.mapper;

import com.agent.provider.model.db.AgentDO;
import com.agent.common.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* agent表 Mapper 接口
*
* @author lll
*/
    @Mapper
    @Repository
    public interface AgentMapper extends BaseMapperX<AgentDO> {

    }

