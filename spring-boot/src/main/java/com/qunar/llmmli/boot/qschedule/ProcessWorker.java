package com.qunar.llmmli.boot.qschedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import qunar.tc.qschedule.config.QSchedule;
import qunar.tc.schedule.TaskHolder;
import qunar.tc.schedule.TaskMonitor;

/**
 * 调度平台监控任务
 *
 * @author llmm.li
 * @version v1.0.0
 * @since 2017/10/3
 */
@Component
public class ProcessWorker {
    private static Logger logger = LoggerFactory.getLogger(ProcessWorker.class);
    private int dataSize;

    @QSchedule("flight.jy.sharings.process")
    public void run() {
        // TaskHolder.getKeeper() 必须在调度方法里调用，并且不能在新起的线程中调用
        TaskMonitor monitor = TaskHolder.getKeeper();
        // 使用该日志对象写的日志会收集到调度中心
        Logger qsLogger = monitor.getLogger();
        /**
         * TaskMonitor 其他api自行尝试
         *
         * @see TaskMonitor
         */
        // 向qshedule输出日志
        qsLogger.info("work begin");
        int size = getDataSize();
        // 设置任务容量
        monitor.setRateCapacity(size);
        for (int i = 0; i < size; i++) {
            processData(i, qsLogger);
            // 增加任务进度
            monitor.addRate(1);
            // 设置任务进度
            monitor.setRate(i + 1);
        }
        qsLogger.info("work finish");
    }

    private int getDataSize() {
        return 100;
    }

    private void processData(int i, Logger logger) {
        logger.info("process data {}", i);
    }
}
