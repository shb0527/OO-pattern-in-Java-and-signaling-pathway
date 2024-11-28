package ooad.life.cells.pathway.command;

import ooad.life.cells.pathway.EventType;
import ooad.life.cells.pathway.Subject;
import ooad.life.cells.pathway.SubjectRegistry;

public class DephosphorylateCommand<T> {
    private T key;
    private Subject subject;
    public DephosphorylateCommand(T key, SubjectRegistry subjectRegistry){

        this.key = key;
        this.subject = subjectRegistry.getSubject(EventType.Dephosphorylate);
    }
    public void execute(){

        subject.setState(EventType.Dephosphorylate,this.key.toString() + " is dephosphorylated." );
        System.out.println(this.key.toString() + " is dephosphorylated.");
    }
}
