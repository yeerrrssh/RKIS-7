package ru.fridge.rest;

/**
 * Класс предмета одежды
 */
public class Fridge {
    /**
     * Идентификатор холодильника
     */
    private int id;

    /**
     * Модель холодильника
     */
    private String model;

    /**
     * Бренд холодильника
     */
    private String brand;

    /**
     * Цвет холодильника
     */
    private String color;

    /**
     * Стоимость холодильника
     */
    private double price;

    /**
     * Количество холодильников
     */
    private int quantity;

    /**
     * Конструктор класса без параметров
     *
     *
     */
    public Fridge(String model, String brand, String color, double price, int quantity){
        this.model = model;
        this.brand = brand;
        this.color = color;
        this.price = price;
        this.quantity = quantity;
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