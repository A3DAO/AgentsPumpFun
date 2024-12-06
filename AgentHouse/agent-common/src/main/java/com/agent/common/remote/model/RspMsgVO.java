package com.agent.common.remote.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 用户信息
 *
 * @author lll
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RspMsgVO {

    private String user;

    private String text;

    private String action;

}
