package com.mandy.api.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mandy.api.ctrl.Eg4ServerEndpoint;

@ConditionalOnWebApplication
@Configuration
public class WebSocketConfig {
	
    @Bean
    public Eg4ServerEndpoint eg2ServerEndpoint() {
        return new Eg4ServerEndpoint();
    }

}
