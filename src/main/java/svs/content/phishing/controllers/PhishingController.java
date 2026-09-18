package svs.content.phishing.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import svs.content.phishing.models.PhishingEmail;
import svs.content.phishing.models.PhishingSample;
import svs.content.phishing.services.PhishingSampleService;

import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/phishing")
public class PhishingController {

    private final PhishingSampleService service;
    private final Random random = new Random();

    public PhishingController(PhishingSampleService service) {
        this.service = service;
    }

    @GetMapping("/random")
    public ResponseEntity<PhishingEmail> getRandomPhishing(){
        System.out.println("GET Request foi feito");

        List<PhishingSample> samples = service.getPhishingList();

        if(samples.isEmpty()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        PhishingEmail email = new PhishingEmail(samples.get(random.nextInt(samples.size())));

        System.out.println("Email:\n    Header: " + email.getHeader() + "\n    Body: " + email.getBody());

        return ResponseEntity.ok(email);
    }

    @PostMapping
    public ResponseEntity<PhishingSample> postPhishingSample(@RequestBody PhishingEmail requestBody){
//        System.out.println("Post Request foi feito");
//        System.out.println("Header: " + requestBody.getHeader());
//        System.out.println("Body: " + requestBody.getBody());

        if(requestBody.getHeader() == null || requestBody.getHeader().isBlank()){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(service.postPhishingList(requestBody));
    }
}