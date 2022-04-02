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
    public AccountResource openAccount(@RequestBody AccountResource account) {
        var command = new OpenAccountCommand(account.accountHolder(), account.accountType(), account.openingBalance());
        commandDispatcher.send(command);
        return AccountResource.of(command);
    }
}
