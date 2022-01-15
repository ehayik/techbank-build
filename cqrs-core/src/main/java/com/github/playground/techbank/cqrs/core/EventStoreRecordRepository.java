package com.github.playground.techbank.cqrs.core;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventStoreRecordRepository extends MongoRepository<EventStoreRecord, String> {

	List<EventStoreRecord> findByAggregateId(String aggregateId);

}
