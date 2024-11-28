package ooad.life.cells.pathway;

import ooad.life.cells.pathway.GN.GDP;
import ooad.life.cells.pathway.GN.GTP;
import ooad.life.cells.pathway.RegulatoryProtein.SOS;
import ooad.life.cells.pathway.command.BindCommand;
import ooad.life.cells.pathway.command.CommandFactory;

import java.util.List;
import java.util.Random;

public class Ras {
    /*
    a small GTP-binding protein, switch protein
    Ras-GTP : Active State
    Ras-GDP : InActive State
     */
    private Binding<Ras, GTP> RasGTP;
    private  Binding<Ras, GDP> RasGDP;
    private  Binding<Binding<Ras, GTP>, GAP> GAPRasGTP;

    private boolean isActive;
    private Random rand = new Random();
    private static int counter = 0;
    private int id;

    String name = "Ras";
    private List<SOS> sosList;
    private SOS sos;
    private List<GAP> gaps;

    private Subject subject;
    private SubjectRegistry subjectRegistry;
    private CommandFactory commandFactory;
    public Ras(List<SOS> sosList, List<GAP> gaps, SubjectRegistry subjectRegistry){
        this.id = ++counter;
        this.sosList = sosList;
        this.gaps = gaps;
        this.subjectRegistry = subjectRegistry;
        this.commandFactory = new CommandFactory(subjectRegistry);
    }
    public int getId() { return id;}

    @Override
    public String toString() {
        return name+ "(" + id + ")";
    }

    public void bindToGTP(Binding<Ras, GDP> rasGDP){

        sos = sosList.get(rand.nextInt(sosList.size()));
        sos.catalyze(rasGDP);
        GTP exchangedGTP = rasGDP.getValue().exchange();

        rasGDP.unbind();

        //Ras must release GDP first, Ras cannot bind GTP without dissociating GDP
        BindCommand bindCommand =  commandFactory.createBindCommand(this, exchangedGTP, subjectRegistry);

        bindCommand.execute();
        RasGTP = bindCommand.getBinding();

        setActivation(true);
    }

    public void bindToGDP(Binding<Ras, GTP> rasGTP){

        BindCommand bindCommand = commandFactory.createBindCommand(rasGTP,gaps.get(rand.nextInt(gaps.size())) ,subjectRegistry);
        bindCommand.execute();
        GAPRasGTP = bindCommand.getBinding();
       //activating protein, accelerate GTPase activity by 100 times
        GDP hydrolyzedGDP = rasGTP.getValue().hydrolyze();
        BindCommand bindCommand2 = commandFactory.createBindCommand(this, hydrolyzedGDP, subjectRegistry);
        System.out.println("............................");
        bindCommand2.execute();
        RasGDP = bindCommand2.getBinding();
        setActivation(false);
    }

    public void setActivation(boolean active){
        subject = subjectRegistry.getSubject(EventType.Activate);
        if(active) {

            subject.setState(EventType.Activate, this.toString() + " is activated.");
            System.out.println(this.name + " is activated");
        } else{
            subject.setState(EventType.Deactivate, this.toString() + " is deactivated.");
            System.out.println(this.name + " is deactivated");
        }
        this.isActive = active;
    }

   public boolean getStatus(){
        return this.isActive;
   }
   public Binding<Ras, GTP> getRasGTP(){ return RasGTP;}
   public Binding<Ras, GDP> getRasGDP(){ return RasGDP;}





}
