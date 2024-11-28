package ooad.life.cells.pathway;

import org.springframework.stereotype.Component;

@Component
public class SubjectFactory {

    public Subject create() {
        // Create and return a new Subject instance (customized if needed)
        return new Subject();
    }
}
