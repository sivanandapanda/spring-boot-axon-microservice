package com.example.orders;

import com.example.orders.core.errorhandling.OrdersServiceEventsErrorHandler;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class OrdersServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdersServiceApplication.class);
    }

    @Autowired
    public void configure(EventProcessingConfigurer configure) {
        configure.registerListenerInvocationErrorHandler("order-group", conf -> new OrdersServiceEventsErrorHandler());
        //configure.registerListenerInvocationErrorHandler("order-group", conf -> PropagatingErrorHandler.instance());
    }
}
