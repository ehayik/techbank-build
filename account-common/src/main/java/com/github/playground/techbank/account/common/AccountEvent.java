package com.github.playground.techbank.account.common;

import com.github.playground.techbank.cqrs.core.Event;
import lombok.NonNull;

public abstract sealed class AccountEvent
		extends Event permits AccountOpenedEvent,AccountClosedEvent,FundsDepositedEvent,FundsWithdrawnEvent {

	protected AccountEvent(@NonNull String id, int version) {
		super(id, version);
	}

	protected AccountEvent(String id) {
		super(id);
	}

}
