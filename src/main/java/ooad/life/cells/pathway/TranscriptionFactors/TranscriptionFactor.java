package ooad.life.cells.pathway.TranscriptionFactors;

import ooad.life.cells.pathway.*;
import ooad.life.cells.pathway.command.CommandFactory;

public class TranscriptionFactor {
    private static int counter = 0;
    private int id;
    private String name = "Transcription Factor";
    private boolean isActive;
    private boolean isBind;
    private Subject subject;
    private SubjectRegistry subjectRegistry;
    private CommandFactory commandFactory;
    public TranscriptionFactor(SubjectRegistry subjectRegistry){

        this.id = ++counter;
        this.subjectRegistry =subjectRegistry;
        this.commandFactory = new CommandFactory(subjectRegistry);
    }
    public TranscriptionFactor(Binding binding){
        this.name = binding.getName();
    }

    public String getName(){
        return name;
    }
    public void setActivation(boolean value){
        subject = subjectRegistry.getSubject(EventType.Activate);
        subject.setState(EventType.Activate, name + " is activated.");
        this.isActive = value;
    }
    public boolean getActivation(){
        return this.isActive;
    }
    public void dimerization(){}
    public void unmaskNLS(){}
    public String toString() {
        return name + " ID: " + id;
    }
    public int getId(){ return id;}
    public void setId(int val) { id = val;}
    public void enterNucleus(Nucleus nucleus){}
    public boolean getBind(){
        return this.isBind;
    }
}
