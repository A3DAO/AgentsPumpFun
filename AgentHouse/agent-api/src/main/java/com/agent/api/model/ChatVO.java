package com.agent.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 用户信息
 *
 * @author lll
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatVO {

    @NotBlank(message = "address must not be blank")
    @Length(max = 128, message = "Over Length")
    private String chatId;

    private String message;

    private String sendAgentId;

    private String receiverAgentId;

}