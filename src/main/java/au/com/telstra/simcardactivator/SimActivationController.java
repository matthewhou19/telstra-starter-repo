package au.com.telstra.simcardactivator;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class SimActivationController {
    private static final String ACTUATOR_ENDPOINT = "http://localhost:8444/actuate";
    private SimCardRepository simCardRepository;
    SimActivationController(SimCardRepository simCardRepository) {
        this.simCardRepository = simCardRepository;
    }
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
            simCardRepository.save(new SimCardEntity(simRequest.getIccid(),simRequest.getCustomerEmail(),true ));
            return "Activation of SIM card with ICCID: " + simRequest.getIccid() + " was successful.";
        } else {
            simCardRepository.save(new SimCardEntity(simRequest.getIccid(),simRequest.getCustomerEmail(),false ));
            return "Activation of SIM card with ICCID: " + simRequest.getIccid() + " failed.";
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SimCardEntity> getSimCard(@PathVariable Long id) {
        return new ResponseEntity<>(simCardRepository.getReferenceById(id), HttpStatus.OK);
    }
}
