package org.javers.api;

import org.javers.core.Javers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author pawel szymczyk
 */
@Configuration
public class JaversApiAutoConfiguration {

    @Autowired
    private Javers javers;

    @Bean
    JaversEndpoint javersEndpoint() {
        return new JaversEndpoint(new JaversService(javers));
    }
}
