package com.github.playground.techbank.cqrs.core;

public interface CommandDispatcher {

	<T extends Command> void registerHandler(Class<T> commandType, CommandHandler<T> handler);

	<T extends Command> void send(T command);

}
