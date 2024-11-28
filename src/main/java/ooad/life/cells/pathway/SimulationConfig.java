package ooad.life.cells.pathway;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimulationConfig {

    @Bean
    @Qualifier("primarySubjectRegistry")
    public SubjectRegistry subjectRegistry() {
        return new SubjectRegistry(new SubjectFactory());
    }
    @Bean
    public OuterCell outerCell(InnerCell innerCell) {
        return new OuterCell.Builder(innerCell).createAndAddLigands().build();
    }

    @Bean
    public InnerCell innerCell(SubjectRegistry subjectRegistry) {
        return InnerCell.getNewBuilder()
                .createAndAddTyrosineResidue()
                .createAndAddReceptors()
                .createAndAddRegulatoryProteins()
                .createAndAddTranscriptionFactors()
                .createAndAddKinases()
                .createAndAddRas()
                .createAndAddGAP()
                .build();
    }

    @Bean
    public Nucleus nucleus(SubjectRegistry subjectRegistry) {
        return new Nucleus(subjectRegistry);
    }
    @Bean
    public SignalingWorld signalingWorld(OuterCell outerCell, InnerCell innerCell, Nucleus nucleus, SubjectRegistry subjectRegistry) {
            return new SignalingWorld(outerCell, innerCell, nucleus, subjectRegistry);
        }

}
