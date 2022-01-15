package com.github.playground.techbank.cqrs.core;

import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Objects;

@Document("eventStore")
public record EventStoreRecord(@Id String id, @NonNull LocalDateTime timestamp, @NonNull String aggregateId,
		@NonNull String aggregateType, int version, @NonNull String eventType, @NonNull Event eventData) {

	public static EventStoreRecord of(@NonNull String aggregateId, @NonNull Class<?> aggregateType,
			@NonNull Event eventData) {

		if (aggregateId.isBlank())
			throw new IllegalArgumentException("aggregateId is blank.");

		return new EventStoreRecord(null, LocalDateTime.now(), aggregateId, aggregateType.getTypeName(),
				eventData.getVersion(), eventData.getClass().getTypeName(), eventData);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof EventStoreRecord other) && id.equals(other.id);
	}
}
