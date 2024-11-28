package ooad.life.cells.pathway.Kinases;

import ooad.life.cells.pathway.EventType;
import ooad.life.cells.pathway.Nucleus;
import ooad.life.cells.pathway.Subject;
import ooad.life.cells.pathway.SubjectRegistry;

public class Kinase {
    protected static int counter = 0;
    protected int id;
    String name = "Kinase";
    protected boolean isActive;

    protected boolean isInNucleus;
    public Kinase(){
        this.id = ++counter;
    }

    public int getId() { return id;}


    public void setActivation(boolean value){
        this.isActive = value;
    }
    public boolean getActivation(){
        return this.isActive;
    }

    public String toString() {
        return  name + "(" + id + ")";
    }
}
