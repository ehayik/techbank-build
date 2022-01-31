package com.github.playground.techbank.account.query;

import com.github.playground.techbank.account.common.AccountClosedEvent;
import com.github.playground.techbank.account.common.AccountEvent;
import com.github.playground.techbank.account.common.AccountOpenedEvent;
import com.github.playground.techbank.account.common.FundsDepositedEvent;
import com.github.playground.techbank.account.common.FundsWithdrawnEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class AccountEventConsumer {

	private final AccountEventHandler accountEventHandler;

	@KafkaListener(topics = "AccountOpenedEvent", groupId = "${spring.kafka.consumer.group-id}")
	public void consume(AccountOpenedEvent event, Acknowledgment ack) {
		consumeAndAcknowledge(event, ack);
	}

	private void consumeAndAcknowledge(AccountEvent event, Acknowledgment ack) {
		accountEventHandler.on(event);
		ack.acknowledge();
	}

	@KafkaListener(topics = "FundsDepositedEvent", groupId = "${spring.kafka.consumer.group-id}")
	public void consume(FundsDepositedEvent event, Acknowledgment ack) {
		consumeAndAcknowledge(event, ack);
	}

	@KafkaListener(topics = "FundsWithdrawnEvent", groupId = "${spring.kafka.consumer.group-id}")
	public void consume(FundsWithdrawnEvent event, Acknowledgment ack) {
		consumeAndAcknowledge(event, ack);
	}

	@KafkaListener(topics = "AccountClosedEvent", groupId = "${spring.kafka.consumer.group-id}")
	public void consume(AccountClosedEvent event, Acknowledgment ack) {
		consumeAndAcknowledge(event, ack);
	}

}
