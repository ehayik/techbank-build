package com.github.playground.techbank.cqrs.core;

@FunctionalInterface
public interface CommandHandler<T extends AbstractCommand> {

	void handle(T command);

}
