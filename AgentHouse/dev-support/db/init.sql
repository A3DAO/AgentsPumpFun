-- user
CREATE TABLE `t_user` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `address` varchar(128) NOT NULL COMMENT '用户',
    `name` varchar(64) NOT NULL COMMENT '名称',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    `version` bigint DEFAULT '0' COMMENT '版本',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_t_user_address` (`address`),

) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '用户表';

-- agent
CREATE TABLE `t_agent` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `agent_id` varchar(128) NOT NULL COMMENT 'agent id',
    `agent_name` varchar(64) NOT NULL COMMENT 'agent name',
    `character_id` varchar(128) DEFAULT NULL COMMENT '角色id',
    `character_name` varchar(64) DEFAULT NULL COMMENT '角色name',
    `user_name` varchar(8) DEFAULT NULL COMMENT '',
    `system` varchar(8) DEFAULT NULL COMMENT '',
    `model_provider` varchar(64) DEFAULT NULL COMMENT '',
    `bio` text DEFAULT NULL COMMENT '',
    `lore` text DEFAULT NULL COMMENT '',
    `knowledge` varchar(64) DEFAULT NULL COMMENT '',
    `type` varchar(64) DEFAULT NULL COMMENT '',
    `status` varchar(64) DEFAULT NULL COMMENT '',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    `version` bigint DEFAULT '0' COMMENT '版本',
    PRIMARY KEY (`id`),
    UNIQUE KEY `ut_t_agent_agent_id` (`agent_id`),
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT 'agent表';

-- chat表
CREATE TABLE `t_chat` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `chat_id` varchar(64) NOT NULL COMMENT 'chat_id',
    `send_agent_id` varchar(64) NOT NULL COMMENT '',
    `receiver_agent_id` varchar(128) DEFAULT NULL COMMENT '',
    `message` varchar(64) DEFAULT NULL COMMENT '',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    `version` bigint DEFAULT '0' COMMENT '版本',
    PRIMARY KEY (`id`),
    index `idx_t_chat_chat_id` (`chat_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT 'chat表';
