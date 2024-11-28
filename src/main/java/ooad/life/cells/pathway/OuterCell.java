package ooad.life.cells.pathway;
import ooad.life.cells.pathway.Kinases.KinaseFactory;
import ooad.life.cells.pathway.Ligand.*;
import ooad.life.cells.pathway.MoleculeType;
import ooad.life.cells.pathway.RegulatoryProtein.RegulatoryProteinFactory;
import ooad.life.cells.pathway.TranscriptionFactors.TFFactory;
import ooad.life.cells.pathway.receptors.EpoR;
import ooad.life.cells.pathway.receptors.RTK;
import ooad.life.cells.pathway.receptors.ReceptorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

public class OuterCell {

    private MoleculeType MoleculrType;
    private List<Ligand> ligandPool = new ArrayList<>();
    private List<TGFB> tgfbPool = new ArrayList<>();
    private List<Cytokine> cytokines = new ArrayList<>();
    private List<EGF> egfPool = new ArrayList<>();
    private SubjectRegistry subjectRegistry;

    //cytokine binds cytokine receptor
    //all ligands gathered
    private InnerCell innerCell;


    public OuterCell(InnerCell innerCell, SubjectRegistry subjectRegistry){
        this.innerCell = innerCell;
        this.subjectRegistry = subjectRegistry;
    }

    public static Builder getNewBuilder() {
        return new Builder();
    }

    public String toString() {
        return String.join(", ", ligandPool.stream().map(Object::toString).toList());
    }

    public List<EpoR> getEpoRPool(){
        return this.innerCell.getEpoRPool();
    }

    public List<RTK> getRTKPool(){
        return this.innerCell.getRtksPool();
    }

    public static class Builder {

        private OuterCell outerCell;
        public Builder(InnerCell innerCell) {
            this.outerCell = new OuterCell(innerCell, new SubjectRegistry(new SubjectFactory()));
        }

        LigandFactory ligandFactory = new LigandFactory();

        Builder() {
            this(new LigandFactory());
        }


        public Builder(LigandFactory ligandFactory) {
            this.ligandFactory = ligandFactory;
        }

        public Builder createAndAddLigands(){
            outerCell.cytokines= ligandFactory.createNumberOfCytokine(MoleculeType.Cytokine.getRatio(), outerCell.getEpoRPool(), outerCell.subjectRegistry);
            outerCell.egfPool = ligandFactory.createNumberOfEGF(MoleculeType.EGF.getRatio(), outerCell.getRTKPool(), outerCell.subjectRegistry);
            outerCell.tgfbPool = ligandFactory.createNumberOfTGFB(MoleculeType.TGFB.getRatio(), outerCell.subjectRegistry);

            for(Cytokine cytokine: outerCell.cytokines){
                outerCell.ligandPool.add(cytokine);
            }
            for(EGF egf: outerCell.egfPool){
                outerCell.ligandPool.add(egf);
            }
            for(TGFB tgfb: outerCell.tgfbPool){
                outerCell.ligandPool.add(tgfb);
            }
            return this;
        }

        public OuterCell build() {
            return outerCell;
        }
    }

    public List<TGFB> getTgfbPool(){
        return tgfbPool;
    }
    public List<Cytokine> getCytokinePool(){
        return cytokines;
    }
    public List<EGF> getEgfPool(){
        return egfPool;
    }
}
