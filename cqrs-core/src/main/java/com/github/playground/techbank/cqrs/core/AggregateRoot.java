package com.github.playground.techbank.cqrs.core;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.unmodifiableList;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class AggregateRoot<I> {

	@EqualsAndHashCode.Include
	protected I id;

	private int version;

	private final List<Event> uncommittedChanges;

	protected AggregateRoot() {
		uncommittedChanges = new LinkedList<>();
	}

	public void raiseEvent(@NonNull Event event) {
		applyNewChange(event);
	}

	protected void applyNewChange(@NonNull Event event) {
		apply(event);
		uncommittedChanges.add(event);
	}

	protected abstract void apply(Event event);

	public void replayEvents(@NonNull Iterable<Event> events) {
		events.forEach(this::apply);
	}

	public Optional<I> getId() {
		return Optional.ofNullable(id);
	}

	public List<Event> getUncommittedChanges() {
		return unmodifiableList(uncommittedChanges);
	}

	public void markChangesAsCommitted() {
		uncommittedChanges.clear();
	}

}
