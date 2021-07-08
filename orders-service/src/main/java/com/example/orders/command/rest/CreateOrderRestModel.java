package com.example.orders.command.rest;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class CreateOrderRestModel {
    @NotBlank(message = "Product Id is a required field")
    private String productId;

    @NotBlank(message = "Address Id is a required field")
    private String addressId;

    @Min(value = 1, message = "Quantity cannot be lower than 1")
    @Max(value = 100, message = "Quantity cannot be more than 100")
    private Integer quantity;
}
