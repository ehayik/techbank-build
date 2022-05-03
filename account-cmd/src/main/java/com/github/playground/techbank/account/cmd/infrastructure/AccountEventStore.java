package com.github.playground.techbank.account.cmd.infrastructure;

import com.github.playground.techbank.account.cmd.domain.AccountAggregate;
import com.github.playground.techbank.cqrs.core.ConcurrencyException;
import com.github.playground.techbank.cqrs.core.Event;
import com.github.playground.techbank.cqrs.core.EventProducer;
import com.github.playground.techbank.cqrs.core.EventStore;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
final class AccountEventStore implements EventStore<AccountAggregate> {

	private final EventStoreRecordRepository eventStoreRepository;

	private final EventProducer accountEventEventProducer;

	@Override
	public void save(@NonNull AccountAggregate aggregate) {
		var aggregateId = aggregate.getId().orElseThrow();
		var accountRecordedEvents = eventStoreRepository.findByAggregateId(aggregateId);
		assertVersion(accountRecordedEvents, aggregate.getVersion());
		var version = aggregate.getVersion();

		for (var event : aggregate.getUncommittedChanges()) {
			event.setVersion(version++);
			eventStoreRepository.save(EventStoreRecord.of(aggregateId, AccountAggregate.class, event));
			accountEventEventProducer.produce(event);
		}
	}

	private static void assertVersion(List<EventStoreRecord> accountRecordedEvents, int expectedVersion) {
		if (expectedVersion == -1 || accountRecordedEvents.isEmpty())
			return;
		EventStoreRecord tailEvent = accountRecordedEvents.get(accountRecordedEvents.size() - 1);
		if (tailEvent.version() != expectedVersion)
			throw new ConcurrencyException();
	}

	@Override
	public List<Event> get(String aggregateId) {
		var eventStream = eventStoreRepository.findByAggregateId(aggregateId);

		if (eventStream.isEmpty())
			throw new NoSuchElementException("Incorrect account ID provided!");

		return eventStream.stream().map(EventStoreRecord::eventData).toList();
	}

}
