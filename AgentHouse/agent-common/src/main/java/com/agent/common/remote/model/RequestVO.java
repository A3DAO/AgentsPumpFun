package com.agent.common.remote.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class RequestVO {

    private String text;
    private String userId;
    private String userName;

}
