package com.github.playground.techbank.cqrs.core;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public abstract non-sealed class Command implements Message<String> {

	@NonNull
	private final String id;

}
