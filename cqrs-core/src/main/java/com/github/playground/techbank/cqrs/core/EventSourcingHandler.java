package com.github.playground.techbank.cqrs.core;

import java.util.Optional;

public interface EventSourcingHandler<T extends AggregateRoot> {

	void save(T aggregate);

	Optional<T> getById(String id);

}
