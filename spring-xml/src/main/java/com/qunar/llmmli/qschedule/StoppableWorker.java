package com.qunar.llmmli.qschedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import qunar.tc.qschedule.config.QSchedule;
import qunar.tc.schedule.TaskHolder;
import qunar.tc.schedule.TaskMonitor;

/**
 * @author llmm.li
 * @version v1.0.0
 * @since 2017/10/4
 */
@Component
public class StoppableWorker {
    private static Logger logger = LoggerFactory.getLogger(StoppableWorker.class);

    @QSchedule("flight.jy.sharings.stop")
    public void run() {
        final TaskMonitor taskMonitor = TaskHolder.getKeeper();
        while (!taskMonitor.isStopped()) {
            //执行业务逻辑
        }
        logger.info("stop");
    }
}
