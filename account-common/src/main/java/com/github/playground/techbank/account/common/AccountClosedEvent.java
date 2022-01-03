package com.github.playground.techbank.account.common;

import lombok.NonNull;
import lombok.ToString;

@ToString(callSuper = true)
public final class AccountClosedEvent extends AccountEvent {

	public AccountClosedEvent(@NonNull String id) {
		super(id);
	}

}
