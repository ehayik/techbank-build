package com.github.playground.techbank.account.query;

import com.github.playground.techbank.account.common.AccountClosedEvent;
import com.github.playground.techbank.account.common.AccountOpenedEvent;
import com.github.playground.techbank.account.common.FundsDepositedEvent;
import com.github.playground.techbank.account.common.FundsWithdrawnEvent;
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

	public void on(@NonNull AccountOpenedEvent event) {

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

	public void on(@NonNull FundsDepositedEvent event) {
		accountRepository.findById(event.getId()).ifPresent(account -> {
			LOG.info("Depositing funds to account {}", account.getId());
			account.deposit(event.getAmount());
			accountRepository.save(account);
		});
	}

	public void on(@NonNull FundsWithdrawnEvent event) {
		accountRepository.findById(event.getId()).ifPresent(account -> {
			LOG.info("Withdrawing funds from account {}", account.getId());
			account.withdraw(event.getAmount());
			accountRepository.save(account);
		});

	}

	public void on(@NonNull AccountClosedEvent event) {
		accountRepository.deleteById(event.getId());
	}

}
