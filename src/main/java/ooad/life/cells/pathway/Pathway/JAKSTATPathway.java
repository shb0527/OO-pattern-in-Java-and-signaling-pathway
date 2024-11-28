package ooad.life.cells.pathway.Pathway;

import ooad.life.cells.pathway.*;
import ooad.life.cells.pathway.Kinases.JAK2;
import ooad.life.cells.pathway.Ligand.Cytokine;
import ooad.life.cells.pathway.TranscriptionFactors.STAT;
import ooad.life.cells.pathway.TranscriptionFactors.Smad;
import ooad.life.cells.pathway.receptors.EpoR;

import java.util.List;
import java.util.Random;

public class JAKSTATPathway extends Pathway {

    private Cytokine cytokine;
    private EpoR epoR;
    private Random rand = new Random();
    Binding<Cytokine, EpoR> binding;
    private JAK2 jak2;
    private STAT stat;
    private List<Cytokine> cytokineList;
    private List<JAK2> jak2s;
    private List<STAT> stats;
    private Nucleus nucleus;
    private InnerCell innerCell;
    private List<TyrosineResidue> tyrosineResidueList;
    public JAKSTATPathway(InnerCell innerCell, OuterCell outerCell){
        this.cytokineList = outerCell.getCytokinePool();
        this.jak2s = innerCell.getJAK2Pool();
        this.stats = innerCell.getStatPool();
        this.nucleus = innerCell.getNucleus();
        this.tyrosineResidueList = innerCell.getTyrosineResiduePool();
        this.innerCell = innerCell;

    }

    public void phaseOne(){

        List<Cytokine> tempC = cytokineList.stream()
                .filter(c -> !c.getBind())
                .toList();
        if(tempC.size() > 0){
            cytokine = tempC.get(rand.nextInt(tempC.size()));
            cytokine.bindingToReceptor();
            binding = cytokine.getBinding();
            cytokine.setBind(true);
            epoR = binding.getValue();
            epoR.dimerization();
            epoR.phosphorylation();
        }

    }

    public void phaseTwo(){

        List<JAK2> tempJak2s = jak2s.stream()
                .filter(jak2 -> !jak2.getActivation())
                .toList();
        if(tempJak2s.size() > 0){
            jak2 = tempJak2s.get(rand.nextInt(tempJak2s.size()));
            jak2.bindingToReceptor();
            jak2.setActivation(true);
            jak2.phosphorylation(tyrosineResidueList);
        }

        if(binding != null){
            binding.getValue().setBind(false);
            binding.unbind();
        }
        if(jak2 != null) {
            jak2.getBinding().getValue().setBind(false);
            jak2.getBinding().unbind();
        }

    }

    public void phaseThree(List<STAT> stats){
        if(jak2 != null) {


            if (jak2.getActivation()) {

                if (stats.size() > 0) {
                    if (stat != null) {
                        stat = stats.get(rand.nextInt(stats.size()));

                        stat.bindingtophosphoTyrosine(jak2.getTyrosineResidue());
                        stat.getBinding().getValue().setBind(true);
                        stat.phosphorylation(jak2);
                    }
                }
            }
        }
    }


    public void phaseFour(Nucleus nucleus){
        if(stat != null && stat.getActivation()) {
            stat.dimerization();
            stat.unmaskNLS();
            stat.enterNucleus(nucleus);
            if(epoR != null){
                epoR.dephosphorylation();
            }
            if(stat.getBinding()  != null){
                stat.getBinding().getValue().setBind(false);
                stat.getBinding().unbind();
            }
            nucleus.getMoleculesInNucleus();
        }
    }
}
