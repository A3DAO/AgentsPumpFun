package com.agent.common.remote;

import com.agent.common.model.BaseResponse;
import com.agent.common.remote.model.RequestVO;
import com.agent.common.remote.model.RspMsgVO;
import feign.Headers;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Headers("Content-Type: application/json")
public interface AgentTateApi {
    /**
     * 发送信息
     * @return
     */
    @RequestLine("POST /message")
    List<RspMsgVO> sendMessage(@RequestBody RequestVO requestVO);
}
