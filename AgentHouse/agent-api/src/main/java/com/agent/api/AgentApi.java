package com.agent.api;

import com.agent.api.model.AgentStatusVO;
import com.agent.api.model.AgentVO;
import com.agent.common.model.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * agent api
 *
 * @author lll
 **/
public interface AgentApi {

    /**
     * 增加agent
     */
    @PostMapping(value = "/api/v1/agent")
    public BaseResponse<AgentVO> addAgent(@RequestBody AgentVO agentVO);

    /**
     * 查询agent
     *
     * @param agentId
     * @return
     */
    @GetMapping(value = "/api/v1/agent")
    public BaseResponse<AgentVO> getAgent(@RequestParam String agentId);

    /**
     * 查询所有agent
     *
     * @return
     */
    @GetMapping(value = "/api/v1/agents")
    public BaseResponse<List<AgentVO>> getAgents();

    @PostMapping(value = "/api/v1/status")
    public BaseResponse updateAgentStatus(@RequestBody AgentStatusVO agentStatusVO);

    @GetMapping(value = "/api/v1/status")
    public BaseResponse<AgentStatusVO> getAgentStatus(@RequestParam String agentId);

}
