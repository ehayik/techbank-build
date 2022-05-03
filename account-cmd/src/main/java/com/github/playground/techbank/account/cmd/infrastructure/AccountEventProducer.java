package com.github.playground.techbank.account.cmd.infrastructure;

import com.github.playground.techbank.cqrs.core.Event;
import com.github.playground.techbank.cqrs.core.EventProducer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
@RequiredArgsConstructor
final class AccountEventProducer implements EventProducer {

	private final KafkaTemplate<String, Object> kafkaTemplate;

	@Override
	public void produce(@NonNull String topic, @NonNull Event event) {

		if (topic.isBlank())
			throw new IllegalArgumentException("topic cannot be blank");

		var asyncSendResult = kafkaTemplate.send(topic, event);
		asyncSendResult.addCallback(new ListenableFutureCallbackImpl(event.getId(), topic));
	}

	@RequiredArgsConstructor
	private static class ListenableFutureCallbackImpl implements ListenableFutureCallback<SendResult<String, Object>> {

		private final String eventId;

		private final String topic;

		@Override
		public void onFailure(Throwable ex) {
			LOG.error("Event {} delivery to topic {} failed. Error: {}", eventId, topic, ex.getMessage(), ex);
		}

		@Override
		public void onSuccess(SendResult<String, Object> result) {
			LOG.debug("Produced event {} to topic {}", eventId, topic);
		}

	}

}
