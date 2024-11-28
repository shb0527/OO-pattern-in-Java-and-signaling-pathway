package ooad.life.cells.pathway.command;

import ooad.life.cells.pathway.EventType;
import ooad.life.cells.pathway.Kinases.Kinase;
import ooad.life.cells.pathway.Subject;
import ooad.life.cells.pathway.SubjectFactory;
import ooad.life.cells.pathway.SubjectRegistry;
import ooad.life.cells.pathway.TranscriptionFactors.TranscriptionFactor;

public class PhosphorylateCommand<T, U> implements Command{

    protected T key;
    protected U value;
    private Subject subject;
    private SubjectRegistry subjectRegistry;
    public PhosphorylateCommand(T key, U value, SubjectRegistry subjectRegistry){
       this.key = key;
       this.value = value;
       this.subject = subjectRegistry.getSubject(EventType.Phosphorylate);
       this.subjectRegistry = subjectRegistry;
    }

    public void execute(){

        subject.setState(EventType.Phosphorylate, this.key.toString() + " phosphorylates " + this.value.toString());
        System.out.println(subjectRegistry.getSubject(EventType.Phosphorylate).getState());
        System.out.println(this.key.toString() + " phosphorylates " + this.value);
    }
}
