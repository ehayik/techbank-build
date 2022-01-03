package com.github.playground.techbank.account.cmd;

import com.github.playground.techbank.cqrs.core.Command;
import lombok.NonNull;
import lombok.ToString;

@ToString(callSuper = true)
public class CloseAccountCommand extends Command {

	public CloseAccountCommand(@NonNull String id) {
		super(id);
	}

}
