package com.github.playground.techbank.account.cmd.infrastructure;

import com.github.playground.techbank.cqrs.core.Event;
import com.github.playground.techbank.cqrs.core.EventProducer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
final class AccountEventProducer implements EventProducer {

	private final KafkaTemplate<String, Object> kafkaTemplate;

	@Override
	public void produce(@NonNull String topic, @NonNull Event event) {

		if (topic.isBlank())
			throw new IllegalArgumentException("topic cannot be blank");

		kafkaTemplate.send(topic, event);
		LOG.debug("Produced event {} to topic {}", event.getId(), topic);
	}

}
