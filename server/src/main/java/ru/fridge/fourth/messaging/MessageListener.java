package ru.fridge.fourth.messaging;

import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Прослушиватель jms сообщений
 */
@Component
public class MessageListener {
    private final JmsTemplate jmsTemplate;
    private final String queueName;
    private final ScheduledExecutorService scheduler;

    /**
     * Конструктор слушателя сообщений
     * @param jmsTemplate   Шаблон для работы с jms
     * @param queueName     Название очереди для работы
     */
    @Autowired
    public MessageListener(JmsTemplate jmsTemplate, @Value("${queue.name}") String queueName) {
        this.jmsTemplate = jmsTemplate;
        this.queueName = queueName;
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    /**
     * Метод получения сообщения
     * @return Сообщение в виде строки
     */
    public String receiveMessage(){
        try {
            return (String) jmsTemplate.receiveAndConvert(queueName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Метод начала прослушивания сообщений
     */
    public void startReceivingMessages() {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                String receivedMessage = receiveMessage();
                System.out.println(Objects.requireNonNullElse(receivedMessage, "Нет новых сообщений"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 200, TimeUnit.MILLISECONDS);
    }

    /**
     * Метод окончания прослушивания сообщений
     */
    @PreDestroy
    public void stopReceivingMessages() {
        scheduler.shutdown();
        try {
            scheduler.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}