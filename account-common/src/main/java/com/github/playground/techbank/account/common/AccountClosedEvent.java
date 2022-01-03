package com.github.playground.techbank.account.common;

import com.github.playground.techbank.cqrs.core.AbstractCommand;
import lombok.NonNull;
import lombok.ToString;

@ToString(callSuper = true)
public class AccountClosedEvent extends AbstractCommand {

	public AccountClosedEvent(@NonNull String id) {
		super(id);
	}

}
