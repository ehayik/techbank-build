package com.github.playground.techbank.account.cmd.domain;

import com.github.playground.techbank.cqrs.core.EventSourcingHandler;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
public final class AccountService {

	private final EventSourcingHandler<AccountAggregate> eventSourcingHandler;

	public void open(@NonNull OpenAccountCommand command) {
		eventSourcingHandler.save(new AccountAggregate(command));
	}

	private AccountAggregate getAccount(@NonNull String accountId) {
		return eventSourcingHandler.getById(accountId)
				.orElseThrow(() -> new NoSuchElementException("Account %s not found!".formatted(accountId)));
	}

	public void depositFunds(@NonNull DepositFundsCommand command) {
		var aggregate = getAccount(command.getId());
		aggregate.depositFunds(command.getAmount());
		eventSourcingHandler.save(aggregate);
	}

	public void withdrawFunds(@NonNull WithdrawFundsCommand command) {
		var aggregate = getAccount(command.getId());
		aggregate.withdrawFunds(command.getAmount());
		eventSourcingHandler.save(aggregate);
	}

	public void close(@NonNull CloseAccountCommand command) {
		var aggregate = getAccount(command.getId());
		aggregate.closeAccount();
		eventSourcingHandler.save((aggregate));
	}

}
