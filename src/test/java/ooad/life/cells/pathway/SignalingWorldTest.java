package ooad.life.cells.pathway;

import ooad.life.cells.pathway.TranscriptionFactors.Smad;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SignalingWorldTest {
    private SubjectRegistry subjectRegistry = new SubjectRegistry(new SubjectFactory());
    @Test
    void TGFBPathway() throws InterruptedException {
        InnerCell innerCell = InnerCell.getNewBuilder()
                .createAndAddTyrosineResidue()
                .createAndAddReceptors()
                .createAndAddRegulatoryProteins()
                .createAndAddTranscriptionFactors()
                .createAndAddKinases()
                .createAndAddRas()
                .createAndAddGAP()
                .build();

        OuterCell outerCell = new OuterCell.Builder(innerCell)
                .createAndAddLigands()
                .build();

        Nucleus nucleus = new Nucleus(subjectRegistry);
        SignalingWorld signalingWorld = new SignalingWorld(outerCell, innerCell,nucleus, subjectRegistry );
        signalingWorld.RunTGFBPathway();
        signalingWorld.Regulation();
    }

    @Test
    void JAKSTATPathway() throws InterruptedException {
        InnerCell innerCell = InnerCell.getNewBuilder()
                .createAndAddTyrosineResidue()
                .createAndAddReceptors()
                .createAndAddRegulatoryProteins()
                .createAndAddTranscriptionFactors()
                .createAndAddKinases()
                .createAndAddRas()
                .createAndAddGAP()
                .build();

        OuterCell outerCell = new OuterCell.Builder(innerCell)
                .createAndAddLigands()
                .build();

        Nucleus nucleus = new Nucleus(subjectRegistry);

        SignalingWorld signalingWorld = new SignalingWorld(outerCell, innerCell, nucleus, subjectRegistry);
        System.out.println(innerCell.getSmadPool());
        int i = 0;
             signalingWorld.RunJAKSTATPathway();

            signalingWorld.RunJAKSTATPathway();
            signalingWorld.Regulation();


    }


    @Test
    void Test3(){
        InnerCell innerCell = InnerCell.getNewBuilder()
                .createAndAddTyrosineResidue()
                .createAndAddReceptors()
                .createAndAddRegulatoryProteins()
                .createAndAddTranscriptionFactors()
                .createAndAddKinases()
                .createAndAddGAP()
                .createAndAddRas()
                .createAndAddGTP()
                .createAndAddGDP()
                .build();

        OuterCell outerCell = new OuterCell.Builder(innerCell)
                .createAndAddLigands()
                .build();

        Nucleus nucleus = new Nucleus(subjectRegistry);
        SignalingWorld signalingWorld = new SignalingWorld(outerCell, innerCell, nucleus,subjectRegistry);
        signalingWorld.RunAll();


    }

    @Test
    void TestMain() throws InterruptedException {
        InnerCell innerCell = InnerCell.getNewBuilder()
                .createAndAddTyrosineResidue()
                .createAndAddReceptors()
                .createAndAddRegulatoryProteins()
                .createAndAddTranscriptionFactors()
                .createAndAddKinases()
                .createAndAddGAP()
                .createAndAddRas()
                .createAndAddGTP()
                .createAndAddGDP()
                .build();

        OuterCell outerCell = new OuterCell.Builder(innerCell)
                .createAndAddLigands()
                .build();

        Nucleus nucleus = new Nucleus(subjectRegistry);
        SignalingWorld signalingWorld = new SignalingWorld(outerCell, innerCell, nucleus,subjectRegistry);
        signalingWorld.RunRasMAPPathway();
        signalingWorld.RunRasMAPPathway();
        signalingWorld.RunRasMAPPathway();
        signalingWorld.RunRasMAPPathway();
        signalingWorld.RunRasMAPPathway();
        signalingWorld.RunRasMAPPathway();
        signalingWorld.RunRasMAPPathway();
        signalingWorld.RunRasMAPPathway();
        signalingWorld.RunRasMAPPathway();
    }


}
