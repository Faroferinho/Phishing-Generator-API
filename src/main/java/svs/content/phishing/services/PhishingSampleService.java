package svs.content.phishing.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import svs.content.phishing.models.PhishingSamples;
import svs.content.phishing.repositories.PhishingSampleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PhishingSampleService {
    @Autowired
    private PhishingSampleRepository repository;

    public List<PhishingSamples> getPhishingList(){
        return repository.findAll();
    }

    public Optional<PhishingSamples> getPhishingSample(Long id){
        return repository.findById(id);
    }

    public Optional<PhishingSamples> getPhishingSample(String header){
        return repository.findByHeader(header);
    }
}
