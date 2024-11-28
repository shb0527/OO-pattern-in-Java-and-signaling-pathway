package ooad.life.cells.pathway.Kinases;

import ooad.life.cells.pathway.Binding;
import ooad.life.cells.pathway.EventType;
import ooad.life.cells.pathway.Subject;
import ooad.life.cells.pathway.SubjectRegistry;
import ooad.life.cells.pathway.command.BindCommand;
import ooad.life.cells.pathway.command.CommandFactory;

public class MEK extends Kinase{

    String name = "MEK";
    private CommandFactory commandFactory;
    private SubjectRegistry subjectRegistry;
    private Subject subject;
    public MEK(SubjectRegistry subjectRegistry){

        super();
        this.subjectRegistry = subjectRegistry;
        this.commandFactory = new CommandFactory(subjectRegistry);
    }
    public void setActivation(boolean val){
        subject = subjectRegistry.getSubject(EventType.Activate);
        subject.setState(EventType.Activate, this.toString() + " is activated.");
        isActive = val;
    }
    public void phosphorylation(MAPkinase mapkinase){
        if(isActive){

            mapkinase.setActivation(true);
            BindCommand bindCommand = commandFactory.createBindCommand(this, mapkinase, subjectRegistry);
            bindCommand.execute();
        }
    }

    public String toString() {
        return  name + "(" + id + ")";
    }

}
