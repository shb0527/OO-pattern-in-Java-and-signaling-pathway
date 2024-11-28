package ooad.life.cells.pathway.receptors;

import ooad.life.cells.pathway.EventType;
import ooad.life.cells.pathway.Subject;
import ooad.life.cells.pathway.SubjectRegistry;
import ooad.life.cells.pathway.command.CommandFactory;
import ooad.life.cells.pathway.command.DephosphorylateCommand;

public class Receptor {
    private static int counter = 0; // Static counter for unique IDs
    private int id;
    private boolean isActive;
    String name = "Receptor";
    private SubjectRegistry subjectRegistry;
    private CommandFactory commandFactory;
    private Subject subject;
    private boolean isPhosphorylated;
    public Receptor(SubjectRegistry subjectRegistry){
        this.subjectRegistry = subjectRegistry;
        this.id = ++counter;
        this.commandFactory = new CommandFactory(subjectRegistry);
    }
    public int getId() { return id;}

    @Override
    public String toString() {
        return name + "(" + id + ")";
    }
    public void phosphorylation(){}


    public void setActivation(boolean value){
        subject = subjectRegistry.getSubject(EventType.Activate);
        subject.setState(EventType.Activate, name + " is activated.");
        this.isActive = value;
    }
    public void dephosphorylation(){

        DephosphorylateCommand dpc = commandFactory.createDephoshorylatecommand(this, subjectRegistry);
        dpc.execute();
        setPhosphorylation(false);

    }

    public void setPhosphorylation(boolean value){
        this.isPhosphorylated = value;
    }

    public boolean getPhosphorylation(){
        return this.isPhosphorylated;
    }
    public boolean getActivation(){
        return this.isActive;
    }
}
