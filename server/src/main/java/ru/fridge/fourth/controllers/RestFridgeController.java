package ru.fridge.fourth.controllers;

import ru.fridge.fourth.models.Fridge;
import ru.fridge.fourth.services.FridgeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * Контроллер REST API
 */
@RestController
@RequestMapping("/api")
public class RestFridgeController {
    private static final Logger logger = LoggerFactory.getLogger(FridgeController.class);

    private final FridgeService fridgeService;

    /**
     * Конструктор класса RestFridgeController
     * @param fridgeService Сервис для работы с холодильниками
     */
    @Autowired
    public RestFridgeController(FridgeService fridgeService) {
        this.fridgeService = fridgeService;
    }

    /**
     * Возвращает все записи о холодильниках
     * @return Список холодильников
     */
    @GetMapping("/fridge")
    public List<Fridge> getAll() {
        return fridgeService.findAll();
    }

    /**
     * Возвращает холодильник с заданным id
     * @param id    Индекс холодильника
     * @return      Ответ на запрос
     */
    @GetMapping("/fridge/{id}")
    public ResponseEntity<Fridge> getById(@PathVariable("id") int id) {
        Fridge fridge = fridgeService.findOne(id);
        if (fridge != null) {
            return new ResponseEntity<>(fridge, HttpStatus.OK);
        } else {
            logger.error("Fridge with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Добавляет холодильник в базу данных
     * @param fridge    Новый холодильник
     * @return          Ответ на запрос
     */
    @PostMapping
    public ResponseEntity<Fridge> create(@RequestBody @Valid Fridge fridge) {
        fridgeService.save(fridge);
        return new ResponseEntity<>(fridge, HttpStatus.CREATED);
    }

    /**
     * Обновляет холодильник по индексу
     * @param fridge   Обновлённый холодильник
     * @param id        Индекс изменяемого холодильника
     * @return          Ответ на запрос
     */
    @PutMapping("/{id}")
    public ResponseEntity<Fridge> update(@RequestBody @Valid Fridge fridge, @PathVariable("id") int id) {
        if (fridgeService.doesNotExist(id)) {
            logger.error("Attempted to update non-existing fridge with id {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        fridgeService.update(id, fridge);
        return new ResponseEntity<>(fridge, HttpStatus.OK);
    }

    /**
     * Удаляет холодильник по индексу
     * @param id    Индекс удаляемого холодильника
     * @return      Ответ на запрос
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletefridge(@PathVariable("id") int id) {
        if (fridgeService.doesNotExist(id)) {
            logger.error("Attempted to delete non-existing fridge with id {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        fridgeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}}