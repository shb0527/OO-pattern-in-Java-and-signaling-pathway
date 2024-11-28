package ooad.life.cells.pathway;

import ooad.life.cells.pathway.GN.GDP;
import ooad.life.cells.pathway.GN.GTP;
import ooad.life.cells.pathway.Kinases.JAK2;
import ooad.life.cells.pathway.Kinases.MAPkinase;
import ooad.life.cells.pathway.Kinases.MEK;
import ooad.life.cells.pathway.Kinases.Raf;
import ooad.life.cells.pathway.Ligand.Cytokine;
import ooad.life.cells.pathway.Ligand.EGF;
import ooad.life.cells.pathway.Ligand.TGFB;
import ooad.life.cells.pathway.Pathway.*;
import ooad.life.cells.pathway.RegulatoryProtein.*;
import ooad.life.cells.pathway.TranscriptionFactors.STAT;
import ooad.life.cells.pathway.TranscriptionFactors.Smad;
import ooad.life.cells.pathway.receptors.EpoR;
import ooad.life.cells.pathway.receptors.TGFBReceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SignalingWorld {

    private Subject subject;
    private Random rand = new Random();
    private InnerCell innerCell;
    private List<TGFBReceptor> tgfbReceptorList;
    private List<Smad> smadList;
    private List<TGFB> tgfbs;
    private List<Cytokine> cytokineList;
    private List<JAK2> jak2s;
    private List<STAT> stats;
    private List<EGF> egfList;
    private List<Ras> rasList;
    private List<GDP> gdpList;
    private List<MAPkinase> maPkinases;
    private List<MEK> mekList;
    private List<Raf> rafList;
    private Nucleus nucleus;
    private SubjectRegistry subjectRegistry;
    private OuterCell outerCell;
    List<Binding<Ras, GTP>> RasGTPList;

    public SignalingWorld(OuterCell outerCell, InnerCell innerCell, Nucleus nucleus,
                          SubjectRegistry subjectRegistry){
        this.innerCell = innerCell;
        this.subjectRegistry = subjectRegistry;
        smadList = innerCell.getSmadPool();
        tgfbReceptorList = innerCell.getTgfbReceptorsPool();
        tgfbs = outerCell.getTgfbPool();
        cytokineList = outerCell.getCytokinePool();
        jak2s = innerCell.getJAK2Pool();
        stats = innerCell.getStatPool();
        egfList = outerCell.getEgfPool();
        rasList = innerCell.getRasPool();
        gdpList = innerCell.getGdpPool();
        maPkinases = innerCell.getMaPkinases();
        mekList = innerCell.getMekPool();
        rafList = innerCell.getRafPool();
        RasGTPList = innerCell.getRasGTPPool();
        this.outerCell = outerCell;
        this.nucleus = nucleus;

    }
    public void RunAll(){
        List<Runnable> pathways = new ArrayList<>();

        pathways.add(() -> {
            try {
                RunTGFBPathway();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        pathways.add(() -> {
            try {
                RunJAKSTATPathway();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        pathways.add(() -> {
            try {
                RunRasMAPPathway();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
        pathways.add(() -> {
            try {
                RunMAPKPathway();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        pathways.add(() -> {
            Regulation();
        });

        int i = 0;
        ExecutorService executor = Executors.newFixedThreadPool(pathways.size());

        while(nucleus.getSize() < 2) {

            for (Runnable pathway : pathways) {
                executor.submit(pathway);
            }
            i++;
        }

        executor.shutdown();

    }



    public void RunTGFBPathway() throws InterruptedException {
        System.out.println("TGFB Pathway is running..");
        subjectRegistry.getSubject(EventType.RunPathway).setState(EventType.RunPathway, "TGFB pathway is running ... ");

        TGFBPathway strategy = new TGFBPathway(tgfbReceptorList, smadList, tgfbs);
        strategy.phaseOne();
        strategy.phaseTwo();
        strategy.phaseThree(nucleus);
    }


   public void RunJAKSTATPathway() throws InterruptedException {
       System.out.println("JAK-STAT Pathway is running..");
       subjectRegistry.getSubject(EventType.RunPathway).setState(EventType.RunPathway, "JAK-STAT pathway is running ... ");

       JAKSTATPathway jakstatStrategy = new JAKSTATPathway(innerCell, outerCell);
       jakstatStrategy.phaseOne();
       jakstatStrategy.phaseTwo();
       jakstatStrategy.phaseThree(innerCell.getSTATPool());
       jakstatStrategy.phaseFour(nucleus);
   }


    public void RunRasMAPPathway() throws InterruptedException {

        System.out.println("Ras_MAP Pathway is running..");
        subjectRegistry.getSubject(EventType.RunPathway).setState(EventType.RunPathway, "Ras-MAP pathway is running ... ");

        RasMAPPathway rasMAPStrategy = new RasMAPPathway(egfList, rasList, gdpList, innerCell);
        rasMAPStrategy.phaseOne();
        rasMAPStrategy.phaseTwo();
        rasMAPStrategy.phaseThree();
    }



    //Run only when RasGTP Pool is not empty
    public void RunMAPKPathway() throws InterruptedException {

        System.out.println("MAPK Pathway is running..");
        subjectRegistry.getSubject(EventType.RunPathway).setState(EventType.RunPathway, "MAPK pathway is running ... ");

        MAPKPathway mapkStrategy = new MAPKPathway(rafList, maPkinases, mekList);
        List<Binding<Ras, GTP>> RasGTPPool = innerCell.getRasGTPPool();
        mapkStrategy.phaseOne(RasGTPPool.get(rand.nextInt(RasGTPPool.size())));
        mapkStrategy.phaseTwo();
        mapkStrategy.phaseThree(this.nucleus);

    }

    public void Regulation() {
        System.out.println("Regulation is running..");
        RegulatePathway regulatePathway = new RegulatePathway(innerCell);
        regulatePathway.regulateOne(innerCell.getTyrosineResiduePool());
        regulatePathway.regulateTwo(innerCell.getSkiPool());
        regulatePathway.regulateThree(innerCell.getTyrosineResiduePool(), innerCell.getSOCSPool());
        regulatePathway.regulateFour(innerCell.getRasGDPPool(), innerCell.getSOSPool());
    }


    public InnerCell getInnerCell(){
        innerCell = InnerCell.getNewBuilder()
                .createAndAddTyrosineResidue()
                .createAndAddReceptors()
                .createAndAddRas()
                .createAndAddKinases()
                .createAndAddGAP()
                .createAndAddRegulatoryProteins()
                .createAndAddTranscriptionFactors()
                .createAndAddGTP()
                .createAndAddGDP()
                .build();
        return innerCell;
    }
   public OuterCell getOuterCell(){
       return new OuterCell.Builder(getInnerCell())
               .createAndAddLigands()
               .build();
   }
   @Bean
   public SignalingWorld signalingWorld(OuterCell outerCell, InnerCell innerCell,SubjectRegistry subjectRegistry){
        return new SignalingWorld(outerCell, innerCell, new Nucleus(subjectRegistry), subjectRegistry);
   }

}
