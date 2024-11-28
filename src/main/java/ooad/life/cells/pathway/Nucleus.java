package ooad.life.cells.pathway;

import ooad.life.cells.pathway.Kinases.Kinase;
import ooad.life.cells.pathway.Kinases.MAPkinase;
import ooad.life.cells.pathway.TranscriptionFactors.STAT;
import ooad.life.cells.pathway.TranscriptionFactors.Smad;
import ooad.life.cells.pathway.TranscriptionFactors.TranscriptionFactor;
import ooad.life.cells.pathway.command.CommandFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Nucleus {
    private List<Smad> Smads = new ArrayList<>();
    private List<STAT> Stats = new ArrayList<>();
    private List<MAPkinase> maPkinases = new ArrayList<>();


    private Subject subject;
    private SubjectRegistry subjectRegistry;
    private CommandFactory commandFactory;


    public Nucleus(SubjectRegistry subjectRegistry){
        this.subjectRegistry = subjectRegistry;
        this.subject = subjectRegistry.getSubject(EventType.EnterNucleus);
    }


    public void addTranscriptionfactors(TranscriptionFactor tf){

        if(tf instanceof Smad){
            subject.setState(EventType.EnterNucleus, "Smad("+ ((Smad)tf).getId() + ") entered the nucleus");
            Smads.add((Smad)tf);
        }
        else if(tf instanceof STAT){
            subject.setState(EventType.EnterNucleus, "Stat("+ ((STAT)tf).getId() + ") entered the nucleus");
            Stats.add((STAT)tf);
        }
    }
    public void addKinase(Kinase kinase){
        if(kinase instanceof MAPkinase){

            subject.setState(EventType.EnterNucleus, "mapKinase("+ ((MAPkinase)kinase).getId() + ") entered the nucleus");
            maPkinases.add((MAPkinase)kinase);
        }
    }

    public void getMoleculesInNucleus(){
        List<List<?>> alls = List.of(Smads, Stats, maPkinases);

       String str =  alls.stream()
                .map(group -> group.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", ")))
                .collect(Collectors.joining("\n"));

       System.out.println("\n ::: Molecules in the nucleus are :::: \n"+ str + "\n");

    }

    public int getSize(){
        List<Object> alls = new ArrayList<>();
        alls.addAll(Smads);
        alls.addAll(Stats);
        alls.addAll(maPkinases);

        return alls.size();
    }
}
