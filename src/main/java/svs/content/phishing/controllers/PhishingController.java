package svs.content.phishing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import svs.content.phishing.models.PhishingEmail;
import svs.content.phishing.models.PhishingSamples;
import svs.content.phishing.services.PhishingSampleService;

import java.util.List;
import java.util.Random;

@RequestMapping("/api/phishing")
public class PhishingController {
    @Autowired
    public PhishingSampleService service;

    private final Random random = new Random();
    private final List<PhishingSamples> samples = service.getPhishingList();

    @GetMapping("/random")
    public ResponseEntity<PhishingEmail> getRandomPhishing(){
        if(samples.isEmpty()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        PhishingEmail email = new PhishingEmail(samples.get(random.nextInt(samples.size())));
        return ResponseEntity.ok(email);
    }
}
