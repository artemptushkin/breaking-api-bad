package dev.boiarshinov.breakingbad.clientlib.client;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        CrystalClientProperties.class
})
public class ClientAutoConfiguration {

    @ConditionalOnProperty(prefix = "crystal.client", name = "uri")
    @Bean
    public CrystalClient crystalClient(CrystalClientProperties crystalClientProperties) {
        return new CrystalClientBaseImpl(crystalClientProperties);
    }
}
