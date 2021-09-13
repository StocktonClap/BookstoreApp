package com.ms.repository;

import com.ms.entities.UserBilling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBillingRepository extends JpaRepository<UserBilling, Integer> {
}
