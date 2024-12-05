package com.agent.provider.model.db;

import com.agent.common.model.BaseDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户表 entity
 *
 * @author lll
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)

@TableName("t_user")


public class UserDO extends BaseDO {


    /**
     * 用户
     */
    private String address;

    /**
     * 名称
     */
    private String name;


}
