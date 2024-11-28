package ooad.life.cells.pathway.RegulatoryProtein;

import ooad.life.cells.pathway.Kinases.JAK2;
import ooad.life.cells.pathway.Subject;
import ooad.life.cells.pathway.SubjectFactory;
import ooad.life.cells.pathway.SubjectRegistry;
import ooad.life.cells.pathway.TyrosineResidue;
import ooad.life.cells.pathway.command.CommandFactory;
import ooad.life.cells.pathway.command.RegulateCommand;

public class SHP1 extends RegulatoryProtein {

    private JAK2 jak2;
    private static int counter = 0;
    private int id;
    String name = "SHP1";
    private SubjectRegistry subjectRegistry;
    private CommandFactory commandFactory;
    public SHP1(SubjectRegistry subjectRegistry){
        super(subjectRegistry);
        this.id = ++counter;

        this.subjectRegistry = subjectRegistry;
        this.commandFactory = new CommandFactory(subjectRegistry);
    }

    public void regulation(TyrosineResidue tr) {


        RegulateCommand rc = commandFactory.createRegulateCommand(this, tr, subjectRegistry);
        rc.execute();

    }
    public void regulation(JAK2 jak2){

        RegulateCommand rc = commandFactory.createRegulateCommand(this, jak2, subjectRegistry);
        rc.execute();
    }
    public String toString() {
        return name + "(" + id + ")";
    }

}
