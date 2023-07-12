package au.com.telstra.simcardactivator;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SimActivationController {
    private static final String ACTUATOR_ENDPOINT = "http://localhost:8444/actuate";

    @PostMapping("/activateSim")
    public String activateSim(@RequestBody SimRequest simRequest) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // create actuator request
        ActuatorRequest actuatorRequest = new ActuatorRequest(simRequest.getIccid());

        HttpEntity<ActuatorRequest> entity = new HttpEntity<>(actuatorRequest, headers);

        // send POST request to actuator
        ResponseEntity<ActuatorResponse> response = restTemplate.postForEntity(ACTUATOR_ENDPOINT, entity, ActuatorResponse.class);

        if (response.getBody().getSuccess()) {
            return "Activation of SIM card with ICCID: " + simRequest.getIccid() + " was successful.";
        } else {
            return "Activation of SIM card with ICCID: " + simRequest.getIccid() + " failed.";
        }
    }
}
