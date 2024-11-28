package ooad.life.cells.pathway.receptors;

import ooad.life.cells.pathway.*;
import ooad.life.cells.pathway.command.CommandFactory;
import ooad.life.cells.pathway.command.PhosphorylateCommand;

import java.util.List;
import java.util.Random;

public class RTK extends Receptor{
    //tyrsosine kinse activity - rtks are enzymatic receptors
    //intrinsic tyrosine kinase activity
    private static int counter = 0; // Static counter for unique IDs
    private SubjectRegistry subjectRegistry;
    private CommandFactory commandFactory;
    private List<TyrosineResidue> tyrosineResidueList;
    private Subject subject;
    private int id;
    private Random rand = new Random();
    String name = "RTK";
    private boolean isActive;
    private boolean isBind;
    public RTK(List<TyrosineResidue> tyrosineResidueList, SubjectRegistry subjectRegistry){
        super(subjectRegistry);
        this.id = ++counter;
        this.subjectRegistry = subjectRegistry;
        this.tyrosineResidueList = tyrosineResidueList;
        this.commandFactory = new CommandFactory(subjectRegistry);
    }


    public void phosphorylation(){
        List<TyrosineResidue> tempTRs = tyrosineResidueList.stream()
                .filter(tyrosineResidue -> !tyrosineResidue.getActivation())
                .toList();
        TyrosineResidue tr = tempTRs.get(rand.nextInt(tempTRs.size()));
        PhosphorylateCommand pc = commandFactory.createPhoshorylatecommand(this,
                tr, subjectRegistry);
        tr.setActivation(true);
        pc.execute();
    }

    public void setActivation(boolean value){
        subject = subjectRegistry.getSubject(EventType.Activate);
        subject.setState(EventType.Activate, this.toString() + " is activated.");
        this.isActive = value;
    }

    public void setBind(boolean value){
        this.isBind = value;
    }

    public boolean getBind(){
        return this.isBind;
    }
    public String toString() {
        return name + "(" + id + ")";
    }

}
