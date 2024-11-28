package ooad.life.cells.pathway.Pathway;

import ooad.life.cells.pathway.Binding;
import ooad.life.cells.pathway.Kinases.MAPkinase;
import ooad.life.cells.pathway.Kinases.MEK;
import ooad.life.cells.pathway.Kinases.Raf;
import ooad.life.cells.pathway.GN.GTP;
import ooad.life.cells.pathway.Nucleus;
import ooad.life.cells.pathway.Ras;

import java.util.List;
import java.util.Random;

public class MAPKPathway extends Pathway {
    private List<Raf> rafList;
    private MAPkinase maPkinase;
    private List<MAPkinase> maPkinases;
    private List<MEK> mekList;
    private MEK mek;
    private Random rand = new Random();
    private Raf raf;

    Binding<Raf, Binding<Ras, GTP>> RafRasGTP;
    public MAPKPathway(List<Raf> rafList, List<MAPkinase> maPkinases,
                       List<MEK> mekList){
        this.rafList = rafList;
        this.maPkinases = maPkinases;
        this.mekList = mekList;
    }
    public void phaseOne(Binding<Ras, GTP> rasGTP){
        //activated  by Sos to Ras and bind to GTP
        if(rasGTP.getKey().getStatus()){
            System.out.println(rasGTP.getKey().getStatus());
            raf = rafList.get(rand.nextInt(rafList.size()));
            raf.bindingToRasGTP(rasGTP);
            RafRasGTP = raf.getRafRasGTP();
            maPkinase = maPkinases.get(rand.nextInt(maPkinases.size()));
            maPkinase.setActivation(true);
        }
    }
    //hydrolysis of Ras-GTP to RAS-GDP release active Raf, which phosphorylates and activates MEK
    public void phaseTwo(){
        Raf raf = RafRasGTP.getKey();
        Binding<Ras,GTP> RasGTP = RafRasGTP.getValue();
        RafRasGTP.unbind();
        raf.setActivation(false);
        RasGTP.getValue().hydrolyze();
        mek = mekList.get(rand.nextInt(mekList.size()));
        mek.setActivation(true);
        mek.phosphorylation(maPkinase);//activates MAPKinase
    }

    public void phaseThree(Nucleus nucleus){

        maPkinase.phosphorylation();
        maPkinase.enterNucleus(nucleus);
        nucleus.getMoleculesInNucleus();

    }

    //RTK Pathway influences
}
