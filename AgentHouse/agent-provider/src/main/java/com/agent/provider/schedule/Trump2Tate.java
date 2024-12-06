package com.agent.provider.schedule;

import com.agent.common.model.BaseResponse;
import com.agent.common.remote.AgentEternalApi;
import com.agent.common.remote.AgentTateApi;
import com.agent.common.remote.AgentTrumpApi;
import com.agent.common.remote.model.RequestVO;
import com.agent.common.remote.model.RspMsgVO;
import com.agent.common.util.ResponseHelper;
import com.agent.provider.mapper.ChatMapper;
import com.agent.provider.model.db.ChatDO;
import com.agent.provider.service.AgentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Component
@EnableScheduling
public class Trump2Tate {

    @Autowired
    private AgentTrumpApi agentTrumpApi;
    @Autowired
    private AgentTateApi agentTateApi;
    @Autowired
    private AgentEternalApi agentEternalApi;
    @Autowired
    private ChatMapper chatMapper;

    private String initMessage="";
    final String trumpId="trump_agent";
    final String tateId="tate_agent";
    final String eternalId="eternal_agent";

    final String trump2Tate="trump2tate";
    final String tate2Eternal="tate2Eternal";
    final String eternal2Trump="eternal2trump";

    @Scheduled(fixedRate = 10000 )
    public void schedule() {
        try
        {
            if(StringUtils.isEmpty(initMessage))
            {
                initMessage=Topic.topic;
                if (StringUtils.isEmpty(initMessage))
                {
                    return;
                }
            }

            process();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void process() {
        RequestVO requestVO=new RequestVO();
        requestVO.setText(initMessage);
        requestVO.setUserId("user");
        requestVO.setUserName("User");
        final List<RspMsgVO> trumpMsgList=agentTrumpApi.sendMessage(requestVO);
        String trumpRspMessage=getMsg(trumpMsgList);

        toDb(eternal2Trump,eternalId,trumpId,initMessage);
        toDb(eternal2Trump,trumpId,eternalId,trumpRspMessage);

        //trump->tate
        requestVO.setText(trumpRspMessage);
        requestVO.setUserId("user");
        requestVO.setUserName("User");
        final List<RspMsgVO> tateMsgList=agentTateApi.sendMessage(requestVO);
        String tateRspMessage=getMsg(tateMsgList);

        toDb(trump2Tate,trumpId,tateId,trumpRspMessage);
        toDb(trump2Tate,tateId,trumpId,tateRspMessage);

        //tate->eternal
        requestVO.setText(tateRspMessage);
        requestVO.setUserId("user");
        requestVO.setUserName("User");
        final List<RspMsgVO> eternalMsgList=agentEternalApi.sendMessage(requestVO);
        initMessage=eternalMsgList.get(0).getText();

        toDb(tate2Eternal,tateId,eternalId,tateRspMessage);
        toDb(tate2Eternal,eternalId,tateId,initMessage);

    }
    private String getMsg(List<RspMsgVO> msgList) {
        if (msgList!=null&&!msgList.isEmpty())
        {
            return msgList.get(0).getText();
        }
        return "";
    }

    private void toDb(String chatId,String send,String receiver,String message){
        ChatDO chatDO=new ChatDO();
        chatDO.setChatId(chatId);
        chatDO.setSendAgentId(send);
        chatDO.setReceiverAgentId(receiver);
        chatDO.setMessage(message);
        chatMapper.insert(chatDO);
    }
}
