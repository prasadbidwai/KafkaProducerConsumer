import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by prasad on 3/25/2017.
 */
public class LogRepository {

    HashMap<String, Integer> theMap = new HashMap<String, Integer>();
    int count = 0;

    Logger logger = Logger.getLogger(LogRepository.class);

    public void updateLogInfo(String ipAddress, String linesCount){
        count++;

        if (!theMap.containsKey(ipAddress)){
            theMap.put(ipAddress, 1);
        } else{
            int currentCount = theMap.get(ipAddress);
            theMap.put(ipAddress, currentCount+1);
        }
//        if (count==Integer.parseInt(linesCount)){
          // count of total number of lines in file
          if (count==163416){
            printTheCounts();
        }
    }

    public void printTheCounts(){
        System.out.println("\n******************************************************");
        System.out.println("Printing the Statistics about IPs of interest::");

        String sampleString = "some IP address";
        String attackerIP= "suspected attackerIP";
        String nonAttackerIP = "IP of legitimate user";

        for (Map.Entry it: theMap.entrySet()){
            if(it.getKey().equals(sampleString) || it.getKey().equals(attackerIP) || it.getKey().equals(nonAttackerIP))
                System.out.println(it.getKey()+" ::  "+it.getValue());
        }
    }
}
