package com.github.playground.techbank.cqrs.core;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public abstract class AbstractCommand implements Message<String> {

	@NonNull
	private final String id;

}
