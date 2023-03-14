package dev.boiarshinov.breakingbad.clientlib.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "crystal.client")
public class CrystalClientProperties {

    private String uri;
    private int timeout = 2000;
    private String token;
}
