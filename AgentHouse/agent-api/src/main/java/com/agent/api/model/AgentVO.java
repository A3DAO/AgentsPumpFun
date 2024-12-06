package com.agent.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgentVO {


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

    private String system;

    private String modelProvider;

    private String bio;

    private String lore;

    private String knowledge;

    private String type;

    private String status;
}
