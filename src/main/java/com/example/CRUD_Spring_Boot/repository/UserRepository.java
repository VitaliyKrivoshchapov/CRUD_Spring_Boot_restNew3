package com.example.CRUD_Spring_Boot.repository;

import com.example.CRUD_Spring_Boot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
//public interface UserRepository extends CrudRepository<User, Long> {
public interface UserRepository extends JpaRepository<User, Long> {
    /*волшебство boot - этот класс помогает иметь запросы к бд на имея их,
в этот класс  для которого мы реализуем JPA репозиторий передаем в дженерик имя класса и id/
// но можно испольщовать CrudRepository
*/

    //Optional<User> findByFirstName(String firstName);
    User findByFirstName(String username);
    User findByMail(String mail);
    User findUserById(Long id);


}

