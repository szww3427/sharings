package com.qunar.llmmli.boot.qschedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import qunar.tc.qschedule.config.QSchedule;

/**
 * @author llmm.li
 * @version v1.0.0
 * @since 2017/10/3
 */
@Component
public class SimpleWorker {

    private static Logger logger = LoggerFactory.getLogger(SimpleWorker.class);

    @QSchedule("flight.jy.sharings.simple")
    public void run() {
        logger.info("simple qschedule test");
    }
}
