package com.agent.provider.service;

import com.agent.api.model.AgentStatusVO;
import com.agent.api.model.AgentVO;
import com.agent.common.model.BaseResponse;

import java.util.List;

/**
 * user 服务
 *
 * @author lll
 */
public interface AgentService {

    /**
     * 增加agent
     *
     * @param agentVO
     * @return
     */
    BaseResponse<AgentVO> AddAgent(AgentVO agentVO);

    /**
     * 查询agent
     *
     * @param agentId
     * @return
     */
    BaseResponse<AgentVO> getAgent(String agentId);

    /**
     * 查询所有agent
     *
     * @return
     */
    BaseResponse<List<AgentVO>> getAgents();

    BaseResponse updateAgentStatus(AgentStatusVO agentStatusVO);

    BaseResponse<AgentStatusVO> getAgentStatus(String agentId);

}
