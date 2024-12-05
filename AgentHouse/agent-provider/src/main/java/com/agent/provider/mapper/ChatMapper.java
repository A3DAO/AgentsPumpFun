package com.agent.provider.mapper;

import com.agent.common.mapper.BaseMapperX;
import com.agent.provider.model.db.ChatDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Mapper 接口
 *
 * @author lll
 */
@Mapper
@Repository
public interface ChatMapper extends BaseMapperX<ChatDO> {

}

