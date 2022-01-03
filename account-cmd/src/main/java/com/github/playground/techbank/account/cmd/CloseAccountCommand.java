package com.github.playground.techbank.account.cmd;

import com.github.playground.techbank.cqrs.core.AbstractCommand;
import lombok.NonNull;
import lombok.ToString;

@ToString(callSuper = true)
public class CloseAccountCommand extends AbstractCommand {

	public CloseAccountCommand(@NonNull String id) {
		super(id);
	}

}
