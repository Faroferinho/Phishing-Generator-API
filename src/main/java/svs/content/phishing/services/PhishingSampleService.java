package svs.content.phishing.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import svs.content.phishing.models.PhishingEmail;
import svs.content.phishing.models.PhishingSample;
import svs.content.phishing.repositories.PhishingSampleRepository;

import java.math.BigDecimal;
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

    public Optional<PhishingSample> getPhishingSample(String header){
        return repository.findByHeader(header);
    }

    public PhishingSample postPhishingList(PhishingEmail email){
        PhishingSample sample = new PhishingSample();

        sample.setHeader(email.getHeader());
        sample.setBody(email.getBody());

        return repository.save(sample);
    }

    public BigDecimal phishingBamboozleUser(String header){
        PhishingSample sample = getPhishingSample(header).get();
        return sample.userBamboozled();
    }

    public BigDecimal phishingDidntBamboozleUser(String header){
        PhishingSample sample = getPhishingSample(header).get();
        return sample.userNotBamboozled();
    }

    public void deletePhishing(Long id){
        repository.delete(getPhishingSample(id).get());
    }

    public void deletePhishing(String header){
        repository.delete(getPhishingSample(header).get());
    }
}
