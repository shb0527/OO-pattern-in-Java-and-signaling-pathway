package ooad.life.cells.pathway;

import ooad.life.cells.pathway.command.CommandFactory;

public class TyrosineResidue {

    //amino acid
    String name = "Tyrosine Residue";
    private static int counter = 0;
    private int id;

    private Subject subject;
    private SubjectRegistry subjectRegistry;
    private CommandFactory commandFactory;
    private boolean isPhosphorlyated;
    private boolean isBind;
    public TyrosineResidue(SubjectRegistry subjectRegistry){
        this.subjectRegistry = subjectRegistry;
        this.id = ++counter;
        this.commandFactory = new CommandFactory(subjectRegistry);
    }

    public int getId() { return id;}

    @Override
    public String toString() {
        return name + "(" + id + ")";
    }

    private boolean isActive;

    public void setActivation(boolean value){
        subject = subjectRegistry.getSubject(EventType.Activate);
        subject.setState(EventType.Activate, this.toString() + " is activated.");
        this.isActive = value;
    }
    public boolean getActivation(){
        return this.isActive;
    }
    public void setBind(boolean value){
        this.isBind = value;
    }
    public boolean getBind(){
        return this.isBind;
    }
    public void setPhosphorylation(boolean value){
        this.isPhosphorlyated = value;
    }
    public boolean getPhosphorylation(){
        return this.isPhosphorlyated;
    }
}
