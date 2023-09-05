package com.company.cryptoboot.repositories;

import com.company.cryptoboot.entities.Money;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoneyRepository extends JpaRepository<Money, Long> {
}
