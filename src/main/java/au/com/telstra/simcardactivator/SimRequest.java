package au.com.telstra.simcardactivator;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SimRequest {
    private String iccid;

    @JsonProperty("customerEmail")
    private String customerEmail;

    public String getIccid() {
        return iccid;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }
}
