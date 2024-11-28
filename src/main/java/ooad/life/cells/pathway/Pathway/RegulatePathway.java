package ooad.life.cells.pathway.Pathway;

import ooad.life.cells.pathway.Binding;
import ooad.life.cells.pathway.GN.GDP;
import ooad.life.cells.pathway.InnerCell;
import ooad.life.cells.pathway.Kinases.JAK2;
import ooad.life.cells.pathway.Ras;
import ooad.life.cells.pathway.RegulatoryProtein.SHP1;
import ooad.life.cells.pathway.RegulatoryProtein.SOCS;
import ooad.life.cells.pathway.RegulatoryProtein.SOS;
import ooad.life.cells.pathway.RegulatoryProtein.Ski;
import ooad.life.cells.pathway.TranscriptionFactors.STAT;
import ooad.life.cells.pathway.TranscriptionFactors.Smad;
import ooad.life.cells.pathway.TyrosineResidue;
import ooad.life.cells.pathway.receptors.EpoR;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RegulatePathway {
    private List<TyrosineResidue> trList;
    private Random rand  = new Random();
    private List<SHP1> shp1List;
    private List<JAK2> jak2sList;
    private List<Smad> smadList;
    private List<Ski> skiList;
    private List<SOCS> socsList;
    private List<STAT> statList;
    private List<EpoR> epoRList;
    private List<Binding<Ras, GDP>> RasGDPList;
    private List<SOS> sosList;


    public RegulatePathway(InnerCell innerCell){
        trList = innerCell.getTyrosineResiduePool();
        shp1List = innerCell.getSHP1Pool();
        jak2sList = innerCell.getJAK2Pool();
        smadList = innerCell.getSmadPool();
        skiList = innerCell.getSkiPool();
        socsList = innerCell.getSOCSPool();
        statList = innerCell.getStatPool();
        epoRList = innerCell.getEpoRPool();
        RasGDPList = innerCell.getRasGDPPool();
        sosList = innerCell.getSOSPool();
    }



    public void regulateOne(List<TyrosineResidue> trList){

        List<TyrosineResidue> tempTRs = trList.stream()
                .filter(tyrosineResidue -> tyrosineResidue.getActivation())
                .toList();
        TyrosineResidue tr = tempTRs.get(rand.nextInt(tempTRs.size()));
        SHP1 shp1 = shp1List.get(rand.nextInt(shp1List.size()));
        shp1.regulation(tr);

        List<JAK2> jak2s = jak2sList.stream()
                .filter(jak2 -> jak2.getActivation())
                .toList();
        JAK2 jak2 = jak2sList.get(rand.nextInt(jak2sList.size()));
        shp1.regulation(jak2);
    }


    public void regulateTwo(List<Ski> skiList){

        Ski ski = skiList.get(rand.nextInt(skiList.size()));
        ski.regulation(smadList);
    }


    public void regulateThree(List<TyrosineResidue>trList, List<SOCS> socsList){
        List<TyrosineResidue> tempTR2s = trList.stream()
                .filter(tyrosineResidue -> tyrosineResidue.getActivation())
                .toList();
        System.out.println("socslist" + socsList.size());
        if(!socsList.isEmpty()){
            SOCS socs = socsList.get(rand.nextInt(socsList.size()));
            socs.regulation(tempTR2s.get(rand.nextInt(tempTR2s.size())));
            List<STAT> tempStats = statList.stream()
                    .filter(stat -> stat.getActivation())
                    .toList();
            if(tempStats.size()> 0){
                socs.regulation(tempStats.get(rand.nextInt(tempStats.size())));
                List<EpoR> tempEpoRs = epoRList.stream()
                        .filter(epoR -> epoR.getActivation())
                        .toList();

                socs.regulation(epoRList.get(rand.nextInt(epoRList.size())));
            }

        }




    }


    public void regulateFour(List<Binding<Ras, GDP>> RasGDPList, List<SOS> sosList){
        List<Binding<Ras, GDP>> tempRasGDPs = RasGDPList.stream()
                .filter(RasGDP -> !RasGDP.getKey().getStatus())
                .toList();

        SOS sos = sosList.get(rand.nextInt(sosList.size()));
        if(tempRasGDPs.size() > 0){
            sos.catalyze(tempRasGDPs.get(rand.nextInt(tempRasGDPs.size())));
        }

    }
}
