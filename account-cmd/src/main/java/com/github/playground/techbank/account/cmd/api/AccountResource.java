package com.github.playground.techbank.account.cmd.api;

import com.github.playground.techbank.account.cmd.domain.OpenAccountCommand;
import com.github.playground.techbank.account.common.AccountType;
import lombok.NonNull;

import java.math.BigDecimal;

record AccountResource(String id, String accountHolder, AccountType accountType, BigDecimal openingBalance) {

    public static AccountResource of(@NonNull OpenAccountCommand command) {

        return new AccountResource(
                command.getId(), //
                command.getAccountHolder(), //
                command.getAccountType(), //
                command.getOpeningBalance() //
        );
    }

}
