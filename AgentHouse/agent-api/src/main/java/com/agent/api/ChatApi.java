package com.agent.api;

import com.agent.api.model.ChatPopularVO;
import com.agent.api.model.ChatVO;
import com.agent.common.model.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * chat api
 *
 * @author lll
 **/
public interface ChatApi {

    /**
     * 增加chat
     */
    @PostMapping(value = "/api/v1/chat")
    public BaseResponse<ChatVO> addChat(@RequestBody ChatVO chatVO);

    @GetMapping(value = "/api/v1/chatList")
    public BaseResponse<List<String>> getChatList();

    @GetMapping(value = "/api/v1/chats")
    public BaseResponse<List<ChatVO>> getChatList(@RequestParam String chatId);

    @GetMapping(value = "/api/v1/chat/popularity")
    public BaseResponse<List<ChatPopularVO>> getChatPopularity();

    @GetMapping(value = "/api/v1/chat/topic")
    public BaseResponse initTopic(@RequestParam String topic);

}
