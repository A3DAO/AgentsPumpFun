package com.agent.provider.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lll
 */
@Mapper
public interface AgentConvert extends BaseConvert {
    AgentConvert INSTANCE = Mappers.getMapper(AgentConvert.class);


}
