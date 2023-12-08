package ru.fridge.fourth.services;

import ru.fridge.fourth.models.Fridge;
import ru.fridge.fourth.repository.FridgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Сервис для работы с холодильниками
 */
@Service
@Transactional(readOnly = true)
public class FridgeService {
    private final FridgeRepository repository;

    /**
     * Внедряет зависимости репозитория холодильников
     * @param repository Репозиторий с холодильниками
     */
    @Autowired
    private FridgeService(FridgeRepository repository) {
        this.repository = repository;
    }

    /**
     * Возвращает список всех холодильников
     * @return Список холодильников
     */
    public List<Fridge> findAll() {
        return repository.findAll();
    }

    /**
     * Находит холодильник по id
     *
     * @param id    Id холодильника
     * @return      Найденный холодильник или null
     */
    public Fridge findOne(int id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Сохраняет новую запись о холодильнике
     *
     * @param fridge Холодильник для сохранения.
     */
    @Transactional
    public void save(Fridge fridge) {
        repository.save(fridge);
    }

    /**
     * Обновляет информацию о холодильнике
     *
     * @param id        Id холодильника
     * @param fridge    Холодильник для обновления
     */
    @Transactional
    public void update(int id, Fridge fridge) {
        fridge.setId(id);
        repository.save(fridge);
    }

    /**
     * Удаляет холодильник по идентификатору
     *
     * @param id Идентификатор холодильника для удаления
     */
    @Transactional
    public void delete(int id) {
        repository.deleteById(id);
    }

    /**
     * Проверяет холодильник в базе данных по идентификатору
     * @param id    Идентификатор холодильника для проверки
     * @return      True, если холодильника нет в базе, иначе false
     */
    public boolean doesNotExist(int id) {
        return !repository.existsById(id);
    }

    /**
     * Фильтрует холодильники по имени
     * @param name  Имя для фильтрации холодильников
     * @return      Список холодильников, соответствующей заданному имени
     */
    public List<Fridge> filterByName(String name) {
        return repository.findByNameContains(name);
    }
}