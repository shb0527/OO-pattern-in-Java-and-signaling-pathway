package ooad.life.cells.pathway.Ligand;


import ooad.life.cells.pathway.Binding;
import ooad.life.cells.pathway.Subject;
import ooad.life.cells.pathway.SubjectRegistry;
import ooad.life.cells.pathway.command.BindCommand;
import ooad.life.cells.pathway.command.CommandFactory;
import ooad.life.cells.pathway.receptors.EpoR;
import ooad.life.cells.pathway.receptors.TGFBReceptor;

import java.util.List;
import java.util.Random;

public class TGFB extends Ligand {
    //signaling molecule
    // binds to the TGFBR (receptor)
    //extracellular
    private static int counter = 0; // Static counter for unique IDs
    private int id;
    private Random rand = new Random();
    String name = "TGFB";
    private   Binding <TGFB, TGFBReceptor> TGFBReceptorbinding;
    private SubjectRegistry subjectRegistry;
    private CommandFactory commandFactory;
    private boolean isBind;
    public TGFB(SubjectRegistry subjectRegistry){

        this.id = ++counter;
        this.subjectRegistry = subjectRegistry;
        this.commandFactory = new CommandFactory(subjectRegistry);
    }
    public void cleavage(){
        System.out.println("TGFB precursors are cleaved");
        System.out.println("TGFB pro-domain is bound to LTBP");

    }

    public void bindingToReceptor(List<TGFBReceptor> tgfbReceptorPool){

        BindCommand bindCommand = commandFactory.createBindCommand(this, getRandomReceptor(tgfbReceptorPool),subjectRegistry);
        bindCommand.execute();
        TGFBReceptorbinding = bindCommand.getBinding();
        TGFBReceptorbinding.getValue().setBind(true);
        this.setBind(true);
    }

    public Binding<TGFB, TGFBReceptor> getBinding(){
        return TGFBReceptorbinding;
    }

    public TGFBReceptor getRandomReceptor(List<TGFBReceptor> tgfbReceptorPool){
        List<TGFBReceptor> tempEpoR = tgfbReceptorPool.stream()
                .filter(tgfbr -> !tgfbr.getBind())
                .toList();
        return tempEpoR.get(rand.nextInt(tempEpoR .size()));
    }
    public void setBind(boolean value){
        this.isBind = value;
    }

    public boolean getBind(){
        return this.isBind;
    }
    public String toString() {
        return  name + "(" + id + ")";
    }
}
