package cz.cvut.fel.pc2e.garminworker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mustache.MustacheEnvironmentCollector;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class GarminWorkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GarminWorkerApplication.class, args);
    }
}
