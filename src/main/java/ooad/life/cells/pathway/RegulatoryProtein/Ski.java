package ooad.life.cells.pathway.RegulatoryProtein;

import ooad.life.cells.pathway.Binding;
import ooad.life.cells.pathway.InnerCell;
import ooad.life.cells.pathway.SubjectRegistry;
import ooad.life.cells.pathway.TranscriptionFactors.Smad;
import ooad.life.cells.pathway.TyrosineResidue;
import ooad.life.cells.pathway.command.BindCommand;
import ooad.life.cells.pathway.command.CommandFactory;
import ooad.life.cells.pathway.command.RegulateCommand;

import java.io.LineNumberReader;
import java.util.List;
import java.util.Random;

public class Ski extends RegulatoryProtein {

    //In nucleus, Ski binds to SMad4 in Smad3/Smad4 complexes and disrupt interactions between Smad proteins.
    String name = "Ski";
    private Random rand = new Random();
    private static int counter = 0;
    private int id;
    private List<Smad> smads;
    private SubjectRegistry subjectRegistry;
    private CommandFactory commandFactory;
    private boolean isBind;
    private Smad smad;
    public Ski(InnerCell innerCell, SubjectRegistry subjectRegistry){
        super(subjectRegistry);
        this.id = ++counter;
        this.smads = innerCell.getSmadPool();
        this.subjectRegistry =subjectRegistry;
        this.commandFactory = new CommandFactory(subjectRegistry);
    }
    public void regulation(List<Smad> smads){

        //binds to the Smad3/smad4 complex and disrupt interaction between the Smad protein
        //block transcription activation by the Smad complexes
        List<Smad> tempSmads = smads.stream()
                .filter(smad -> !smad.getBind())
                .toList();

        if(tempSmads.size() > 0){
            smad = tempSmads.get(rand.nextInt(tempSmads.size()));
            BindCommand Skibinding = commandFactory.createBindCommand(this,smad, subjectRegistry);

            Skibinding.execute();
            RegulateCommand rc = commandFactory.createRegulateCommand(this, smad, subjectRegistry);
            rc.execute();
        }



    }

    public String toString() {
        return name + "( " + id  + ")";
    }

}