package com.epam.engx.cleancode.naming.task1;

import com.epam.engx.cleancode.naming.task1.thirdpartyjar.*;

import java.util.List;

public class DeliveryOrderServiceOrder implements ServiceOrder {

    private DeliveryService deliveryService;

    private OrderFulfilmentService orderFulfilmentService;

    public void submitOrder(Order order) {
        if (deliveryService.isDeliverable()) {
            List<Product> products = order.getProducts();
            orderFulfilmentService.fulfilProducts(products);
        } else {
            throw new NotDeliverableOrderException();
        }
    }

    public void setDeliveryService(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    public void setOrderFulfilmentService(OrderFulfilmentService orderFulfilmentService) {
        this.orderFulfilmentService = orderFulfilmentService;
    }
}
