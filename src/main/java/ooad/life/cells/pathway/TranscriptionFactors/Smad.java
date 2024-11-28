package ooad.life.cells.pathway.TranscriptionFactors;

import ooad.life.cells.pathway.*;
import ooad.life.cells.pathway.RegulatoryProtein.SOS;
import ooad.life.cells.pathway.RegulatoryProtein.Ski;
import ooad.life.cells.pathway.command.BindCommand;
import ooad.life.cells.pathway.command.CommandFactory;

import java.util.List;
import java.util.Random;

public class Smad extends TranscriptionFactor {
    //intreceullar signaling molecules
    private Random rand = new Random();
    private String name = "Smad";

    private InnerCell innerCell;
    private boolean isPhosphorylated;
    private boolean inNucleus;
    private static int counter = 0;
    private int id;
    private boolean isBind;

    private Subject subject;
    private SubjectRegistry subjectRegistry;
    private CommandFactory commandFactory;
    private List<Smad> smads;
    private Nucleus nucleus;
    public Smad(InnerCell innerCell, SubjectRegistry subjectRegistry){
       super(subjectRegistry);
       this.id = ++counter;
       this.smads = innerCell.getSmadPool();
       this.innerCell = innerCell;
       this.subjectRegistry = subjectRegistry;
       this.commandFactory = new CommandFactory(subjectRegistry);
       this.nucleus = innerCell.getNucleus();
    }

    public Smad(Binding<Smad, Smad> SmadComplex){super(SmadComplex);}

    public Smad fromBinding(Binding<Smad, Smad> binding) {
        return binding.getKey();
    }

    public Smad formingSmadComplex(Smad s1, Smad s2){
        //Forming Smad complex

        BindCommand bindCommand = commandFactory.createBindCommand(s1, s2, subjectRegistry);
        bindCommand.execute();

        return fromBinding(bindCommand.getBinding());
    }

    public String getName(){
        return name;
    }

    @Override
    public void setActivation(boolean status){
        super.setActivation(status);
        subject = subjectRegistry.getSubject(EventType.Activate);
        subject.setState(EventType.Activate, this.toString() + " is activated.");
        this.isPhosphorylated = status;
    }
    @Override
    public boolean getActivation(){
        return super.getActivation();
    }

    public void unmaskNLS(){
        System.out.println("nuclear-localization signal is unmasked");
    }
    public boolean getBind(){
        return this.isBind;
    }

    public void enterNucleus(Nucleus nucleus){
        if(!this.inNucleus){
            subject = subjectRegistry.getSubject(EventType.EnterNucleus);
            subject.setState(EventType.EnterNucleus, this.toString());
            nucleus.addTranscriptionfactors(this);
            innerCell.removeSmad(this);
            this.inNucleus = true;
        }
    }


    public String toString() {
        return name + "(" + id + ")";
    }
    public boolean isInNucleus(){
        return inNucleus;
    }

    public boolean isPhosphorylated(){
        return isPhosphorylated;
    }

    public void setPhosphorylated(boolean value){
        this.isPhosphorylated = value;
    }
    public void setBind(boolean value){
        this.isBind = value;
    }


    public Smad getRandomSmad(List<Smad> smads){
        List<Smad> tempSmads = smads.stream()
                .filter(smad -> smad.getId() != this.getId() && !smad.inNucleus )
                .toList();
        return smads.get(rand.nextInt(smads.size()));
    }
}
