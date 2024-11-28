package ooad.life.cells.pathway.GN;

import ooad.life.cells.pathway.EventType;
import ooad.life.cells.pathway.RegulatoryProtein.SOS;
import ooad.life.cells.pathway.Subject;
import ooad.life.cells.pathway.SubjectRegistry;
import ooad.life.cells.pathway.command.CommandFactory;

import java.util.List;
import java.util.Random;

public class GDP {


    private static int counter = 0; // Static counter for unique IDs
    private int id;
    private List<GTP> gtpList;
    private String name = "GDP";
    private Random rand = new Random();
    private CommandFactory commandFactory;
    private SubjectRegistry subjectRegistry;
    private Subject subject;

    public GDP(List<GTP> gtpList, SubjectRegistry subjectRegistry) {
        this.gtpList = gtpList;
        this.id = ++counter;
        this.subjectRegistry= subjectRegistry;

    }

    @Override
    public String toString() {
        return  name + "(" + id + ")";
    }

    public GTP exchange() {
        System.out.println("Exchange of GDP for GTP..");
        subject = subjectRegistry.getSubject(EventType.Exchange);
        subject.setState(EventType.Exchange, "Exchange of GDP for GTP.. ");
        return gtpList.get(rand.nextInt(gtpList.size()));
    }

}
