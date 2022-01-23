package com.github.playground.techbank.account.cmd;

import com.github.playground.techbank.account.cmd.domain.*;
import com.github.playground.techbank.account.cmd.infrastructure.AccountEventStore;
import com.github.playground.techbank.account.cmd.infrastructure.EventStoreRecordRepository;
import com.github.playground.techbank.cqrs.core.EventSourcingHandler;
import com.github.playground.techbank.cqrs.core.EventStore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = EventStoreRecordRepository.class)
public class CommandApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommandApplication.class, args);
	}

	@Bean
	AccountCommandDispatcher commandDispatcher(AccountService accountService) {
		var dispatcher = new AccountCommandDispatcher();
		dispatcher.registerHandler(OpenAccountCommand.class, accountService::open);
		dispatcher.registerHandler(CloseAccountCommand.class, accountService::close);
		dispatcher.registerHandler(DepositFundsCommand.class, accountService::depositFunds);
		dispatcher.registerHandler(WithdrawFundsCommand.class, accountService::withdrawFunds);
		return dispatcher;
	}

	@Bean
	AccountService accountService(EventSourcingHandler<AccountAggregate> accountAggregateEventSourcingHandler) {
		return new AccountService(accountAggregateEventSourcingHandler);
	}

	@Bean
	EventSourcingHandler<AccountAggregate> accountAggregateEventSourcingHandler(
			EventStore<AccountAggregate> eventStore) {
		return new AccountEventSourcingHandler(eventStore);
	}

	@Bean
	EventStore<AccountAggregate> eventStore(EventStoreRecordRepository eventStoreRecordRepository) {
		return new AccountEventStore(eventStoreRecordRepository);
	}

}
