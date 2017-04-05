import java.io.*;
import java.util.*;
import org.apache.kafka.clients.producer.*;

public class ProducerCode {

    public static void main(String[] args) throws IOException {

        //setting up the configuration properties for kafka Producer
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "localhost:9092");  //server broker
        kafkaProps.put("acks", "all");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer producer = new KafkaProducer<String, String>(kafkaProps);
        String TOPIC="test";

        String FILELOCATION = "File location Path";
//        int linesCount = countLines(FILELOCATION);

        try{
            FileInputStream fstream = new FileInputStream(FILELOCATION);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            int count = 0;
            while ((strLine = br.readLine()) != null) {
                String[] tokens = strLine.split(" ");
                String dateToken  = tokens[3];
                String dateParsed = dateToken.substring(1, dateToken.length());

                LogFields logRecords = new LogFields(tokens[0],tokens[1],dateParsed);//process record , etc
//                producer.send(new ProducerRecord(TOPIC, logRecords.ipAddress, String.valueOf(linesCount)));
                producer.send(new ProducerRecord(TOPIC, logRecords.getIpAddress(), logRecords.getDate()));
            }
            in.close();
        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
        producer.close();
        }

    public static int countLines(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
            }
            return (count == 0 && !empty) ? 1 : count;
        } finally {
            is.close();
        }
    }
}
