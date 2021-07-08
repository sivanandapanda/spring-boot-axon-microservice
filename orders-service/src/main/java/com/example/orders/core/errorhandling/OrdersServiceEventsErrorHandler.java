package com.example.orders.core.errorhandling;

import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.EventMessageHandler;
import org.axonframework.eventhandling.ListenerInvocationErrorHandler;

public class OrdersServiceEventsErrorHandler implements ListenerInvocationErrorHandler {

    @Override
    public void onError(Exception e, EventMessage<?> eventMessage, EventMessageHandler eventMessageHandler) throws Exception {
        throw e;
    }
}
