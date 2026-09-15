package svs.content.phishing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import svs.content.phishing.models.PhishingSample;

import java.util.Optional;

@Repository
public interface PhishingSampleRepository extends JpaRepository<PhishingSample, Long> {
    PhishingSample getByHeader(String header);

    Optional<PhishingSample> findByHeader(String header);
}
