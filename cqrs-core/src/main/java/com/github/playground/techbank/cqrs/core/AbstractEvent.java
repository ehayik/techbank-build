package com.github.playground.techbank.cqrs.core;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public abstract class AbstractEvent implements Message<String> {

	@NonNull
	private final String id;

	private final int version;

}
