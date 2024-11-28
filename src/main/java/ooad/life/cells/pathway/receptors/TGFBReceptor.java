package ooad.life.cells.pathway.receptors;

import ooad.life.cells.pathway.EventType;
import ooad.life.cells.pathway.Subject;
import ooad.life.cells.pathway.SubjectRegistry;
import ooad.life.cells.pathway.TranscriptionFactors.Smad;
import ooad.life.cells.pathway.command.CommandFactory;
import ooad.life.cells.pathway.command.DephosphorylateCommand;
import ooad.life.cells.pathway.command.PhosphorylateCommand;

import java.util.List;
import java.util.Random;

public class TGFBReceptor extends Receptor {
    //kinase and receptor at the same time

    private Random rand = new Random();
    private static int counter = 0; // Static counter for unique IDs
    private int id;
    private String name = "TGFB Receptor";
    private SubjectRegistry subjectRegistry;
    private CommandFactory commandFactory;
    private Subject subject;
    private PhosphorylateCommand pc;
    private Smad smad;
    private boolean isActive;
    private boolean isBind;

    private boolean isPhosphorylated;
    public TGFBReceptor(List<Smad> smadList, SubjectRegistry subjectRegistry) {
        super(subjectRegistry);
        this.id = ++counter;
        this.subjectRegistry = subjectRegistry;
        this.commandFactory = new CommandFactory(subjectRegistry);
    }

    public String getName(){
        return name;
    }
    public void setActivation(boolean value){
        subject = subjectRegistry.getSubject(EventType.Activate);
        subject.setState(EventType.Activate, this.toString() + " is activated.");
        this.isActive = value;
    }
    public void phosphorylation(List<Smad> smads){
        //activates transcription factor - Smad
        List<Smad> tempSmads = smads.stream()
                .filter(smad -> !smad.getActivation() && !smad.isInNucleus() && !smad.isPhosphorylated())
                .toList();
        smad = tempSmads.get(rand.nextInt(tempSmads.size()));
        pc = commandFactory.createPhoshorylatecommand(this, smad, subjectRegistry);
        pc.execute();
        smad.setPhosphorylated(true);

        super.setActivation(true);
    }
    public void dephosphorylation(){

        DephosphorylateCommand dpc = commandFactory.createDephoshorylatecommand(this, subjectRegistry);
        dpc.execute();
        setPhosphorylation(false);

    }
    public void setPhosphorylation(boolean value){
        this.isPhosphorylated = value;
    }
    public Smad getphosphorylatedSmad(){
        return smad;
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
