package com.agent.common.remote;

import java.util.List;

import com.agent.common.remote.model.RequestVO;
import com.agent.common.remote.model.RspMsgVO;
import org.springframework.web.bind.annotation.RequestBody;

import com.agent.common.model.BaseResponse;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Headers("Content-Type: application/json")
public interface AgentTrumpApi {
    /**
     * 发送信息
     * @return
     */
    @RequestLine("POST /message")
    List<RspMsgVO> sendMessage(@RequestBody RequestVO requestVO);
}
