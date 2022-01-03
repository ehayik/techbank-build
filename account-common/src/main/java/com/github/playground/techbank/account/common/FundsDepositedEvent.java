package com.github.playground.techbank.account.common;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.Value;

import java.math.BigDecimal;

@Value
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class FundsDepositedEvent extends AccountEvent {

	BigDecimal amount;

	public FundsDepositedEvent(@NonNull String id, @NonNull BigDecimal amount) {
		super(id);
		this.amount = amount;
	}

}
