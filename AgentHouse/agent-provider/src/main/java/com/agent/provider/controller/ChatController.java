package com.agent.provider.controller;

import com.agent.api.ChatApi;
import com.agent.api.model.ChatPopularVO;
import com.agent.api.model.ChatVO;
import com.agent.common.model.BaseResponse;
import com.agent.provider.service.ChatService;
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
public class ChatController implements ChatApi {

    @Autowired
    private ChatService chatService;

    @Override
    public BaseResponse<ChatVO> addChat(ChatVO chatVO) {
        return chatService.addChat(chatVO);
    }

    @Override
    public BaseResponse<List<String>> getChatList() {
        return chatService.getChatIdList();
    }

    @Override
    public BaseResponse<List<ChatVO>> getChatList(String chatId) {
        return chatService.getChatList(chatId);
    }

    @Override
    public BaseResponse<List<ChatPopularVO>> getChatPopularity() {
        return chatService.getChatPopularity();
    }

    @Override
    public BaseResponse initTopic(String topic) {
        chatService.setTopic(topic);
        return BaseResponse.success();
    }
}
