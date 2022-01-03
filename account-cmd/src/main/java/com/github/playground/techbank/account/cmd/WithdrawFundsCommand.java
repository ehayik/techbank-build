package com.github.playground.techbank.account.cmd;

import com.github.playground.techbank.cqrs.core.AbstractCommand;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.Value;

import java.math.BigDecimal;

@Value
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class WithdrawFundsCommand extends AbstractCommand {

	BigDecimal amount;

	public WithdrawFundsCommand(@NonNull String id, @NonNull BigDecimal amount) {
		super(id);
		this.amount = amount;
	}

}
