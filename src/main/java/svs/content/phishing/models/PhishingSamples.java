package svs.content.phishing.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter @Setter
@Table(name = "PhishingEmails")
public class PhishingSamples {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String header;
    private String body;
    @Column(precision = 3, scale = 1)
    private BigDecimal fallPercentage;

    public PhishingSamples(){}

    public PhishingSamples(Long id, String header, String body, BigDecimal fallPercentage) {
        this.id = id;
        this.header = header;
        this.body = body;
        this.fallPercentage = fallPercentage;
    }
}
