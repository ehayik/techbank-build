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
		var aggregateId = aggregate.getId().orElse(null);
		var expectedVersion = aggregate.getVersion();
		var eventStream = eventStoreRepository.findByAggregateId(aggregateId);

		if (expectedVersion != -1 && eventStream.get(eventStream.size() - 1).version() != expectedVersion) {
			throw new ConcurrencyException();
		}

		var version = expectedVersion;

		for (var event : aggregate.getUncommittedChanges()) {
			event.setVersion(version++);
			var persistedEvent = eventStoreRepository
					.save(EventStoreRecord.of(aggregateId, AccountAggregate.class, event));

			if (persistedEvent != null) {
				accountEventEventProducer.produce(event);
			}
		}
	}

	@Override
	public List<Event> get(String aggregateId) {
		var eventStream = eventStoreRepository.findByAggregateId(aggregateId);

		if (eventStream.isEmpty()) {
			throw new NoSuchElementException("Incorrect account ID provided!");
		}

		return eventStream.stream().map(EventStoreRecord::eventData).toList();
	}

}
