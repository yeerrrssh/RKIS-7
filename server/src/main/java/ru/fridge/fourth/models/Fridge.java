package ru.fridge.fourth.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "fridges")
public class Fridge {
    /**
     * Идентификатор холодильника
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Модель холодильника
     */
    @Size(min = 2, max = 30, message = "Модель должна содержать от 3 до 30 символов")
    @Column(name = "model")
    private String model;

    /**
     * Бренд холодильника
     */
    @Size(min = 2, max = 30, message = "Бренд должен содержать от 3 до 30 символов")
    @Column(name = "brand")
    private String brand;

    /**
     * Цвет холодильника
     */
    @Size(min = 2, max = 30, message = "Цвет должен содержать от 3 до 30 символов")
    @Column(name = "color")
    private String color;

    /**
     * Стоимость холодильника
     */
    @Min(value = 0, message = "Стоимость не может быть отрицательной")
    @Column(name = "price")
    private double price;

    /**
     * Количество холодильников
     */
    @Min(value = 0, message = "Количество не может быть отрицательным")
    @Column(name = "quantity")
    private int quantity;

    /**
     * Конструктор класса без параметров
     */
    public Fridge(){
        this.model = "Def";
        this.brand = "Def";
        this.color = "Def";
        this.price = 0;
        this.quantity = 1;
    }

    /**
     * Получает индекс холодильника
     * @return Индекс холодильника
     */
    public int getId() {
        return id;
    }

    /**
     * Устанавливает индекс холодильника
     * @param id Индекс холодильника
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Получает модель холодильника
     * @return Модель холодильника
     */
    public String getModel() {
        return model;
    }

    /**
     * Устанавливает модель холодильника
     * @param model Модель холодильника
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Получает цвет холодильника
     * @return Цвет холодильника
     */
    public String getColor() {
        return color;
    }

    /**
     * Устанавливает цвет холодильника
     * @param color цвет холодильника
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Получает бренд холодильника
     * @return Бренд холодильника
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Устанавливает бренд холодильника
     * @param brand Бренд холодильника
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Получает стоимость холодильника
     * @return Стоимость холодильника
     */
    public double getPrice() {
        return price;
    }

    /**
     * Устанавливает стоимость холодильника
     * @param price стоимость холодильника
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Получает количество холодильников
     * @return Количество холодильников
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Устанавливает количество холодильников
     * @param quantity Количество холодильников
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Создаёт строковое представление объекта
     * @return Строковое представление объекта
     */
    @Override
    public String toString() {
        return String.format(
                "ID: %d, Модель: %s, Бренд: %s, Цвет: %s, Стоимость: %.2f ₽, Количество: %d",
                id, model, brand, color, price, quantity
        );
    }
}

