package com.github.playground.techbank.cqrs.core;

import java.util.List;

public interface EventStore<T extends AggregateRoot> {

	void save(T aggregate);

	List<Event> get(String aggregateId);

}
