package com.agent.provider.service.impl;

import com.agent.api.model.AgentStatusVO;
import com.agent.api.model.AgentVO;
import com.agent.common.constants.BizCodeEnum;
import com.agent.common.exception.BizException;
import com.agent.common.model.BaseResponse;
import com.agent.provider.mapper.AgentMapper;
import com.agent.provider.model.db.AgentDO;
import com.agent.provider.service.AgentService;
import com.agent.provider.util.UUIDUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 出入金
 *
 * @author lll
 */
@Slf4j
@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    private AgentMapper agentMapper;

    @Override
    public BaseResponse<AgentVO> AddAgent(AgentVO agentVO) {
        if (agentVO == null) {
            throw new BizException(BizCodeEnum.BIZ_ERROR_EXCEPTION_REQUEST);
        }
        if (StringUtils.isEmpty(agentVO.getAgentId())) {
            agentVO.setAgentId(UUIDUtil.UUID());
        }
        if (StringUtils.isEmpty(agentVO.getCharacterId())) {
            agentVO.setCharacterId(UUIDUtil.UUID());
        }
        if (StringUtils.isEmpty(agentVO.getCharacterName())) {
            throw new BizException(BizCodeEnum.BIZ_ERROR_EMPTY_CHARACTER);
        }
        if (StringUtils.isEmpty(agentVO.getModelProvider())) {
            throw new BizException(BizCodeEnum.BIZ_ERROR_EMPTY_MODEL);
        }

        final AgentDO newAgentDO = convert2AgentDO(agentVO);
        newAgentDO.setStatus("");
        newAgentDO.setVersion(0L);
        System.out.println(newAgentDO);
        agentMapper.insert(newAgentDO);
        return BaseResponse.of(agentVO);
    }

    @Override
    public BaseResponse<AgentVO> getAgent(String agentId) {
        if (StringUtils.isEmpty(agentId)) {
            throw new BizException(BizCodeEnum.BIZ_ERROR_EXCEPTION_REQUEST);
        }
        final LambdaQueryWrapper<AgentDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AgentDO::getAgentId, agentId);
        final AgentDO agentDO = agentMapper.selectOne(queryWrapper);
        if (agentDO == null) {
            throw new BizException(BizCodeEnum.BIZ_ERROR_NOT_EXISTING_AGENT);
        }
        final AgentVO agentVO = convert2AgentVO(agentDO);
        return BaseResponse.of(agentVO);
    }

    @Override
    public BaseResponse<List<AgentVO>> getAgents() {
        List<AgentDO> agentDOList = agentMapper.selectList(null);
        return BaseResponse.of(convert2AgentVOList(agentDOList));
    }

    @Override
    public BaseResponse updateAgentStatus(AgentStatusVO agentStatusVO) {
        final LambdaQueryWrapper<AgentDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AgentDO::getAgentId, agentStatusVO.getAgentId());
        final AgentDO agentDO = agentMapper.selectOne(queryWrapper);
        if (agentDO == null) {
            throw new BizException(BizCodeEnum.BIZ_ERROR_NOT_EXISTING_AGENT);
        }
        agentDO.setStatus(agentStatusVO.getStatus());
        agentMapper.updateById(agentDO);
        return BaseResponse.success();
    }

    @Override
    public BaseResponse<AgentStatusVO> getAgentStatus(String agentId) {
        final LambdaQueryWrapper<AgentDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AgentDO::getAgentId, agentId);
        final AgentDO agentDO = agentMapper.selectOne(queryWrapper);
        if (agentDO == null) {
            throw new BizException(BizCodeEnum.BIZ_ERROR_NOT_EXISTING_AGENT);
        }

        AgentStatusVO agentStatusVO = new AgentStatusVO();
        agentStatusVO.setAgentId(agentDO.getAgentId());
        agentStatusVO.setStatus(agentDO.getStatus());
        return BaseResponse.of(agentStatusVO);
    }


    private AgentVO convert2AgentVO(AgentDO agentDO) {
        final AgentVO agentVO = new AgentVO();
        agentVO.setAgentId(agentDO.getAgentId());
        agentVO.setAgentName(agentDO.getAgentName());
        agentVO.setCharacterId(agentDO.getCharacterId());
        agentVO.setCharacterName(agentDO.getCharacterName());
        agentVO.setUserName(agentDO.getUserName());
        agentVO.setSystem(agentDO.getSystemValue());
        agentVO.setModelProvider(agentDO.getModelProvider());
        agentVO.setBio(agentDO.getBio());
        agentVO.setLore(agentDO.getLore());
        agentVO.setKnowledge(agentDO.getKnowledge());
        agentVO.setType(agentDO.getType());
        agentVO.setStatus(agentDO.getStatus());
        return agentVO;
    }

    private AgentDO convert2AgentDO(AgentVO agentVO) {
        final AgentDO agentDO = new AgentDO();
        agentDO.setAgentId(agentVO.getAgentId());
        agentDO.setAgentName(agentVO.getAgentName());
        agentDO.setCharacterId(agentVO.getCharacterId());
        agentDO.setCharacterName(agentVO.getCharacterName());
        agentDO.setUserName(agentVO.getUserName());
        agentDO.setSystemValue(agentVO.getSystem());
        agentDO.setModelProvider(agentVO.getModelProvider());
        agentDO.setBio(agentVO.getBio());
        agentDO.setLore(agentVO.getLore());
        agentDO.setKnowledge(agentVO.getKnowledge());
        agentDO.setType(agentVO.getType());
        agentDO.setStatus(agentVO.getStatus());
        return agentDO;
    }

    private List<AgentVO> convert2AgentVOList(List<AgentDO> agentDOList) {
        final List<AgentVO> agentVOList = new ArrayList<>();
        for (AgentDO agentDO : agentDOList) {
            final AgentVO agentVO = convert2AgentVO(agentDO);
            agentVOList.add(agentVO);
        }
        return agentVOList;
    }
}
