package svs.content.phishing.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity

@Table(name = "PhishingEmails")
public class PhishingSample {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    Long id;

    @Getter @Setter
    private String header;
    @Getter @Setter
    private String body;

    private int totalClicks = 0;
    private int bamboozledClicks = 0;

    public PhishingSample(){}

    public PhishingSample(Long id, String header, String body, int totalClicks, int bamboozledClicks) {
        this.id = id;
        this.header = header;
        this.body = body;
        this.totalClicks = totalClicks;
        this.bamboozledClicks = bamboozledClicks;
    }

    public BigDecimal userBamboozled(){
        BigDecimal fallPercentage;

        totalClicks++;
        bamboozledClicks++;

        fallPercentage = new BigDecimal(bamboozledClicks);
        fallPercentage.divide(new BigDecimal(totalClicks));

        return fallPercentage;
    }

    public BigDecimal userNotBamboozled(){
        BigDecimal fallPercentage;

        totalClicks++;

        fallPercentage = new BigDecimal(bamboozledClicks);
        fallPercentage.divide(new BigDecimal(totalClicks));

        return fallPercentage;
    }
}
