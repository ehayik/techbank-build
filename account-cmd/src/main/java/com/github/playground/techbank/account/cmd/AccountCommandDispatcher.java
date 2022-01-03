package com.github.playground.techbank.account.cmd;

import com.github.playground.techbank.cqrs.core.AbstractCommand;
import com.github.playground.techbank.cqrs.core.CommandDispatcher;
import com.github.playground.techbank.cqrs.core.CommandHandler;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@SuppressWarnings({ "unchecked", "rawtypes" })
public final class AccountCommandDispatcher implements CommandDispatcher {

	@SuppressWarnings("java:S3740")
	private final Map<Class<? extends AbstractCommand>, CommandHandler> routes;

	public AccountCommandDispatcher() {
		this(new HashMap<>());
	}

	@Override
	public <T extends AbstractCommand> void registerHandler(@NonNull Class<T> commandType,
			@NonNull CommandHandler<T> handler) {

		if (routes.containsKey(commandType))
			throw new IllegalArgumentException(
					"A handler is already registered for the given command " + commandType.getSimpleName());

		routes.put(commandType, handler);
	}

	@Override
	public void send(@NonNull AbstractCommand command) {
		var handler = routes.get(command.getClass());

		if (handler == null)
			throw new NoSuchElementException(
					"Handler for command %s isn't registered yet. Use #registerHandler(...) to do so."
							.formatted(command.getClass().getSimpleName()));

		handler.handle(command);
	}

}
