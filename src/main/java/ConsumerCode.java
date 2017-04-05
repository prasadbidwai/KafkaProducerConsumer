/**
 * Created by prasad on 3/25/2017.
 */
import java.util.*;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.log4j.Logger;

public class ConsumerCode {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(ConsumerCode.class);

        LogRepository logrepo = new LogRepository();
        String attackerIP= "attackerIP address";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092"); //setting the address to remote EC2 port
        props.put("acks", "all");
        props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id", "test");
        String TOPIC="test";    //topic name set to test

        Consumer theconsumer = new KafkaConsumer<String, String>(props);

        theconsumer.subscribe(Arrays.asList(TOPIC));    //making kafka consumer to subscribe to topic called 'test'

        while (true) {
            ConsumerRecords<String, String> records = theconsumer.poll(100);
            for (ConsumerRecord<String, String> record : records){
                if (record.key().equals(attackerIP))
                    System.out.println("attacker "+attackerIP+" detected at:: "+record.value());

                logrepo.updateLogInfo(record.key(), record.value());
            }
        }
    }
}
