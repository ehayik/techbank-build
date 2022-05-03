##  CQRS & Event Sourcing with Kafka

### Running the application

- Make sure you have docker installed and running, then execute:

`docker network create --attachable -d bridge techbankNetwork`

- Make sure you have *docker-compose*, then execute:

`docker-compose -f docker/docker-compose.yml up -d`

- Open new terminal and execute:

`./gradlew account-cmd:bootRun`

- Open new terminal and execute:

`./gradlew account-query:bootRun`

### Http request examples

#### Open account
```
curl --request POST \
        --url http://localhost:5000/accounts \
        --header 'content-type: application/json' \
        --data '{"accountHolder": "Johnny Terrory", "accountType": "CURRENT", "openingBalance": 10000}'
```

### References

- [Can Your Kafka Consumers Handle a Poison Pill?](https://www.confluent.io/blog/spring-kafka-can-your-kafka-consumers-handle-a-poison-pill/)