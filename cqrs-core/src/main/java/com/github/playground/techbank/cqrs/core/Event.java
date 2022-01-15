package com.github.playground.techbank.cqrs.core;

import lombok.*;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public abstract non-sealed class Event implements Message<String> {

	@NonNull
	private final String id;

	private int version;

	protected Event(String id) {
		this(id, 0);
	}

	public void setVersion(int version) {

		if (version < 0)
			throw new IllegalArgumentException("version cannot lower than 0.");

		if (version < this.version)
			throw new IllegalArgumentException(
					"current version %s is higher than version %s".formatted(this.version, version));

		this.version = version;
	}

}
