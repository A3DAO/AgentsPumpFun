package com.agent.provider.model.db;

import com.agent.common.model.BaseDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * entity
 *
 * @author lll
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)

@TableName("t_chat")


public class ChatDO extends BaseDO {


    /**
     * chat_id
     */
    private String chatId;

    private String sendAgentId;

    private String receiverAgentId;

    private String message;


}
