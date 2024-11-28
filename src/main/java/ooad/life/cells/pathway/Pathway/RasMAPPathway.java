package ooad.life.cells.pathway.Pathway;

import ooad.life.cells.pathway.Binding;
import ooad.life.cells.pathway.GN.GDP;
import ooad.life.cells.pathway.GN.GTP;
import ooad.life.cells.pathway.InnerCell;
import ooad.life.cells.pathway.Ligand.EGF;
import ooad.life.cells.pathway.Ras;
import ooad.life.cells.pathway.receptors.RTK;

import java.util.List;
import java.util.Random;

public class RasMAPPathway extends Pathway {
    private Random rand = new Random();
    private Ras ras;
    public EGF egf;
    private RTK rtk;
    private List<EGF> egfList;
    private List<Ras> rasList;
    private List<GDP> gdps;
    Binding<Ras, GTP> RasGTP;
    Binding<Ras, GDP> RasGDP;
    private InnerCell innerCell;
    public RasMAPPathway(List<EGF> egfList, List<Ras> rasList,
                         List<GDP> gdps, InnerCell innerCell){
        this.egfList = egfList;
        this.rasList = rasList;
        this.gdps=gdps;
        this.innerCell = innerCell;

    }

    public void phaseOne(){
        List<EGF> tempEGF = egfList.stream()
                .filter(egf -> !egf.getBind())
                .toList();
        egf = tempEGF.get(rand.nextInt(tempEGF.size()));
        egf.bindingToReceptor();
        rtk = egf.getBinding().getValue();
        rtk.setActivation(true);
        egf.setBind(true);
    }

    public void phaseTwo(){
        //activation
        ras = rasList.get(rand.nextInt(rasList.size()));
        GDP gdp = gdps.get(rand.nextInt(gdps.size()));
        ras.bindToGTP(new Binding<>(ras,gdp));
        RasGTP = ras.getRasGTP();
        innerCell.addRasGTP(RasGTP);
        RasGTP = ras.getRasGTP();
    }

    public void phaseThree(){
        //deactivation
        ras.bindToGDP(RasGTP);
        RasGDP = ras.getRasGDP();
        innerCell.addRasGDP(RasGDP);
        innerCell.removeRasGTP(RasGTP);
    }
}
