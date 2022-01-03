package com.github.playground.techbank.account.cmd;

import com.github.playground.techbank.account.common.AccountType;
import com.github.playground.techbank.cqrs.core.AbstractCommand;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.Value;

import java.math.BigDecimal;

@Value
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class OpenAccountCommand extends AbstractCommand {

	String accountHolder;

	AccountType accountType;

	BigDecimal openingBalance;

	public OpenAccountCommand(@NonNull String id, @NonNull String accountHolder, @NonNull AccountType accountType,
			@NonNull BigDecimal openingBalance) {
		super(id);
		this.accountHolder = accountHolder;
		this.accountType = accountType;
		this.openingBalance = openingBalance;
	}

}
