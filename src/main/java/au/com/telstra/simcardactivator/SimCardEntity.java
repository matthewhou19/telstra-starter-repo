package au.com.telstra.simcardactivator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SimCardEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String iccid;
    private String customerEmail;
    private Boolean activated;

    public SimCardEntity() {
    }

    public SimCardEntity(String iccid, String customerEmail, Boolean activated) {
        this.iccid = iccid;
        this.customerEmail = customerEmail;
        this.activated = activated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }
}
