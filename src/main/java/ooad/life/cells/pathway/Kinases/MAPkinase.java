package ooad.life.cells.pathway.Kinases;

import ooad.life.cells.pathway.*;
import ooad.life.cells.pathway.TranscriptionFactors.TranscriptionFactor;
import ooad.life.cells.pathway.command.CommandFactory;
import ooad.life.cells.pathway.command.PhosphorylateCommand;

import java.util.List;
import java.util.Random;

public class MAPkinase extends Kinase {
    // MAP kinase
   String name = "MAP kinase";
   private List<TranscriptionFactor> tfList;
   private Random rand = new Random();
   private boolean isInNucleus;
   private InnerCell innerCell;
   private CommandFactory commandFactory;
   private SubjectRegistry subjectRegistry;
   private Subject subject;

private Nucleus nucleus;
   public MAPkinase(InnerCell innerCell, SubjectRegistry subjectRegistry){
      super();
      this.tfList = innerCell.getTfPool();
      this.innerCell = innerCell;
      this.subjectRegistry =subjectRegistry;
      this.commandFactory = new CommandFactory(subjectRegistry);
      this.nucleus = innerCell.getNucleus();
   }


    public void phosphorylation(){
        //phosphorylate transcription factors

        PhosphorylateCommand pc = commandFactory.createPhoshorylatecommand(this,
                tfList.get(rand.nextInt(tfList.size())), subjectRegistry);

        pc.execute();
        setActivation(true);
    }

    @Override
    public String toString() {
        return name + "(" + id + ")"; // Use the private name field in the subclass
    }
    public void enterNucleus(Nucleus nucleus){

       if(!isInNucleus){
           System.out.println("MapKinase just entered Nucleus");
           subject = subjectRegistry.getSubject(EventType.EnterNucleus);
           subject.setState(EventType.EnterNucleus, this.toString());
           nucleus.addKinase(this);
           isInNucleus = true;
           innerCell.removeMapKinase(this);
       }


    }
}
