package ru.fridge.fourth;

import ru.fridge.fourth.messaging.MessageListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Класс, запускающий работу программы
 */
@SpringBootApplication
public class Application {
    /**
     * Точка входа в приложение
     * @param args Аргументы командной строки
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}