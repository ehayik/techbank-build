package com.github.playground.techbank.account.common;

import com.github.playground.techbank.cqrs.core.AbstractEvent;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class AccountOpenedEvent extends AbstractEvent {

	String accountHolder;

	AccountType accountType;

	LocalDate createdDate;

	BigDecimal openingBalance;

	public AccountOpenedEvent(@NonNull String id, int version, @NonNull String accountHolder,
			@NonNull AccountType accountType, @NonNull LocalDate createdDate, @NonNull BigDecimal openingBalance) {
		super(id, version);
		this.accountHolder = accountHolder;
		this.accountType = accountType;
		this.createdDate = createdDate;
		this.openingBalance = openingBalance;
	}

}
