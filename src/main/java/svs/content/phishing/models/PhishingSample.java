package svs.content.phishing.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

        fallPercentage = BigDecimal.valueOf(bamboozledClicks)
                            .multiply(BigDecimal.valueOf(100))
                            .divide(BigDecimal.valueOf(totalClicks), 2, RoundingMode.HALF_UP);
        //printNumbers("" + fallPercentage.doubleValue());

        return fallPercentage;
    }

    public BigDecimal userNotBamboozled(){
        BigDecimal fallPercentage;

        totalClicks++;

        fallPercentage = BigDecimal.valueOf(bamboozledClicks)
                            .multiply(BigDecimal.valueOf(100))
                            .divide(BigDecimal.valueOf(totalClicks), 2, RoundingMode.HALF_UP);
        //printNumbers("" + fallPercentage.doubleValue());

        return fallPercentage;
    }

    private void printNumbers(String percentage){
        System.out.println("    totalClicks: " + totalClicks);
        System.out.println("    bamboozledClicks: " + bamboozledClicks);
        System.out.println("    Percentage: " + percentage);
        System.out.println("-------------------------------------------");
    }
}
