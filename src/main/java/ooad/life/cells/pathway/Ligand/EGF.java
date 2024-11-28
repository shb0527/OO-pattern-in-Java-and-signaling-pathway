package ooad.life.cells.pathway.Ligand;

import ooad.life.cells.pathway.Binding;
import ooad.life.cells.pathway.Subject;
import ooad.life.cells.pathway.SubjectRegistry;
import ooad.life.cells.pathway.command.BindCommand;
import ooad.life.cells.pathway.command.CommandFactory;
import ooad.life.cells.pathway.receptors.EpoR;
import ooad.life.cells.pathway.receptors.RTK;

import java.util.List;
import java.util.Random;

public class EGF extends Ligand {
    //epidermal growth factor
    //binding of EGF molecule to a monomeric receptor

//  binding of a growth factor (e.g., EGF) to an RTK (e.g., the EGF receptor) lead to activation of Ras
    String name = "EGF";
    private static int counter = 0; // Static counter for unique IDs
    private int id;
    private Random rand = new Random();
    private BindCommand bind;
    private List<RTK> rtkList;
    private SubjectRegistry subjectRegistry;
    private CommandFactory commandFactory;
    private Subject subject;
    private Binding<EGF, RTK> binding;
    private boolean isBind;
    public EGF(List<RTK> rtkList, SubjectRegistry subjectRegistry){
       this.id = ++counter;
       this.rtkList = rtkList;
       this.subjectRegistry = subjectRegistry;
       this.commandFactory = new CommandFactory(subjectRegistry);
    }
    public void bindingToReceptor(){

        List<RTK> tempRTK = rtkList.stream()
                .filter(rtk -> !rtk.getBind())
                .toList();
        bind = commandFactory.createBindCommand(this, tempRTK.get(rand.nextInt(tempRTK.size())), subjectRegistry);
        bind.execute();
        binding = bind.getBinding();
        binding.getValue().setBind(true);
    }
    public String toString() {
        return  name + "(" + id + ")";
    }
    public void setBind(boolean value){
        this.isBind = value;
    }

    public boolean getBind(){
        return this.isBind;
    }
    public Binding<EGF, RTK> getBinding(){
        return binding;
    }
}


