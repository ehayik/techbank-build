package com.github.playground.techbank.account.query;

import com.github.playground.techbank.account.common.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AccountEventHandler {

	private final BankAccountRepository accountRepository;

	public void on(@NonNull AccountEvent event) {
		switch (event) {
			case AccountOpenedEvent evt -> on(evt);
			case AccountClosedEvent evt -> on(evt);
			case FundsDepositedEvent evt -> on(evt);
			case FundsWithdrawnEvent evt -> on(evt);
		}
	}

	private void on(AccountOpenedEvent event) {

		if (accountRepository.existsById(event.getId())) {
			throw new IllegalStateException("Account %s already exists".formatted(event.getId()));
		}

		var bankAccount = new BankAccount() //
				.setId(event.getId()) //
				.setBalance(event.getOpeningBalance()) //
				.setAccountHolder(event.getAccountHolder()) //
				.setCreationDate(event.getCreatedDate()) //
				.setAccountType(event.getAccountType()) //
				.setBalance(event.getOpeningBalance()); //

		accountRepository.save(bankAccount);
	}

	private void on(FundsDepositedEvent event) {
		accountRepository.findById(event.getId()).ifPresent(account -> {
			LOG.info("Depositing funds to account {}", account.getId());
			account.deposit(event.getAmount());
			accountRepository.save(account);
		});
	}

	private void on(FundsWithdrawnEvent event) {
		accountRepository.findById(event.getId()).ifPresent(account -> {
			LOG.info("Withdrawing funds from account {}", account.getId());
			account.withdraw(event.getAmount());
			accountRepository.save(account);
		});

	}

	private void on(AccountClosedEvent event) {
		accountRepository.deleteById(event.getId());
	}

}
