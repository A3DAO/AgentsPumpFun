package com.agent.provider.model.db;

import com.agent.common.model.BaseDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * agent表 entity
 *
 * @author lll
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)

@TableName("t_agent")


public class AgentDO extends BaseDO {


    /**
     * agent id
     */
    private String agentId;

    /**
     * agent name
     */
    private String agentName;

    /**
     * 角色id
     */
    private String characterId;

    /**
     * 角色name
     */
    private String characterName;

    private String userName;

    private String systemValue;

    private String modelProvider;

    private String bio;

    private String lore;

    private String knowledge;

    private String type;

    private String status;


}
