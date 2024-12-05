package com.agent.common.model;

import java.util.Date;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * 基础Dto
 *
 * @author lll
 */
@Data
@SuperBuilder
public class BaseDTO {

    /**
     * 自增Id
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
