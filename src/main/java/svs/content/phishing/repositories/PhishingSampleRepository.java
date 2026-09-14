package svs.content.phishing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import svs.content.phishing.models.PhishingSamples;

import java.util.Optional;

public interface PhishingSampleRepository extends JpaRepository<PhishingSamples, Long> {
    PhishingSamples getByHeader(String header);

    Optional<PhishingSamples> findByHeader(String header);
}
