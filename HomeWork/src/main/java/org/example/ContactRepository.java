package org.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    // Метод для получения всех контактов
    List<Contact> findAll();

    // Метод для получения контакта по его ID
    Optional<Contact> findById(Long id);

    // Метод для добавления нового контакта
    Contact save(Contact contact);

    // Метод для обновления email контакта по его ID
    @Modifying
    @Transactional
    @Query("UPDATE Contact c SET c.email = :email WHERE c.id = :id")
    void updateContactByEmail(@Param("email") String email, @Param("id") Long id);

    // Метод для обновления номера телефона контакта по его ID
    @Modifying
    @Transactional
    @Query("UPDATE Contact c SET c.phone = :phone WHERE c.id = :id")
    void updateContactByPhone(@Param("phone") String phone, @Param("id") Long id);

    // Метод для удаления контакта по его ID
    void deleteById(Long id);
}
