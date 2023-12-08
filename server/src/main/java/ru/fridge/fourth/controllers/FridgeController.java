package ru.fridge.fourth.controllers;

import ru.fridge.fourth.models.Fridge;
import ru.fridge.fourth.services.FridgeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Контроллер для работы с холодильниками
 */
@Controller
@RequestMapping("/")
public class FridgeController {
    /**
     * Логер для вывода ошибок
     */
    private static final Logger logger = LoggerFactory.getLogger(FridgeController.class);

    /**
     * Сервис для работы с холодильниками
     */
    private final FridgeService fridgeService;

    /**
     * Конструктор для внедрения сервиса по работе с холодильниками
     * @param fridgeService Сервис для работы с холодильниками
     */
    @Autowired
    public FridgeController(FridgeService fridgeService) {
        this.fridgeService = fridgeService;
    }

    /**
     * Показывает список всех холодильников
     * @param name  Параметр для фильтрации холодильников
     * @param model Модель для передачи данных в представление
     * @return      Имя представления для отображения
     */
    @GetMapping("/")
    public String listFridges(@RequestParam(name = "name", required = false) String name, Model model) {
        model.addAttribute("currentPage", "index");
        if (name != null) {
            model.addAttribute("fridge", fridgeService.filterByName(name));
        } else {
            model.addAttribute("fridge", fridgeService.findAll());
        }
        return "index";
    }

    /**
     * Показывает детали о холодильнике по его идентификатору
     * @param id    Идентификатор холодильника
     * @param model Модель для передачи данных в представление
     * @return      Имя представления для отображения
     */
    @GetMapping("fridge/{id}")
    public String viewFridgeDetails(@PathVariable("id") int id, Model model) {
        model.addAttribute("currentPage", "details");
        Fridge fridge = fridgeService.findOne(id);
        if (fridge != null) {
            model.addAttribute("fridge", fridge);
            return "show";
        } else {
            logger.error("Холодильник с id {} не найден", id);
            model.addAttribute("error", "Fridge not found");
            return "error";
        }
    }

    /**
     * Форма для редактирования холодильника
     * @param id    Идентификатор холодильника для редактирования
     * @param model Модель для передачи данных в представление
     * @return      Имя представления для отображения
     */
    @GetMapping("fridge/{id}/edit")
    public String editFridgeForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("currentPage", "edit");
        Fridge fridge = fridgeService.findOne(id);
        if (fridge != null) {
            model.addAttribute("fridge", fridge);
            return "edit";
        } else {
            logger.error("Ошибка редактирования несуществующего холодильника с id {}", id);
            return "error";
        }
    }

    /**
     * Форма для создания новойго холодильника
     * @param model Модель для передачи данных в представление
     * @return      Имя представления для отображения
     */
    @GetMapping("/new")
    public String newFridgeForm(Model model) {
        model.addAttribute("currentPage", "new");
        model.addAttribute("fridge", new Fridge());
        return "new";
    }
    /**
     * Создает новую запись о холодильнике
     * @param fridge                Создаваемый холодильник
     * @param bindingResult         Результат привязки для обработки ошибок
     * @param model                 Модель для передачи данных в представление
     * @param redirectAttributes    Атрибуты для передачи данных при перенаправлении
     * @return                      Перенаправление на список всех предметов
     */
    @PostMapping
    public String createClothes(@ModelAttribute("fridges") @Valid Fridge fridge,
                                BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("currentPage", "new");
            return "new";
        }
        fridgeService.save(fridge);
        redirectAttributes.addFlashAttribute("message", "Холодильник успешно добавлен");
        return "redirect:/";
    }

    /**
     * Обновляет существующую запись холодильника
     * @param fridge        Холодильник для обновления
     * @param bindingResult Результат привязки для обработки ошибок
     * @param id            Идентификатор холодильника для обновления
     * @param model         Модель для передачи данных в представление
     * @return              Перенаправление на список холодильников
     */
    @PutMapping("/{id}")
    public String updateClothes(@ModelAttribute("fridges") @Valid Fridge fridge,
                                BindingResult bindingResult, @PathVariable("id") int id, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("currentPage", "edit");
            return "edit";
        }
        if (fridgeService.doesNotExist(id)) {
            logger.error("Несуществующий id {}", id);
            return "error";
        }
        fridgeService.update(id, fridge);
        return "redirect:/";
    }

    /**
     * Удаляет запись о холодильнике
     * @param id    Идентификатор холодильника для удаления
     * @return      Перенаправление на список холодильников
     */
    @DeleteMapping("/{id}")
    public String deleteFridge(@PathVariable("id") int id) {
        if (fridgeService.doesNotExist(id)) {
            logger.error("Несуществующий id {}", id);
            return "error";
        }
        fridgeService.delete(id);
        return "redirect:/";
    }
}