package com.agent.provider.service.impl;

import com.agent.api.model.ChatPopularVO;
import com.agent.api.model.ChatVO;
import com.agent.common.constants.BizCodeEnum;
import com.agent.common.exception.BizException;
import com.agent.common.model.BaseResponse;
import com.agent.common.util.BigDecimalUtil;
import com.agent.provider.mapper.ChatMapper;
import com.agent.provider.model.db.ChatDO;
import com.agent.provider.schedule.Topic;
import com.agent.provider.service.ChatService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 出入金
 *
 * @author lll
 */
@Slf4j
@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatMapper chatMapper;

    @Override
    public BaseResponse addChat(ChatVO chatVO) {
        if (chatVO == null) {
            throw new BizException(BizCodeEnum.BIZ_ERROR_EXCEPTION_REQUEST);
        }
        if (StringUtils.isEmpty(chatVO.getChatId())) {
            throw new BizException(BizCodeEnum.BIZ_ERROR_EXCEPTION_REQUEST);
        }
        if (StringUtils.isEmpty(chatVO.getMessage())) {
            throw new BizException(BizCodeEnum.BIZ_ERROR_EXCEPTION_REQUEST);
        }
        if (StringUtils.isEmpty(chatVO.getSendAgentId())) {
            throw new BizException(BizCodeEnum.BIZ_ERROR_EXCEPTION_REQUEST);
        }
        if (StringUtils.isEmpty(chatVO.getReceiverAgentId())) {
            throw new BizException(BizCodeEnum.BIZ_ERROR_EXCEPTION_REQUEST);
        }
        ChatDO chatDO = convert2DO(chatVO);
        chatMapper.insert(chatDO);
        return BaseResponse.success();
    }

    @Override
    public BaseResponse<List<String>> getChatIdList() {

        LambdaQueryWrapper<ChatDO> queryWrapper = new LambdaQueryWrapper<ChatDO>();
        queryWrapper.select(ChatDO::getChatId);

        final List<ChatDO> chatDOList = chatMapper.selectList(queryWrapper);
        List<String> chatIdList = new ArrayList<String>(chatDOList.stream().map(ChatDO::getChatId).collect(Collectors.toSet()));
        return BaseResponse.of(chatIdList);
    }

    @Override
    public BaseResponse<List<ChatVO>> getChatList(String ChatId) {
        LambdaQueryWrapper<ChatDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ChatDO::getChatId, ChatId).orderByAsc(ChatDO::getId);

        final List<ChatDO> chatDOList = chatMapper.selectList(queryWrapper);
        return BaseResponse.of(convert2VOList(chatDOList));
    }

    @Override
    public BaseResponse<List<ChatPopularVO>> getChatPopularity() {

        final List<ChatDO> chatDOList = chatMapper.selectList(null);
        final List<ChatPopularVO> popularVOS = new ArrayList<>();
        final Set<String> chatIdSet = chatDOList.stream().map(ChatDO::getChatId).collect(Collectors.toSet());
        Random random = new Random();
        for (String chatId : chatIdSet) {
            final ChatPopularVO popularVO = new ChatPopularVO();
            popularVO.setChatId(chatId);
            popularVO.setPopularity(BigDecimalUtil.getBigDecimal(BigDecimal.valueOf(random.nextDouble()), 4).toPlainString());
            popularVOS.add(popularVO);
        }
        return BaseResponse.of(popularVOS);
    }

    @Override
    public BaseResponse setTopic(String topic) {
        Topic.topic=topic;
        return BaseResponse.success();
    }

    private ChatDO convert2DO(ChatVO chatVO) {
        ChatDO chatDO = new ChatDO();
        chatDO.setChatId(chatVO.getChatId());
        chatDO.setSendAgentId(chatVO.getSendAgentId());
        chatDO.setReceiverAgentId(chatVO.getReceiverAgentId());
        chatDO.setMessage(chatVO.getMessage());
        return chatDO;
    }

    private ChatVO convert2VO(ChatDO chatDO) {
        ChatVO chatVO = new ChatVO();
        chatVO.setChatId(chatDO.getChatId());
        chatVO.setSendAgentId(chatDO.getSendAgentId());
        chatVO.setReceiverAgentId(chatDO.getReceiverAgentId());
        chatVO.setMessage(chatDO.getMessage());
        return chatVO;
    }

    private List<ChatVO> convert2VOList(List<ChatDO> chatDOList) {
        List<ChatVO> chatVOList = new ArrayList<>();
        for (ChatDO chatDO : chatDOList) {
            ChatVO chatVO = convert2VO(chatDO);
            chatVOList.add(chatVO);
        }
        return chatVOList;
    }
}
