package ooad.life.cells.pathway.RegulatoryProtein;

import ooad.life.cells.pathway.SubjectRegistry;
import ooad.life.cells.pathway.command.CommandFactory;

public class RegulatoryProtein {

    private static int counter = 0;
    private int id;
    private SubjectRegistry subjectRegistry;
    private CommandFactory commandFactory;
    String name = "Regulatory Protein";
    public RegulatoryProtein(SubjectRegistry subjectRegistry){

        this.id = ++counter;
        this.subjectRegistry = subjectRegistry;
        this.commandFactory = new CommandFactory(subjectRegistry);
    }

    public int getId() { return id;}

    @Override
    public String toString() {
        return name + "(" + id + ")";
    }


    public void regulation(){}
}
