package com.github.playground.techbank.account.cmd;

import com.github.playground.techbank.account.common.*;
import com.github.playground.techbank.cqrs.core.AggregateRoot;
import com.github.playground.techbank.cqrs.core.Event;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.math.BigDecimal.ZERO;

public final class AccountAggregate extends AggregateRoot<String> {

	private boolean active;

	private BigDecimal balance;

	void open(@NonNull OpenAccountCommand command) {
		raiseEvent(AccountOpenedEvent.builder().id(command.getId()).accountHolder(command.getAccountHolder())
				.createdDate(LocalDate.now()).accountType(command.getAccountType())
				.openingBalance(command.getOpeningBalance()).build());
	}

	private void apply(@NonNull AccountOpenedEvent event) {
		id = event.getId();
		active = true;
		balance = event.getOpeningBalance();
	}

	public void depositFunds(@NonNull BigDecimal amount) {

		if (active)
			throw new IllegalStateException("Funds cannot be deposited into a closed account!");

		if (amount.compareTo(ZERO) <= 0)
			throw new IllegalStateException("The deposit amount must be greater than 0!");

		raiseEvent(new FundsDepositedEvent(id, amount));
	}

	private void apply(@NonNull FundsDepositedEvent event) {
		id = event.getId();
		balance = balance.add(event.getAmount());
	}

	public void withdrawFunds(@NonNull BigDecimal amount) {

		if (!active)
			throw new IllegalStateException("Funds cannot be withdrawn from a closed account!");

		raiseEvent(new FundsWithdrawnEvent(id, amount));
	}

	private void apply(@NonNull FundsWithdrawnEvent event) {
		id = event.getId();
		balance = balance.subtract(event.getAmount());
	}

	public void closeAccount() {

		if (!active)
			throw new IllegalStateException("The bank account has already been closed!");

		raiseEvent(new AccountClosedEvent(id));
	}

	void apply(@NonNull AccountClosedEvent event) {
		id = event.getId();
		active = false;
	}

	@Override
	protected void apply(Event event) {

        switch ((AccountEvent) event) {
			case AccountOpenedEvent evt -> apply(evt);
			case AccountClosedEvent evt -> apply(evt);
			case FundsDepositedEvent evt -> apply(evt);
			case FundsWithdrawnEvent evt -> apply(evt);
		}
	}

}
