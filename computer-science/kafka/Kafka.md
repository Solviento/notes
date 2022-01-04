# Apache Kafka

## Consuming Messages with Kafka Consumers and Consumer Groups

### Kafka Consumer

- Required properties for a kafka consumer 
  - bootstrap.servers
    - cluster membership: partition leaders
  - key and value deserializers
    - classes used for message deserialization

Sample code:

``` java
public class KafkaConsumerApp {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "BROKER-1:9092, BROKER-2:9093");
        props.put("key.deserializer", "org.apache.kafka.common.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.StringDeserializer");
        
        KafkaConsumer consumer = new KafkaConsumer(props);
    }
}
```

### Subscribing to Topics

To subscribe to topics, you can use the subscribe method in the built in apache KafkaConsumer class

``` java
KafkaConsumer myConsumer = new KafkaConsumer(props);
myConsumer.subscriber(Arrays.asList("my-topic"));
```

- there is no limit to the topics a consumer can subscribe to
- to subscribe to multiple topics, you must use a List data structure to add all topics and then subscribe
- to unsubscribe from all topics, use the unsubscribe method

There are variations in subscribe and how it works

- subscribe()
  - Used for topics and is dynamic/automatic
  - One topic is mapped out to one-to-many partitions
  - Many topics will generate many more partitions
- assign()
  - Used for partitions
  - Used for one or more partitions, regardless of topic
  - Is a manual and self-administering role

Manual Partition Assignment

``` java
TopicPartition partition0 = new TopicPartition("myTopic", 0);
ArrayList<TopicPartition> partitions = new ArrayList<TopicPartition>();
partitions.add(partition0);
```

The subscribe() method is automatic and will detect new partitions in the Topic subscription

- The topic partitions may change at a given time and subscribe() will detect this change
- If assign() is used, then this change will not be detected 

![01](C:\Users\Jason\Documents\Git Projects\notes\kafka\photos\01.PNG)

### The Poll Loop

- The Kafka consumer is constantly polling 
- Primary function of the kafka consumer
  - poll()
- Continuously polling the brokers for data
- Single API for handling all Consumer-Broker interactions
  - A lot of interactions beyond message retrieval

Starting the Poll Loop

``` java
// set the topic subscription or partition assignments
myConsumer.subscribe(topics);
myConsumer.assign(partitions);
try{
    while(true) {
        ConsumerRecords<String, String> records = myConsumer.poll(100);
        // processing logic
    }
} finally {
    myConsumer.close();
}
```





























