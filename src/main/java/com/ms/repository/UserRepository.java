package com.ms.repository;

import com.ms.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User getUserByUsername(String username);
    User getUserByEmail(String email);
    User getUserByLastName(String lastName);

}
