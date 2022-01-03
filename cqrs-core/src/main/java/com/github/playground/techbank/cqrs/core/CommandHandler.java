package com.github.playground.techbank.cqrs.core;

@FunctionalInterface
public interface CommandHandler<T extends Command> {

	void handle(T command);

}
