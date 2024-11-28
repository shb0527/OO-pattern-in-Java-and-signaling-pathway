package ooad.life.cells.pathway.command;

import ooad.life.cells.pathway.Binding;
import ooad.life.cells.pathway.EventType;
import ooad.life.cells.pathway.Subject;
import ooad.life.cells.pathway.SubjectRegistry;
import ooad.life.cells.pathway.TranscriptionFactors.Smad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


public class BindCommand<T,U> implements Command {


    private T key;
    private U value;
    private Binding binding;
    private Subject bindSubject;

    public BindCommand(T key, U value, SubjectRegistry subjectRegistry) {
        this.key = key;
        this.value = value;
        binding = new Binding(key, value);
        this.bindSubject = subjectRegistry.getSubject(EventType.Bind);

    }

    public void execute(){

        bindSubject.setState(EventType.Bind, key.toString() + " is binding to " + value.toString());
        System.out.println(key.toString() + " is binding to " + value.toString());
    }

    public Binding getBinding(){
        return binding;
    }
}
