package ru.fridge.fourth.repository;

import ru.fridge.fourth.models.Fridge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для работы с холодильниками
 */
@Repository
public interface FridgeRepository extends JpaRepository<Fridge, Integer> {
    /**
     * Находит все записи холодильников, имя которых содержит заданную подстроку
     */
    List<Fridge> findByNameContains(String name);
}
