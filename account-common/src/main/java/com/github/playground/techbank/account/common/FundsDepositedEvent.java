package com.github.playground.techbank.account.common;

import com.github.playground.techbank.cqrs.core.AbstractEvent;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.Value;

import java.math.BigDecimal;

@Value
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class FundsDepositedEvent extends AbstractEvent {

	BigDecimal amount;

	public FundsDepositedEvent(@NonNull String id, int version, @NonNull BigDecimal amount) {
		super(id, version);
		this.amount = amount;
	}

}
