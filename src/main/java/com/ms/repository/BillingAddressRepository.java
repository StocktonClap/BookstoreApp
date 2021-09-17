package com.ms.repository;

import com.ms.entities.BillingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingAddressRepository  extends JpaRepository<BillingAddress, Integer> {
}
