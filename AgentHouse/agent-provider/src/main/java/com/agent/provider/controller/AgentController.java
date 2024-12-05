package com.agent.provider.controller;

import com.agent.api.AgentApi;
import com.agent.api.model.AgentStatusVO;
import com.agent.api.model.AgentVO;
import com.agent.common.model.BaseResponse;
import com.agent.provider.service.AgentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * inner api controller
 *
 * @author lll
 **/
@Slf4j
@RestController
public class AgentController implements AgentApi {

    @Autowired
    private AgentService agentService;

    @Override
    public BaseResponse<AgentVO> addAgent(AgentVO agentVO) {
        return agentService.AddAgent(agentVO);
    }

    @Override
    public BaseResponse<AgentVO> getAgent(String agentId) {
        return agentService.getAgent(agentId);
    }

    @Override
    public BaseResponse<List<AgentVO>> getAgents() {
        return agentService.getAgents();
    }

    @Override
    public BaseResponse updateAgentStatus(AgentStatusVO agentStatusVO) {
        return agentService.updateAgentStatus(agentStatusVO);
    }

    @Override
    public BaseResponse<AgentStatusVO> getAgentStatus(String agentId) {
        return agentService.getAgentStatus(agentId);
    }
}
