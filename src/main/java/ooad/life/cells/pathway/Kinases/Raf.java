package ooad.life.cells.pathway.Kinases;

import ooad.life.cells.pathway.*;
import ooad.life.cells.pathway.GN.GTP;
import ooad.life.cells.pathway.command.BindCommand;
import ooad.life.cells.pathway.command.CommandFactory;

import java.util.List;
import java.util.Random;

public class Raf extends Kinase{
    //serine/threonine protein kinase,
    // meaning it catalyzes the phosphorylation of serine and threonine residues in target proteins.
    String name = "Raf";

    private  Ras ras;
    private  Binding<Raf, Binding<GTP, Ras>> RafGTPRas;
    private CommandFactory commandFactory;
    private SubjectRegistry subjectRegistry;
    private Subject subject;
    private List<MEK> mekList;
    private Random rand = new Random();
    public Raf(SubjectRegistry subjectRegistry, List<MEK> mekList) {

        super();
        this.subjectRegistry = subjectRegistry;
        this.mekList = mekList;
        this.commandFactory = new CommandFactory(subjectRegistry);
    }

    public boolean getStatus(){
        return isActive;
    }
    public void setActivation(boolean status){
        subject = subjectRegistry.getSubject(EventType.Activate);
        subject.setState(EventType.Activate, this.toString() + " is activated.");
        this.isActive = status;
    }
    public void bindingToRasGTP(Binding<Ras, GTP> rasGTP){

        BindCommand bindCommand = commandFactory.createBindCommand(this, rasGTP, subjectRegistry);
        bindCommand.execute();
        RafGTPRas = bindCommand.getBinding();
        setActivation(true);
        List<MEK> tempMEKs = mekList.stream()
                .filter(mek -> !mek.getActivation())
                .toList();
        MEK mek = tempMEKs.get(rand.nextInt(tempMEKs.size()));
        mek.setActivation(true);
    }

    public Binding getRafRasGTP(){
        return RafGTPRas;
    }

    public String toString() {
        return  name + "(" + id + ")";
    }


}
