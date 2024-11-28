package ooad.life.cells.pathway.Ligand;

import ooad.life.cells.pathway.Kinases.JAK2;
import ooad.life.cells.pathway.Subject;
import ooad.life.cells.pathway.SubjectRegistry;
import ooad.life.cells.pathway.command.BindCommand;
import ooad.life.cells.pathway.command.CommandFactory;
import ooad.life.cells.pathway.receptors.EpoR;
import ooad.life.cells.pathway.Binding;

import java.util.List;
import java.util.Random;

public class Cytokine extends  Ligand{
    //erythropoietin  (EPO)

   //ligand binding causes conformational change
   //phosphorylate
    String name = "Cytokine";
     private Random rand = new Random();
    private static int counter = 0; // Static counter for unique IDs
    private int id;
    BindCommand bindCommand;
    Binding<Cytokine, EpoR> binding;
    private SubjectRegistry subjectRegistry;
    private CommandFactory commandFactory;
    private Subject subject;
    private boolean isBind;
    List<EpoR> epoRList;
    public Cytokine(List<EpoR> epoRList, SubjectRegistry subjectRegistry) {
      this.id = ++counter;
      this.epoRList = epoRList;
      this.subjectRegistry = subjectRegistry;
      this.commandFactory = new CommandFactory(subjectRegistry);
    }

    public String toString() {
        return  name + "(" + id + ")";
    }
   public void bindingToReceptor(){
        //binds to EpoR
       List<EpoR> tempEpoR = epoRList.stream()
               .filter(epoR -> !epoR.getBind())
               .toList();
       BindCommand bindCommand = commandFactory.createBindCommand(this,
               tempEpoR.get(rand.nextInt(tempEpoR.size())), subjectRegistry);
       bindCommand.execute();

       binding = bindCommand.getBinding();
       binding.getValue().setBind(true);
    }
   public Binding getBinding(){
        return binding;
   }
    public void setBind(boolean value){
        this.isBind = value;
    }

    public boolean getBind(){
        return this.isBind;
    }
}
