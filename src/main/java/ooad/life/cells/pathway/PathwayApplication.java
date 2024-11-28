package ooad.life.cells.pathway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "ooad.life.cells.pathway")

public class PathwayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PathwayApplication.class, args);
    }
}
