### spring-boot初始化
1. SpringApplication初始化容器ConfigurableApplicationContext：refreshContext
2. AbstractApplicationContext注册工厂：invokeBeanFactoryPostProcessors
3. QScheduleAutoConfiguration注册：SchedulerProvider+AnnotationBean
4. AbstractApplicationContext完成初始化：finishBeanFactoryInitialization
4. SchedulerProvider初始化：MessageConsumerProvider
5. AnnotationBean implements BeanPostProcessor
扫描注解，生成TaskBean（可得到ResultWorker），添加到SchedulerProvider
6. SchedulerProvider的MessageConsumerProvider监听消息

### 处理消息
1. SchedulerProvider的ListenerHolder消费消息Message
2. 初始化DefaultTaskMonitor到TaskHolder
3. ResultWorker执行
