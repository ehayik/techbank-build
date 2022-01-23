package com.github.playground.techbank.account.cmd.domain;

import com.github.playground.techbank.cqrs.core.Event;
import com.github.playground.techbank.cqrs.core.EventSourcingHandler;
import com.github.playground.techbank.cqrs.core.EventStore;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static java.util.Comparator.naturalOrder;

@RequiredArgsConstructor
public class AccountEventSourcingHandler implements EventSourcingHandler<AccountAggregate> {

	private final EventStore<AccountAggregate> eventStore;

	@Override
	public void save(@NonNull AccountAggregate aggregate) {
		eventStore.save(aggregate);
		aggregate.markChangesAsCommitted();
	}

	@Override
	public Optional<AccountAggregate> getById(@NonNull String id) {
		var events = eventStore.get(id);

		if (events.isEmpty())
			return Optional.empty();

		var latestVersion = events.stream().map(Event::getVersion).max(naturalOrder()).orElse(0);
		var aggregate = new AccountAggregate(latestVersion);
		aggregate.replayEvents(events);
		return Optional.of(aggregate);
	}

}
