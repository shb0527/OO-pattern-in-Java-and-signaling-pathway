package ooad.life.cells.pathway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

public class SubjectRegistry {

    private static final Map<EventType, Subject> subjects = new HashMap<>();


    public SubjectRegistry(SubjectFactory subjectFactory) {
        subjects.put(EventType.RunPathway, subjectFactory.create());
        subjects.put(EventType.Bind, subjectFactory.create());
        subjects.put(EventType.Activate, subjectFactory.create());
        subjects.put(EventType.Deactivate, subjectFactory.create());
        subjects.put(EventType.Phosphorylate, subjectFactory.create());
        subjects.put(EventType.Dephosphorylate, subjectFactory.create());
        subjects.put(EventType.EnterNucleus, subjectFactory.create());
        subjects.put(EventType.Hydrolyze, subjectFactory.create());
        subjects.put(EventType.Exchange, subjectFactory.create());

    }

    public Subject getSubject(EventType eventType) {
        return subjects.get(eventType);
    }
}
