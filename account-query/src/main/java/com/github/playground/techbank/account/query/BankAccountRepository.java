package com.github.playground.techbank.account.query;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

	Optional<BankAccount> findByAccountHolder(String accountHolder);

	List<BankAccount> findByBalanceGreaterThan(BigDecimal balance);

	List<BankAccount> findByBalanceLessThan(BigDecimal balance);

}
