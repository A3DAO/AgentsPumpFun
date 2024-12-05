package com.agent.provider.service;

import com.agent.api.model.ChatPopularVO;
import com.agent.api.model.ChatVO;
import com.agent.common.model.BaseResponse;

import java.util.List;

/**
 * user 服务
 *
 * @author lll
 */
public interface ChatService {

    /**
     * 增加chat信息
     *
     * @param chatVO
     * @return
     */
    BaseResponse addChat(ChatVO chatVO);

    BaseResponse<List<String>> getChatIdList();

    BaseResponse<List<ChatVO>> getChatList(String ChatId);

    BaseResponse<List<ChatPopularVO>> getChatPopularity();
}
