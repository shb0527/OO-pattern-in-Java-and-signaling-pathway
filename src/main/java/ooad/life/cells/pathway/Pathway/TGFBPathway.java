package ooad.life.cells.pathway.Pathway;

import ooad.life.cells.pathway.Binding;
import ooad.life.cells.pathway.Ligand.TGFB;
import ooad.life.cells.pathway.Nucleus;
import ooad.life.cells.pathway.TranscriptionFactors.Smad;
import ooad.life.cells.pathway.receptors.TGFBReceptor;

import java.util.List;
import java.util.Random;

public class TGFBPathway extends Pathway {

    private Random rand = new Random();
    //In this pathway, kinase and receptor are not separated
    private TGFB tgfb;
    private Binding<TGFB, TGFBReceptor> TGFBReceptorbinding;
    private Smad smad3;
    private Smad smadComplex;
    private List<TGFBReceptor> tgfbReceptors;
    private List<Smad> smads;
    private List<TGFB> tgfbs;
    private  TGFBReceptor tgfbReceptor;

    public TGFBPathway(List<TGFBReceptor> tgfbReceptorList, List<Smad> smadList,
                       List<TGFB> tgfbs){
        this.tgfbReceptors = tgfbReceptorList;
        this.smads = smadList;
        this.tgfbs = tgfbs;
    }

    public void phaseOne(){
        List<TGFB> tempTGFBs = tgfbs.stream()
                .filter(tgfb -> !tgfb.getBind())
                .toList();
        tgfb = tempTGFBs.get(rand.nextInt(tempTGFBs.size()));
        tgfb.cleavage();
        tgfb.bindingToReceptor(tgfbReceptors);
        TGFBReceptorbinding = tgfb.getBinding();

    }

    public void phaseTwo(){
        //Activated RI phosphorylates Smad
        tgfbReceptor = TGFBReceptorbinding.getValue();
        TGFBReceptorbinding.getValue().phosphorylation(smads);
        smad3 = TGFBReceptorbinding.getValue().getphosphorylatedSmad();
        smad3.setActivation(true);
        smad3.unmaskNLS();
        TGFBReceptorbinding.getValue().setBind(false);
        TGFBReceptorbinding.unbind();
    }

    public void phaseThree(Nucleus nucleus){
        Smad smad4 = smad3.getRandomSmad(smads);
        smadComplex = smad3.formingSmadComplex(smad3, smad4);
        smadComplex.enterNucleus(nucleus);

        nucleus.getMoleculesInNucleus();
        tgfbReceptor.dephosphorylation();
    }

}
