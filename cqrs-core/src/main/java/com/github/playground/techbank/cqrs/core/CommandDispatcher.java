package com.github.playground.techbank.cqrs.core;

public interface CommandDispatcher {

	<T extends AbstractCommand> void registerHandler(Class<T> commandType, CommandHandler<T> handler);

	<T extends AbstractCommand> void send(T command);

}
