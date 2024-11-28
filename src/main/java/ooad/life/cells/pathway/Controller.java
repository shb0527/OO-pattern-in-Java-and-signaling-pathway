package ooad.life.cells.pathway;

import ooad.life.cells.pathway.command.CommandFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cell")
public class Controller {

    private SignalingWorld signalingWorld;
    private SubjectRegistry subjectRegistry;
    private SimulationService simulationService;
    @Autowired
    public Controller(SimulationService simulationService, SubjectRegistry subjectRegistry) {
        this.simulationService = simulationService;
        this.subjectRegistry = subjectRegistry;
        this.signalingWorld = simulationService.createSignalingWorld();

        registerObservers();

    }
   private void registerObservers(){
       registerObserver("PathwayStart", EventType.RunPathway);
       registerObserver("Bind", EventType.Bind);
       registerObserver("Phosphate", EventType.Phosphorylate);
       registerObserver("Dephospho", EventType.Dephosphorylate);
       registerObserver("Activate", EventType.Activate);
       registerObserver("Deactivate", EventType.Deactivate);
       registerObserver("EnterNucleus", EventType.EnterNucleus);
       registerObserver("Hydrolyze", EventType.Hydrolyze);
       registerObserver("Exchange", EventType.Exchange);
   }

   private void registerObserver(String elementId, EventType eventType){
       Subject subject = subjectRegistry.getSubject(eventType);
       ObserverUI observerUI = new ObserverUI(elementId);
       subject.addObserver(observerUI, eventType);
   }

    @PostMapping("/simulate")
    public void Simulate(@RequestBody SimulationRequest request){
        signalingWorld.RunAll();
        String result = "Simulation completed successfully!";
        System.out.println(result);
    }


    @GetMapping("/init")
    public String getInit() throws InterruptedException {
       signalingWorld.RunAll();
        return subjectRegistry.getSubject(EventType.RunPathway).getState();
    }

    @GetMapping("/Bind")
    public String getBind(){
        return subjectRegistry.getSubject(EventType.Bind).getState();
    }

    @GetMapping("/Phosphate")
    public String getPhosphate(){
        return subjectRegistry.getSubject(EventType.Phosphorylate).getState();
    }

    @GetMapping("/Dephospho")
    public String getDephosphorylate(){
        return subjectRegistry.getSubject(EventType.Dephosphorylate).getState();
    }

    @GetMapping("/Activate")
    public String getActivate(){
        return subjectRegistry.getSubject(EventType.Activate).getState();
    }

    @GetMapping("/Deactivate")
    public String getDeactivate(){
        return subjectRegistry.getSubject(EventType.Deactivate).getState();
    }

    @GetMapping("/EnterNucleus")
    public String getNucleus(){
        return subjectRegistry.getSubject(EventType.EnterNucleus).getState();
    }

    @GetMapping("/Hydrolyze")
    public String getHydrolysis(){
        return subjectRegistry.getSubject(EventType.Hydrolyze).getState();
    }

    @GetMapping("/Exchange")
    public String getExchange(){
        return subjectRegistry.getSubject(EventType.Exchange).getState();
    }

    @GetMapping("/Cytokine")
    public int getCytokine() {
        return signalingWorld.getOuterCell().getCytokinePool().size();
    }

    @GetMapping("/EGF")
    public int getEGF() {
        return signalingWorld.getOuterCell().getEgfPool().size();
    }

    @GetMapping("/TGFB")
    public int getTGFB() {
        return signalingWorld.getOuterCell().getTgfbPool().size();
    }

    @GetMapping("/EpoR")
    public int getEpoR() {
        return signalingWorld.getInnerCell().getEpoRPool().size();
    }

    @GetMapping("/RTK")
    public int getRTK() {
        return signalingWorld.getInnerCell().getRtksPool().size();
    }

    @GetMapping("/TGFBR")
    public int getTGFBR() {
        return signalingWorld.getInnerCell().getTgfbReceptorsPool().size();
    }

    @GetMapping("/JAK2")
    public int getJAK2(){return signalingWorld.getInnerCell().getJAK2Pool().size();}

    @GetMapping("/MAPKinase")
    public int getMAPKinase(){ return signalingWorld.getInnerCell().getMaPkinases().size();}

    @GetMapping("/MEK")
    public int getMEK() { return signalingWorld.getInnerCell().getMekPool().size();}

    @GetMapping("/Raf")
    public int getRaf() { return signalingWorld.getInnerCell().getRafPool().size();}

    @GetMapping("/Ras")
    public int getRas() { return signalingWorld.getInnerCell().getRasPool().size();}

    @GetMapping("/SHP1")
    public int getSHP1() {return signalingWorld.getInnerCell().getSHP1Pool().size();}

    @GetMapping("/Ski")
    public int getSki() { return signalingWorld.getInnerCell().getSkiPool().size();}

    @GetMapping("/SOCS")
    public int getSOCS() { return signalingWorld.getInnerCell().getSOCSPool().size();}

    @GetMapping("/SOS")
    public int getSOS() { return signalingWorld.getInnerCell().getSOSPool().size();}

    @GetMapping("/Smad")
    public int getSmad() {return signalingWorld.getInnerCell().getSmadPool().size();}

    @GetMapping("/STAT")
    public int getSTAT() { return signalingWorld.getInnerCell().getSTATPool().size();}

    @GetMapping("/TyrosineResidue")
    public int getTR() { return signalingWorld.getInnerCell().getTyrosineResiduePool().size();}

    @GetMapping("/GTP")
    public int getGTP() {
        return signalingWorld.getInnerCell().getGtpPool().size();
    }

    @GetMapping("/GDP")
    public int getGDP() {
        return signalingWorld.getInnerCell().getGdpPool().size();
    }

    @PostMapping("/move")
    public String movePlayer() {
        //game.movePlayer();
        return signalingWorld.getInnerCell().toString();
    }
}
