package com.github.playground.techbank.account.cmd.api;

import com.github.playground.techbank.account.cmd.domain.OpenAccountCommand;
import com.github.playground.techbank.cqrs.core.CommandDispatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
class AccountsRestController {

	private final CommandDispatcher commandDispatcher;

	@PostMapping
	@ResponseStatus(CREATED)
	public AccountResource openAccount(@RequestBody OpenAccountCommand command) {
		commandDispatcher.send(command);
		return new AccountResource(command.getId());
	}

}
