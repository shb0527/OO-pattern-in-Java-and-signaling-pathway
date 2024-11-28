package ooad.life.cells.pathway.RegulatoryProtein;

import ooad.life.cells.pathway.Kinases.JAK2;
import ooad.life.cells.pathway.SubjectRegistry;
import ooad.life.cells.pathway.TranscriptionFactors.STAT;
import ooad.life.cells.pathway.TyrosineResidue;
import ooad.life.cells.pathway.command.BindCommand;
import ooad.life.cells.pathway.command.CommandFactory;
import ooad.life.cells.pathway.command.RegulateCommand;
import ooad.life.cells.pathway.receptors.EpoR;
import org.springframework.aop.config.AbstractInterceptorDrivenBeanDefinitionDecorator;

public class SOCS extends RegulatoryProtein {

    //negative feedback

    private static int counter = 0;
    private int id;
    String name = "SOCS";
    private SubjectRegistry subjectRegistry;
    private CommandFactory commandFactory;
    public SOCS(SubjectRegistry subjectRegistry){
        super(subjectRegistry);
        this.id = ++counter;
        this.subjectRegistry = subjectRegistry;
        this.commandFactory = new CommandFactory(subjectRegistry);
    }
    public void regulation(TyrosineResidue tr){

        RegulateCommand rc = commandFactory.createRegulateCommand(this, tr, subjectRegistry);
        rc.execute();
    }

    public void regulation(JAK2 jak2){

        RegulateCommand rc = commandFactory.createRegulateCommand(this, jak2, subjectRegistry);
        rc.execute();
    }


    public void regulation(STAT stat){

        RegulateCommand rc = commandFactory.createRegulateCommand(this, stat, subjectRegistry);
        rc.execute();

    }

    public void regulation(EpoR epoR){

        RegulateCommand rc = commandFactory.createRegulateCommand(this, epoR, subjectRegistry);
        rc.execute();

    }

    public String toString() {
        return name + "(" + id + ")";
    }

}
