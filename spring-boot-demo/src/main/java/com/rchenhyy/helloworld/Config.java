package com.rchenhyy.helloworld;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;

import qunar.web.spring.handler.JsonBodyExceptionResolver;

/**
 * @author chenjiahua.chen
 * @version v1.0.0
 * @since 17/9/1
 */
@Configuration
public class Config {

    @Bean
    public HandlerExceptionResolver myResolver() {
        JsonBodyExceptionResolver resolver = new JsonBodyExceptionResolver();
        resolver.setOrder(Ordered.LOWEST_PRECEDENCE);
        return resolver;
    }
}
