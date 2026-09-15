package svs.content.phishing.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhishingEmail {
    private String header;
    private String body;

    public PhishingEmail(){

    }

    public PhishingEmail(String header, String body){
        this.header = header;
        this.body = body;
    }

    public PhishingEmail(PhishingSample sample){
        this.header = sample.getHeader();
        this.body = sample.getBody();
    }
}
