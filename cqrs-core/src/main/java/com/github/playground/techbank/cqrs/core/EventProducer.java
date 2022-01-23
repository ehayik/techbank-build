package com.github.playground.techbank.cqrs.core;

import lombok.NonNull;

public interface EventProducer {

	default void produce(@NonNull Event event) {
		produce(event.getClass().getSimpleName(), event);
	}

	void produce(String topic, Event event);

}
