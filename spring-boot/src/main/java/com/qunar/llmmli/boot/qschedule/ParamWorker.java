package com.qunar.llmmli.boot.qschedule;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import qunar.tc.qschedule.config.QSchedule;
import qunar.tc.schedule.Parameter;
import qunar.tc.schedule.TaskHolder;
import qunar.tc.schedule.TaskMonitor;

import java.util.Date;

/**
 * @author llmm.li
 * @version v1.0.0
 * @since 2017/10/4
 */
@Component
public class ParamWorker {

    @QSchedule("flight.jy.sharings.param")
    public void run(Parameter parameter) {
        TaskMonitor monitor = TaskHolder.getKeeper();
        Logger logger = monitor.getLogger();
        logger.info("jobName : {} ", parameter.getJobName());
        logger.info("task  create date : {} ", parameter.getCreatedDate());
        logger.info("string param:{}", parameter.getString("str"));
        logger.info("number param:{}", parameter.getProperty("num", Integer.class));
        logger.info("date param:{}", parameter.getProperty("date", Date.class));
    }
}
