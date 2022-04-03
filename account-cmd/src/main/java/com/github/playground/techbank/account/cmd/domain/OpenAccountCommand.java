package com.github.playground.techbank.account.cmd.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.playground.techbank.account.common.AccountType;
import com.github.playground.techbank.cqrs.core.Command;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class OpenAccountCommand extends Command {

	String accountHolder;

	AccountType accountType;

	BigDecimal openingBalance;

	@JsonCreator
	public OpenAccountCommand(String accountHolder, AccountType accountType, BigDecimal openingBalance) {
		this(UUID.randomUUID().toString(), accountHolder, accountType, openingBalance);
	}

	public OpenAccountCommand(@NonNull String id, @NonNull String accountHolder, @NonNull AccountType accountType,
			@NonNull BigDecimal openingBalance) {
		super(id);
		this.accountHolder = accountHolder;
		this.accountType = accountType;
		this.openingBalance = openingBalance;
	}

}
