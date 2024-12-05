package com.agent.provider.enums;

import lombok.Getter;

/**
 * 参数code
 *
 * @author lll
 */
@Getter
public enum ParaEnum {
    // 出金id
    BASE_WITHDRAW_ID,
    // 链上超时时间,min
    CHAIN_WITHDRAW_TIMEOUT,
    // 链下超时时间,min
    LOCAL_WITHDRAW_TIMEOUT,
    // privatekey password
    PRIVET_KEY_PASSWORD,
    // 上次计算rewardfee时间
    LAST_CALCULATE_REWARD_FEE_END_TIME,
    // rewardfee比例
    REWARD_FEE_RATE
}
