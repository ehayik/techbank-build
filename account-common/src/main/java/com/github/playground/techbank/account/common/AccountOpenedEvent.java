package com.github.playground.techbank.account.common;

import lombok.*;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class AccountOpenedEvent extends AccountEvent {

	String accountHolder;

	AccountType accountType;

	LocalDate createdDate;

	BigDecimal openingBalance;

	@Builder
	@ConstructorProperties({ "id", "accountHolder", "accountType", "createdDate", "openingBalance" })
	public AccountOpenedEvent(@NonNull String id, @NonNull String accountHolder, @NonNull AccountType accountType,
			@NonNull LocalDate createdDate, @NonNull BigDecimal openingBalance) {
		super(id);
		this.accountHolder = accountHolder;
		this.accountType = accountType;
		this.createdDate = createdDate;
		this.openingBalance = openingBalance;
	}

}
