package com.agent.common.model.event;

import com.agent.common.constants.EventTypeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * base event
 *
 * @author lll
 */
@Data
public class BaseEventDTO<T> implements Serializable {

    private static final long serialVersionUID = -1407391338763464655L;

    private EventTypeEnum eventType;

    private T data;

}
