package com.cyngofokglobal.notification.kafka;

import com.cyngofokglobal.notification.email.EmailService;
import com.cyngofokglobal.notification.kafka.order.cancelorder.OrderCancellation;
import com.cyngofokglobal.notification.kafka.order.OrderConfirmation;
import com.cyngofokglobal.notification.kafka.payment.PaymentConfirmation;
import com.cyngofokglobal.notification.notification.Notification;
import com.cyngofokglobal.notification.notification.NotificationRepository;
import com.cyngofokglobal.notification.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.cyngofokglobal.notification.notification.NotificationType.PAYMENT_CONFIRMATION;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository repository;
    private final EmailService emailService;
    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(format("Consuming the message from payment-topic Topic:: %s", paymentConfirmation));
        repository.save(
                Notification.builder()
                        .type(PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );

        var customerName = paymentConfirmation.customerFirstname() + " " + paymentConfirmation.customerLastname();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );

    }
    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationSuccessNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(format("Consuming the message from order-topic Topic:: %s", orderConfirmation));
        repository.save(
                Notification.builder()
                        .type(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        var customerName = orderConfirmation.customer().firstname() + " " + orderConfirmation.customer().lastname();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );

    }

    @KafkaListener(topics = "cancel-order-topic")
    public void consumeCancelOrderNotification(OrderCancellation orderCancellation) throws MessagingException {
        log.info(format("Consuming the message from cancel-order-topic Topic:: %s", orderCancellation));
        repository.save(
                Notification.builder()
                        .type(NotificationType.CANCEL_ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderCancellation(orderCancellation)
                        .build()
        );

        var customerName = orderCancellation.customer().firstname() + " " + orderCancellation.customer().lastname();
        emailService.sendCancelOrderConfirmationEmail(
                orderCancellation.customer().email(),
                customerName,
                orderCancellation.refundedAmount().getAmount(),
                orderCancellation.orderReference()
        );
    }
}
