/**
 * Created by prasad on 3/25/2017.
 */

public class LogFields {
    private String ipAddress;
    private String method;
    private String date;

    public LogFields(String ipAddress, String method, String date){
        this.ipAddress = ipAddress;
        this.method = method;
        this.date = date;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getMethod() {
        return method;
    }

    public String getDate() {
        return date;
    }

}
