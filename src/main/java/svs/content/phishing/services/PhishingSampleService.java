package svs.content.phishing.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import svs.content.phishing.models.PhishingEmail;
import svs.content.phishing.models.PhishingSample;
import svs.content.phishing.repositories.PhishingSampleRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhishingSampleService {
    @Autowired
    private PhishingSampleRepository repository;

    public List<PhishingSample> getPhishingList(){
        return repository.findAll();
    }

    public Optional<PhishingSample> getPhishingSample(Long id){
        return repository.findById(id);
    }

    public PhishingSample getPhishingSample(String header){
        return repository.findByHeader(header).get();
    }

    public PhishingSample postPhishingList(PhishingEmail email){
        PhishingSample sample = new PhishingSample();

        sample.setHeader(email.getHeader());
        sample.setBody(email.getBody());

        return repository.save(sample);
    }

    public BigDecimal phishingBamboozleUser(String header){
        BigDecimal value;
        PhishingSample sample = getPhishingSample(header);

        value = sample.userBamboozled();
        repository.save(sample);

        return sample.userBamboozled();
    }

    public BigDecimal phishingDidntBamboozleUser(String header){
        BigDecimal value;
        PhishingSample sample = getPhishingSample(header);

        value = sample.userNotBamboozled();
        repository.save(sample);

        return value;
    }

    public void deletePhishing(Long id){
        repository.delete(getPhishingSample(id).get());
    }

    public void deletePhishing(String header){
        repository.delete(getPhishingSample(header));
    }
}
