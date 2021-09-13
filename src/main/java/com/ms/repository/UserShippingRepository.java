package com.ms.repository;

import com.ms.entities.UserShipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserShippingRepository extends JpaRepository<UserShipping, Integer> {
}
