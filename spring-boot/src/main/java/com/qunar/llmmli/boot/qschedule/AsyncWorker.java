package com.qunar.llmmli.boot.qschedule;

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
public class AsyncWorker {
    @QSchedule("flight.jy.sharings.async")
    public void run() {
        // 要注意，这个一定要在执行任务的入口方法里调用，如果别的地方要用则传递过去
        final TaskMonitor monitor = TaskHolder.getKeeper();
        // 关闭自动报告任务完成
        monitor.autoAck(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 开启一个新线程处理
                try{
                    // 处理任务
                    // 任务处理完毕，汇报
                    monitor.finish();
                }catch(Exception e){
                    // 如果任务处理过程中出现异常，则调用 monitor.fail(e);
                    monitor.fail(e);
                }
            }
        }).start();
    }
}
