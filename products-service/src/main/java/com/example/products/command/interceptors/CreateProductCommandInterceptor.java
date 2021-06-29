package com.example.products.command.interceptors;

import com.example.products.command.CreateProductCommand;
import com.example.products.core.data.ProductLookupRepository;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;

@Component
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateProductCommandInterceptor.class);

    @Autowired
    private ProductLookupRepository productLookupRepository;

    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> list) {
        return (index, command) -> {

            LOGGER.info("Intercepted command: " + command.getPayloadType());

            if (CreateProductCommand.class.equals(command.getPayloadType())) {
                CreateProductCommand payload = (CreateProductCommand) command.getPayload();

                if(null != productLookupRepository.findByProductIdOrTitle(payload.getProductId(), payload.getTitle())) {
                    throw new IllegalStateException(String.format("Product with productId %s or title %s already exists",
                            payload.getProductId(), payload.getTitle()));
                }
            }

            return command;
        };
    }
}
