package ooad.life.cells.pathway.GN;

import ooad.life.cells.pathway.EventType;
import ooad.life.cells.pathway.InnerCell;
import ooad.life.cells.pathway.Subject;
import ooad.life.cells.pathway.SubjectRegistry;
import ooad.life.cells.pathway.command.CommandFactory;

import java.util.List;

public class GTP {
    private static int counter = 0; // Static counter for unique IDs
    private int id;
    private String name = "GTP";
    private List<GDP> gdpPool;
    private InnerCell innerCell;
    private CommandFactory commandFactory;
    private SubjectRegistry subjectRegistry;
    private Subject subject;

    public GTP(InnerCell innerCell, SubjectRegistry subjectRegistry){
        this.gdpPool = innerCell.getGdpPool();
        this.innerCell = innerCell;
        this.id = ++counter;
        this.subjectRegistry =subjectRegistry;
    }
    public GDP hydrolyze(){
        System.out.println("Hydrolyzing GTP to GDP..");
        subject = subjectRegistry.getSubject(EventType.Hydrolyze);
        subject.setState(EventType.Hydrolyze, "Hydrolyzing GTP to GDP ");
        GDP gdp = new GDP(innerCell.getGtpPool(), subjectRegistry);
        innerCell.addGDP(gdp);
        System.out.println("New GDP is just created.");
        return gdp;
    }
    public String toString() {
        return  name + "(" + id + ")";
    }
}
