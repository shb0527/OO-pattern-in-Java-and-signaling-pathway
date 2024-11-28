package ooad.life.cells.pathway;

import ooad.life.cells.pathway.GN.GDP;
import ooad.life.cells.pathway.GN.GNFactory;
import ooad.life.cells.pathway.GN.GTP;
import ooad.life.cells.pathway.Kinases.*;
import ooad.life.cells.pathway.RegulatoryProtein.*;
import ooad.life.cells.pathway.TranscriptionFactors.STAT;
import ooad.life.cells.pathway.TranscriptionFactors.Smad;
import ooad.life.cells.pathway.TranscriptionFactors.TFFactory;
import ooad.life.cells.pathway.TranscriptionFactors.TranscriptionFactor;
import ooad.life.cells.pathway.receptors.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class InnerCell {


    private List<Receptor> receptorsPool = new ArrayList<>();
    private List<RegulatoryProtein> regulatoryProteinsPool = new ArrayList<>();
    private List<TranscriptionFactor> tfPool = new ArrayList<>();
    private List<Kinase> kinasePool = new ArrayList<>();
    private List<Ras> rasPool = new ArrayList<>();
    private List<GAP> gapPool = new ArrayList<>();
    private List<Smad> smadPool = new ArrayList<>();
    private List<TGFBReceptor> tgfbReceptorsPool = new ArrayList<>();
    private List<EpoR> epoRPool = new ArrayList<>();
    private List<TyrosineResidue> tyrosineResiduePool = new ArrayList<>();
    private List<JAK2> jak2pool = new ArrayList<>();
    private List<STAT> statPool = new ArrayList<>();
    private List<RTK> rtksPool = new ArrayList<>();
    private List<GDP> gdpPool = new ArrayList<>();
    private List<GTP> gtpPool = new ArrayList<>();
    private List<SOS> SOSPool = new ArrayList<>();
    private List<SHP1> SHP1Pool = new ArrayList<>();
    private List<MAPkinase> maPkinases = new ArrayList<>();
    private List<Ski> SkiPool = new ArrayList<>();
    private List<SOCS> SOCSPool = new ArrayList<>();
    private List<MEK> mekPool = new ArrayList<>();
    private List<Raf> rafPool = new ArrayList<>();

    private List<Binding<Ras, GDP>> RasGDPPool = new ArrayList<>();
    private List<Binding<Ras, GTP>> RasGTPPool = new ArrayList<>();
    private GDP gdp;
    private GTP gtp;
    private SubjectRegistry subjectRegistry;
    private Nucleus nucleus;
    public InnerCell(SubjectRegistry subjectRegistry){
        this.subjectRegistry = subjectRegistry;
        this.nucleus = new Nucleus(subjectRegistry);
    }

    public InnerCell(){
        this.subjectRegistry = null;
    }
    //600 to 6,000 GDP molecules for every Ras molecule
    public static Builder getNewBuilder() {
        return new Builder();
    }


    public String toString() {

        return Stream.of(receptorsPool, regulatoryProteinsPool, tfPool, kinasePool, rasPool, gapPool, smadPool, tgfbReceptorsPool,
                        epoRPool, tyrosineResiduePool, jak2pool, statPool, rtksPool, gapPool, gdpPool, gtpPool, SOSPool) // Combine all lists
                .map(list -> list.stream()
                        .map(Object::toString) // Convert each element to a String
                        .toList()) // Collect as a list of Strings
                .map(subList -> String.join(",", subList)) // Join elements within each list
                .collect(java.util.stream.Collectors.joining("\n")); // Join lists with new lines
    }

    public static class Builder {

        private InnerCell innerCell = new InnerCell(new SubjectRegistry(new SubjectFactory()));

        ReceptorFactory receptorFactory = new ReceptorFactory();
        RegulatoryProteinFactory regulatoryProteinFactory = new RegulatoryProteinFactory();
        TFFactory tfFactory = new TFFactory();
        KinaseFactory kinaseFactory = new KinaseFactory();
        GNFactory gnFactory = new GNFactory(innerCell);

        Builder() {
            this(new ReceptorFactory(), new RegulatoryProteinFactory(), new TFFactory(), new KinaseFactory());
        }


        public Builder(ReceptorFactory receptorFactory, RegulatoryProteinFactory regulatoryProteinFactory,
                       TFFactory tfFactory, KinaseFactory kinaseFactory) {
            this.receptorFactory = receptorFactory;
            this.regulatoryProteinFactory = regulatoryProteinFactory;
            this.tfFactory = tfFactory;
            this.kinaseFactory = kinaseFactory;
        }

        public Builder createAndAddGDP() {
            innerCell.gdpPool = gnFactory.createNumberOfGDP(MoleculeType.GTP.getRatio(), innerCell.gtpPool, innerCell.subjectRegistry);
            return this;
        }

        public Builder createAndAddGTP() {
            innerCell.gtpPool = gnFactory.createNumberOfGTP(MoleculeType.GTP.getRatio(),innerCell.subjectRegistry);
            return this;
        }

        public Builder createAndAddReceptors() {

            innerCell.epoRPool = receptorFactory.createNumberOfEpoR(MoleculeType.EPOR.getRatio(), innerCell.tyrosineResiduePool, innerCell.subjectRegistry );
            innerCell.tgfbReceptorsPool = receptorFactory.createNumberOfTGFBReceptor(MoleculeType.TGFBR.getRatio(), innerCell.smadPool, innerCell.subjectRegistry);
            innerCell.rtksPool = receptorFactory.createNumberOfRTK(MoleculeType.RTK.getRatio(),innerCell.tyrosineResiduePool, innerCell.subjectRegistry);
            for (EpoR epoR : innerCell.epoRPool) {
                innerCell.receptorsPool.add(epoR);
            }
            for (TGFBReceptor tgfbReceptor : innerCell.tgfbReceptorsPool) {
                innerCell.receptorsPool.add(tgfbReceptor);
            }
            for (RTK rtk : innerCell.rtksPool) {
                innerCell.receptorsPool.add(rtk);
            }
            return this;
        }

        public Builder createAndAddRegulatoryProteins() {
            innerCell.SHP1Pool = regulatoryProteinFactory.createNumberOfSHP1(MoleculeType.SHP1.getRatio(), innerCell.subjectRegistry);
            innerCell.SOCSPool = regulatoryProteinFactory.createNumberOfSOCS(MoleculeType.SOCS.getRatio(), innerCell.subjectRegistry);
            innerCell.SkiPool = regulatoryProteinFactory.createNumberOfSki(MoleculeType.Ski.getRatio(), innerCell, innerCell.subjectRegistry);
            innerCell.SOSPool = regulatoryProteinFactory.createNumberOfSOS(MoleculeType.SOS.getRatio(), innerCell.subjectRegistry);
            for (SHP1 shp1 : innerCell.SHP1Pool) {
                innerCell.regulatoryProteinsPool.add(shp1);
            }
            for (SOCS socs : innerCell.SOCSPool) {
                innerCell.regulatoryProteinsPool.add(socs);
            }
            for (Ski ski : innerCell.SkiPool) {
                innerCell.regulatoryProteinsPool.add(ski);
            }
            for (SOS sos : innerCell.SOSPool) {
                innerCell.regulatoryProteinsPool.add(sos);
            }
            return this;
        }

        public Builder createAndAddTranscriptionFactors() {
            innerCell.smadPool = tfFactory.createNumberOfSmad(MoleculeType.Smad.getRatio(), innerCell, innerCell.subjectRegistry);
            innerCell.statPool = tfFactory.createNumberOfSTAT(MoleculeType.STAT.getRatio(), innerCell,  innerCell.subjectRegistry);
            for (Smad smad : innerCell.smadPool) {
                innerCell.tfPool.add(smad);
            }
            for (STAT stat : innerCell.statPool) {
                innerCell.tfPool.add(stat);
            }

            return this;
        }

        public Builder createAndAddKinases() {
            innerCell.jak2pool = kinaseFactory.createNumberOfJAK2(MoleculeType.JAK2.getRatio(), innerCell.epoRPool, innerCell.tyrosineResiduePool,
                    innerCell.subjectRegistry);
            innerCell.maPkinases = kinaseFactory.createNumberOfMAPKinase(MoleculeType.MAPKinase.getRatio(), innerCell, innerCell.subjectRegistry);
            innerCell.mekPool = kinaseFactory.createNumberOfMEK(MoleculeType.MEK.getRatio(), innerCell.subjectRegistry);
            innerCell.rafPool = kinaseFactory.createNumberOfRaf(MoleculeType.Raf.getRatio(), innerCell.getMekPool(), innerCell.subjectRegistry);
            for (JAK2 jak2 : innerCell.jak2pool) {
                innerCell.kinasePool.add(jak2);
            }
            for (MAPkinase maPkinase : innerCell.maPkinases) {
                innerCell.kinasePool.add(maPkinase);
            }
            for (MEK mek : innerCell.mekPool) {
                innerCell.kinasePool.add(mek);
            }
            for (Raf raf : innerCell.rafPool) {
                innerCell.kinasePool.add(raf);
            }
            return this;
        }

        public Builder createAndAddTyrosineResidue() {
            innerCell.tyrosineResiduePool = innerCell.createNumberOfTyrosineResidue(MoleculeType.TyrosineResidue.getRatio());
            return this;
        }

        public Builder createAndAddRas() {
            innerCell.rasPool = innerCell.createNumberOfRas(MoleculeType.Ras.getRatio(), innerCell.SOSPool, innerCell.gapPool);
            return this;
        }

        public Builder createAndAddGAP() {
            innerCell.gapPool = innerCell.createNumberOfGAP(MoleculeType.GAP.getRatio());
            return this;
        }


        public InnerCell build() {
            return innerCell;
        }
    }

    public List<TyrosineResidue> createNumberOfTyrosineResidue(Integer numTR) {
        return IntStream.range(0, numTR)
                .mapToObj(i -> {
                    TyrosineResidue tr = new TyrosineResidue(subjectRegistry);
                    return tr;
                })
                .map(TyrosineResidue.class::cast)
                .toList();
    }

    public List<Ras> createNumberOfRas(Integer numRas, List<SOS> sosList, List<GAP> gapsList) {
        return IntStream.range(0, numRas)
                .mapToObj(i -> {
                    Ras ras = new Ras(sosList, gapsList, subjectRegistry);
                    return ras;
                })
                .map(Ras.class::cast)
                .toList();
    }

    public List<GAP> createNumberOfGAP(Integer numGAP) {
        return IntStream.range(0, numGAP)
                .mapToObj(i -> {
                    GAP gap = new GAP();
                    return gap;
                })
                .map(GAP.class::cast)
                .toList();
    }


    public List<Smad> getSmadPool(){
        return smadPool;
    }
    public List<TGFBReceptor> getTgfbReceptorsPool(){
        return tgfbReceptorsPool;
    }
    public List<EpoR> getEpoRPool(){
        return epoRPool;
    }
    public List<TyrosineResidue> getTyrosineResiduePool() {return tyrosineResiduePool;}
    public List<JAK2> getJAK2Pool(){ return jak2pool;}
    public List<STAT> getStatPool(){ return statPool;}
    public List<RTK> getRtksPool(){return rtksPool;}
    public List<GDP> getGdpPool(){return gdpPool;}
    public List<GTP> getGtpPool(){return gtpPool;}
    public List<Ras> getRasPool(){return rasPool;}
    public List<MAPkinase> getMaPkinases(){ return maPkinases;}
    public List<MEK> getMekPool() { return mekPool;}
    public List<Raf> getRafPool(){ return rafPool;}
    public List<Binding<Ras, GTP>> getRasGTPPool() { return RasGTPPool;}
    public List<Binding<Ras, GDP>> getRasGDPPool() { return RasGDPPool;}
    public List<TranscriptionFactor> getTfPool(){ return tfPool;}
    public List<SHP1> getSHP1Pool() {return SHP1Pool;}
    public List<STAT> getSTATPool() {return statPool;}
    public List<Ski> getSkiPool() { return SkiPool;}
    public List<SOCS> getSOCSPool(){ return SOCSPool;}
    public List<SOS> getSOSPool() {return SOSPool; }
    public void addGDP(GDP gdp){
        gdpPool = new ArrayList<>(gdpPool);
        gdpPool.add(gdp);
    }
    public void addRasGTP(Binding <Ras, GTP> RasGTP){
        RasGTPPool = new ArrayList<>(RasGTPPool);
        RasGTPPool.add(RasGTP);
    }

    public void addRasGDP(Binding <Ras, GDP> RasGDP){
        RasGDPPool = new ArrayList<>(RasGDPPool);
        RasGDPPool.add(RasGDP);
    }
    public void removeRasGTP(Binding<Ras, GTP> RasGTP){
        RasGTPPool = new ArrayList<>(RasGTPPool);
        RasGTPPool.remove(RasGTP);
    }
    public void removeSmad(Smad smad){
        smadPool = new ArrayList<>(smadPool);
        smadPool.remove(smad);
    }
    public void removeSTAT(STAT stat){
        statPool = new ArrayList<>(statPool);
        statPool.remove(stat);
    }

    public void removeMapKinase(MAPkinase maPkinase){
        maPkinases = new ArrayList<>(maPkinases);
        maPkinases.remove(maPkinase);
    }

    public Nucleus getNucleus(){
        return this.nucleus;
    }
}
