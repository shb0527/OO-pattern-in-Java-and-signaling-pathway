package ooad.life.cells.pathway;

import org.springframework.stereotype.Service;

@Service
public class SimulationService {
    public SignalingWorld createSignalingWorld() {
        SubjectRegistry subjectRegistry = new SubjectRegistry(new SubjectFactory());
        InnerCell innerCell = InnerCell.getNewBuilder()
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

        OuterCell outerCell = new OuterCell.Builder(innerCell)
                .createAndAddLigands()
                .build();

        Nucleus nucleus = new Nucleus(subjectRegistry);
        SignalingWorld signalingWorld = new SignalingWorld(outerCell, innerCell, nucleus, subjectRegistry);
        return signalingWorld;
    }
}
