package org.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootApplication
public class Main {

    private static final List<Contact> CONTACTS = List.of(
            new Contact("John", "Doe", "john.doe@example.com", "123-456-7890"),
            new Contact("Jane", "Smith", "jane.smith@example.com", "234-567-8901"),
            new Contact("Michael", "Johnson", "michael.johnson@example.com", "345-678-9012"),
            new Contact("Emily", "Davis", "emily.davis@example.com", "456-789-0123"),
            new Contact("David", "Wilson", "david.wilson@example.com", "567-890-1234"),
            new Contact("Sophia", "Martinez", "sophia.martinez@example.com", "678-901-2345"),
            new Contact("Daniel", "Garcia", "daniel.garcia@example.com", "789-012-3456"),
            new Contact("Olivia", "Anderson", "olivia.anderson@example.com", "890-123-4567"),
            new Contact("Matthew", "Taylor", "matthew.taylor@example.com", "901-234-5678"),
            new Contact("Ava", "Thomas", "ava.thomas@example.com", "012-345-6789")
    );

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    @Transactional
    public CommandLineRunner demo(ContactRepository contactRepository) {
        return args -> {
            contactRepository.deleteAllInBatch();
            // Добавление контактов в базу данных
            contactRepository.saveAll(CONTACTS);

            // Обновление email и телефона для тестирования
            contactRepository.updateContactByEmail("new.email@example.com", 1L);
            contactRepository.updateContactByPhone("098-765-4321", 1L);

            // Получение всех контактов
            List<Contact> contacts = contactRepository.findAll();
            contacts.forEach(System.out::println);

            // Удаление контакта по ID
            contactRepository.deleteById(1L);
        };
    }
}
