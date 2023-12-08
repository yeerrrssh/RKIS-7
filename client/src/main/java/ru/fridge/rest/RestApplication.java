package ru.fridge.rest;

import ru.fasterxml.jackson.core.JsonProcessingException;
import ru.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Запускает клиентскую часть приложения
 */
@SpringBootApplication
public class RestApplication {

    private RestTemplate restTemplate;

    /**
     * Точка входа в приложение
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

    /**
     * Шаблон для работы с REST API
     * @return REST шаблон
     */
    @Bean
    public RestTemplate restTemplate() {
        this.restTemplate = new RestTemplate();
        return this.restTemplate;
    }

    /**
     * Получает все холодильники с сервера
     */
    public void createGetRequest(){
        String url = "http://localhost:8080/api/fridge";
        ResponseEntity<Fridge[]> response = restTemplate.getForEntity(url, Fridge[].class);
        System.out.println("Response Code: " + response.getStatusCode());
        System.out.println("Response headers:");
        System.out.println(response.getHeaders());
        Fridge[] fridgesArray = response.getBody();
        System.out.println("Response body:");
        if (fridgesArray != null) {
            for (Fridge fridge : fridgesArray) {
                System.out.println(fridge);
            }
        }
    }

    /**
     * Получает холодильник с сервера по индексу
     * @param id Индекс запрашиваемого холодильника
     */
    public void createGetByIDRequest(Integer id){
        String url = "http://localhost:8080/api/fridge/" + id;
        try {
            ResponseEntity<Fridge> response = restTemplate.getForEntity(url, Fridge.class);
            System.out.println("Response Code: " + response.getStatusCode());
            System.out.println("Response headers:");
            System.out.println(response.getHeaders());
            System.out.println("Response body:");
            System.out.println(response.getBody());
        }
        catch (HttpClientErrorException e) {
            System.out.println("Response Code: " + e.getStatusCode());
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                System.out.println("Fridge with id " + id + " not found");
            } else {
                throw e;
            }
        }
    }

    /**
     * Отправляет холодильник на сервер
     * @param clothes Новый холодильник
     */
    public void createPostRequest(Fridge fridge){
        String url = "http://localhost:8080/api";
        ObjectMapper mapper = new ObjectMapper();
        try{
            String fridgesJson = mapper.writeValueAsString(fridge);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(fridgesJson, headers);
            ResponseEntity<Fridge> response = restTemplate.postForEntity(url, entity, Fridge.class);
            System.out.println("Response Status : " + response.getStatusCode());
            System.out.println("Response headers:");
            System.out.println(response.getHeaders());
            System.out.println("Response Body\n" + response.getBody());
        }
        catch (JsonProcessingException e){
            System.out.println("Impossible to process json file");
        }
    }

    /**
     * Отправляет модифиццированный холодильник на сервер
     * @param id        Индекс заменяемого холодильника
     * @param clothes   Изменённый холодильник
     */
    public void createPutRequest(Integer id, Fridge fridge){
        String url = "http://localhost:8080/api/" + fridge.getId();
        ObjectMapper mapper = new ObjectMapper();
        try{
            String fridgesJson = mapper.writeValueAsString(fridge);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(fridgesJson, headers);
            try{
                restTemplate.put(url, entity);
                System.out.println("Successfully edited an object\n" + fridge);
            }
            catch (HttpClientErrorException e) {
                if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                    System.out.println("Fridge with id " + id + " not found");
                } else {
                    throw e;
                }
            }
        }
        catch(JsonProcessingException e){
            System.out.println("Impossible to process json file");
        }
    }

    /**
     * Запрашивает удаление холодильник с сервера
     * @param id Индекс удаляемого холодильника
     */
    public void createDeleteRequest(Integer id){
        try {
            restTemplate.delete("http://localhost:8080/api/delete/" + id);

            System.out.println("Fridge with id " + id + " deleted successfully");
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                System.out.println("Fridge with id " + id + " not found");
            } else {
                throw e;
            }
        }
    }

    /**
     * Запускает работу приложения в коммандной строке
     * @return CommandLineRunner
     */
    @Bean
    public CommandLineRunner run() {
        return args -> {
            System.out.println("\n------------------------------ Getting all fridges ------------------------------");
            createGetRequest();
            System.out.println("\n--------------------------- Getting fridge by its ID ---------------------------");
            createGetByIDRequest(1);
            System.out.println("\n--------------------------- Posting new fridge ---------------------------");
            Fridge newFridge = new Fridge("SR-40", "LG", "white", 87000, 10);
            createPostRequest(newFridge);
            System.out.println("\n--------------------------- Editing fridge by its ID ---------------------------");
            Fridge fridge = new Fridge("S1-B1", "Panasonic", "black", 64000, 2);
            int id = 1;
            fridge.setId(id);
            createPutRequest(id, fridge);
            System.out.println("\n--------------------------- Deleting fridge by its ID ---------------------------");
            createDeleteRequest(2);
        };
    }
}