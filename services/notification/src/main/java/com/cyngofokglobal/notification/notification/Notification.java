package com.cyngofokglobal.notification.notification;

import com.cyngofokglobal.notification.kafka.order.cancelorder.OrderCancellation;
import com.cyngofokglobal.notification.kafka.order.OrderConfirmation;
import com.cyngofokglobal.notification.kafka.payment.PaymentConfirmation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {

    @Id
    private String id;
    private NotificationType type;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private OrderCancellation orderCancellation;
    private PaymentConfirmation paymentConfirmation;
}
