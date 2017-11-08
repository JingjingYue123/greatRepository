package com.djcps.crm.exception.component;

import com.djcps.crm.commons.msg.MsgTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

import static com.djcps.crm.exception.enums.ExceptionMsgEnum.RESOURCES_NOT_FOUNT;


/**
 * Created by TruthBean on 2017/6/21 13:05.
 */
@RestControllerAdvice
@RestController
public class ExceptionRestControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionRestControllerAdvice.class);

    @ExceptionHandler(value = Throwable.class)
    public Map<String, Object> error(Throwable exception) {
        logger.error("error catched in ExceptionHandler: " + exception.getMessage(), exception);
        return MsgTemplate.failureMsg(exception.getMessage());
    }

    @RequestMapping(value = "/404")
    public Map<String, Object> error404() {
        return MsgTemplate.failureMsg(RESOURCES_NOT_FOUNT);
    }

}
