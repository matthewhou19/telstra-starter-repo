package au.com.telstra.simcardactivator;

public class ActuatorRequest {
    private String iccid;

    public ActuatorRequest(String iccid) {
        this.iccid = iccid;
    }

    public String getIccid() {
        return iccid;
    }
}
