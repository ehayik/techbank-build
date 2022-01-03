package com.github.playground.techbank.account.common;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.Value;

import java.math.BigDecimal;

@Value
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class FundsWithdrawnEvent extends AccountEvent {

	BigDecimal amount;

	public FundsWithdrawnEvent(@NonNull String id, @NonNull BigDecimal amount) {
		super(id);
		this.amount = amount;
	}

}
