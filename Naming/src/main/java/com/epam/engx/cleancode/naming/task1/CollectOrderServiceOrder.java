package com.epam.engx.cleancode.naming.task1;


import com.epam.engx.cleancode.naming.task1.thirdpartyjar.CollectionService;
import com.epam.engx.cleancode.naming.task1.thirdpartyjar.Message;
import com.epam.engx.cleancode.naming.task1.thirdpartyjar.NotificationManager;
import com.epam.engx.cleancode.naming.task1.thirdpartyjar.Order;

public class CollectOrderServiceOrder implements ServiceOrder {

    private CollectionService collector;
    private NotificationManager notification;

    public void submitOrder(Order order) {
        if (collector.isEligibleForCollection(order))
            notification.notifyCustomer(Message.READY_FOR_COLLECT, 4); // 4 - info notification level
        else
            notification.notifyCustomer(Message.IMPOSSIBLE_TO_COLLECT, 1); // 1 - critical notification level
    }

    public void setCollector(CollectionService collector) {
        this.collector = collector;
    }

    public void setNotification(NotificationManager notification) {
        this.notification = notification;
    }
}
