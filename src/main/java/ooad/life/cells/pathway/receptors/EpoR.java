package ooad.life.cells.pathway.receptors;

import ooad.life.cells.pathway.EventType;
import ooad.life.cells.pathway.Subject;
import ooad.life.cells.pathway.SubjectRegistry;
import ooad.life.cells.pathway.TyrosineResidue;
import ooad.life.cells.pathway.command.CommandFactory;
import ooad.life.cells.pathway.command.PhosphorylateCommand;

import java.util.List;
import java.util.Random;

public class EpoR extends Receptor{
    //Cytokine Receptor
     String name = "EpoR";
     private Random rand = new Random();
    private static int counter = 0; // Static counter for unique IDs
    private int id;
    private List<TyrosineResidue> tyrosineResidueList;
    private SubjectRegistry subjectRegistry;
    private CommandFactory commandFactory;
    private Subject subject;
    private boolean isActive;
    private boolean isBind;
    public EpoR(List<TyrosineResidue> tyrosineResidueList, SubjectRegistry subjectRegistry){
        super(subjectRegistry);
        this.id = ++counter;
        this.tyrosineResidueList = tyrosineResidueList;
        this.subjectRegistry = subjectRegistry;
        this.commandFactory = new CommandFactory(subjectRegistry);
    }
    public void dimerization(){

        System.out.println("A dimeric receptor has formed");
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
