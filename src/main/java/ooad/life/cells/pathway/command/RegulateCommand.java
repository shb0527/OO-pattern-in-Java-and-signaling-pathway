package ooad.life.cells.pathway.command;

import ooad.life.cells.pathway.EventType;
import ooad.life.cells.pathway.Kinases.Kinase;
import ooad.life.cells.pathway.RegulatoryProtein.RegulatoryProtein;
import ooad.life.cells.pathway.Subject;
import ooad.life.cells.pathway.SubjectRegistry;
import ooad.life.cells.pathway.TranscriptionFactors.TranscriptionFactor;
import ooad.life.cells.pathway.receptors.Receptor;

public class RegulateCommand<T,U> implements Command {

    private RegulatoryProtein rp;
    private TranscriptionFactor tf;
    private Kinase kinase;
    private T key;
    private U value;
    private Subject subject;

    public RegulateCommand(T key, U value, SubjectRegistry subjectRegistry){
        this.key = key;
        this.value = value;
        this.subject = subjectRegistry.getSubject(EventType.Deactivate);
    }


    public void execute(){
        System.out.println( value + " is deactivated by " + key);

         subject.setState(EventType.Deactivate,  value + " is deactivated by " + key);
         if(value instanceof TranscriptionFactor){
             ((TranscriptionFactor) value).setActivation(false);
         }else if(value instanceof Kinase){
             ((Kinase) value).setActivation(false);
         } else if(value instanceof Receptor){
             ((Receptor) value).setActivation(false);
         }
    }
}
