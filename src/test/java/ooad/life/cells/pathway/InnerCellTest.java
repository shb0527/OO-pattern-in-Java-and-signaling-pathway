package ooad.life.cells.pathway;

import org.junit.jupiter.api.Test;

public class InnerCellTest {

    @Test
    void Test(){
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


        String str = innerCell.toString();
        System.out.println(str);
        System.out.println(outerCell.toString());

    }
}
