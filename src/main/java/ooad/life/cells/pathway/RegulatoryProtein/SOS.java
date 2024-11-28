package ooad.life.cells.pathway.RegulatoryProtein;

import ooad.life.cells.pathway.Binding;
import ooad.life.cells.pathway.GN.GDP;
import ooad.life.cells.pathway.Ras;
import ooad.life.cells.pathway.SubjectRegistry;
import ooad.life.cells.pathway.TranscriptionFactors.Smad;
import ooad.life.cells.pathway.command.BindCommand;
import ooad.life.cells.pathway.command.CommandFactory;
import ooad.life.cells.pathway.command.RegulateCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SOS extends RegulatoryProtein {
    private static final Logger logger = LoggerFactory.getLogger(SOS.class);
    //regulatory factor
  //promotes formation of active Ras-GTP
    //GEF
 String name = "SOS";
    private static int counter = 0;
    private int id;
    private SubjectRegistry subjectRegistry;
    private CommandFactory commandFactory;

    public SOS(SubjectRegistry subjectRegistry){
      super(subjectRegistry);
      this.id = ++counter;
      this.subjectRegistry = subjectRegistry;
      this.commandFactory = new CommandFactory(subjectRegistry);

  }

  public void catalyze(Binding<Ras, GDP> RasGDP){
//which catalyzes conversion of inactive GDP-bound Ras to
//the active GTP-bound form

      BindCommand<SOS, Binding<Ras, GDP>> bindCommand = commandFactory.createBindCommand(this, RasGDP, subjectRegistry);
      bindCommand.execute();

      RegulateCommand rc = commandFactory.createRegulateCommand(this, RasGDP, subjectRegistry);
      rc.execute();
    }
    public String toString() {
        return name + "(" + id + ")";
    }


}
