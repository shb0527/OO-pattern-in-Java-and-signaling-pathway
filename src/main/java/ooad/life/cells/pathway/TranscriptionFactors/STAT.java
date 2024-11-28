package ooad.life.cells.pathway.TranscriptionFactors;

import ooad.life.cells.pathway.*;
import ooad.life.cells.pathway.Kinases.JAK2;
import ooad.life.cells.pathway.command.BindCommand;
import ooad.life.cells.pathway.command.CommandFactory;
import ooad.life.cells.pathway.command.PhosphorylateCommand;

import java.util.List;

public class STAT extends TranscriptionFactor {
    //N-terminal SH2 domain that binds to phosphotyrosine in the receptor's cytosolic domain, a central DNA binding domain
    // C-terminal domain with a critical tyrosine residute
    //Once a STAT is bound to the receptor, the C- terminal tyrosine is phosphorylated by an associated JAK kinase
 //Transcription factors that regulate gene expression in response to cytokines.
 String name = "STAT";
    private static int counter = 0;
    private int id;
    private InnerCell innerCell;
    private Nucleus nucleus;
    private boolean inNucleus;
    private Subject subject;
    private SubjectRegistry subjectRegistry;
    private CommandFactory commandFactory;
    private Binding<STAT, TyrosineResidue> binding;
    public STAT(InnerCell innerCell, SubjectRegistry subjectRegistry){
       super(subjectRegistry);
       this.id = ++counter;
       this.subjectRegistry = subjectRegistry;
       this.commandFactory = new CommandFactory(subjectRegistry);
       this.innerCell = innerCell;
       this.nucleus = innerCell.getNucleus();
    }

    public void bindingtophosphoTyrosine(TyrosineResidue tyro){ //or recdptor


        BindCommand  bindCommand = commandFactory.createBindCommand(this, tyro, subjectRegistry);
        bindCommand.execute();
        binding = bindCommand.getBinding();
    }

    public Binding<STAT, TyrosineResidue> getBinding(){
        return this.binding;
    }
   public void phosphorylation(JAK2 jak2){

       PhosphorylateCommand pc = commandFactory.createPhoshorylatecommand(jak2, this, subjectRegistry);
       pc.execute();
       setActivation(true);
   }
    public void dimerization() {
        System.out.println("two STAT proteins formed a dimer");
    }
    public void unmaskNLS(){
        System.out.println("nulcear-localization signal is unmasked");
    }

    public void enterNucleus(Nucleus nucleus){
        subject = subjectRegistry.getSubject(EventType.EnterNucleus);

        if(!this.inNucleus){
            System.out.println(this.toString());
            subject.setState(EventType.EnterNucleus, this.toString());
            nucleus.addTranscriptionfactors(this);
            this.inNucleus = true;
            this.innerCell.removeSTAT(this);
        }
    }
    public String toString() {
        return name + "(" + id + ")";
    }


}
