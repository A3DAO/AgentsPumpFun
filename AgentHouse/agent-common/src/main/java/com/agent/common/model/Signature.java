package com.agent.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * signature
 *
 * @author lll
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Signature {
    private String r;
    private String s;
    private Long v;
}
