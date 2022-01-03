package com.github.playground.techbank.cqrs.core;

public sealed interface Message<I> permits Command,Event {

	I getId();

}
