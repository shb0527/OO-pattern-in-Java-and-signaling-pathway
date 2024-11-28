package ooad.life.cells.pathway.Kinases;

import ooad.life.cells.pathway.*;
import ooad.life.cells.pathway.command.BindCommand;
import ooad.life.cells.pathway.command.CommandFactory;
import ooad.life.cells.pathway.command.PhosphorylateCommand;
import ooad.life.cells.pathway.receptors.EpoR;

import java.util.List;
import java.util.Random;

public class JAK2 extends Kinase {
//Tyrosine kinase.

    String name = "JAK2";
   private Random rand = new Random();
    Binding<JAK2, EpoR> binding ;
    private TyrosineResidue tyrosineResidue;
    private boolean phosphoTyrosineResidue;
    private List<EpoR> epoRList;
    private List<TyrosineResidue> tyrosineResidueList;
    private SubjectRegistry subjectRegistry;
    private CommandFactory commandFactory;
    private Subject subject;


    public JAK2(List<EpoR> epoRList, List<TyrosineResidue> tyrosineResidueList,
                SubjectRegistry subjectRegistry){
        super();
        this.epoRList = epoRList;
        this.subjectRegistry = subjectRegistry;
        commandFactory = new CommandFactory(subjectRegistry);
        this.tyrosineResidueList = tyrosineResidueList;
    }


    public void bindingToReceptor(){
        List<EpoR> tempEpoR = epoRList.stream()
                .filter(epoR -> !epoR.getBind())
                .toList();
        System.out.println("boiding...." + tempEpoR);
        if(tempEpoR.size() > 0) {
            BindCommand bind = commandFactory.createBindCommand(this,
                    tempEpoR.get(rand.nextInt(tempEpoR.size())), subjectRegistry);

            bind.execute();
            binding = bind.getBinding();
            binding.getValue().setBind(true);
        }

    }

    public Binding<JAK2, EpoR> getBinding(){
        return this.binding;
    }
    public void phosphorylation(List<TyrosineResidue> tyrosineResidueList){
        List<TyrosineResidue> tempTR = tyrosineResidueList.stream()
                .filter(TR -> !TR.getPhosphorylation())
                .toList();

        if(!tempTR.isEmpty()) {

            tyrosineResidue =  tempTR.get(rand.nextInt(tempTR.size()));

            PhosphorylateCommand pc = commandFactory.createPhoshorylatecommand(this, tyrosineResidue, subjectRegistry);
            pc.execute();
            tyrosineResidue.setPhosphorylation(true);
        }
    }

    public TyrosineResidue getTyrosineResidue() {
        return this.tyrosineResidue;
    }

    public void setActivation(boolean activated){

        subject = subjectRegistry.getSubject(EventType.Activate);
        subject.setState(EventType.Activate, this.toString() + " is activated.");
        this.isActive = activated;
   }

    public boolean getActivation(){
        return this.isActive;
    }
    public String toString() {
        return  name + "(" + id + ")";
    }


}
