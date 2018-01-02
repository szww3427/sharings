package com.qunar.llmmli.qschedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import qunar.api.pojo.node.JacksonSupport;
import qunar.tc.qschedule.config.QSchedule;
import qunar.tc.schedule.Parameter;

/**
 * @author llmm.li
 * @version v1.0.0
 * @since 2017/10/4
 */
@Component
public class ShardedWorker {
    private static Logger logger = LoggerFactory.getLogger(ProcessWorker.class);

    @QSchedule("flight.jy.sharings.sharded")
    public void run(Parameter parameter) {
        logger.info("shards num:{}",parameter.shards());
        logger.info("shard id:{}",parameter.shardId());
        logger.info(JacksonSupport.toJson(parameter));
    }
}
