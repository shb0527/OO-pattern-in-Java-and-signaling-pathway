package ooad.life.cells.pathway.Ligand;

import ooad.life.cells.pathway.MoleculeType;
import ooad.life.cells.pathway.SubjectRegistry;
import ooad.life.cells.pathway.receptors.EpoR;
import ooad.life.cells.pathway.receptors.RTK;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class LigandFactory {

    private List<Ligand> ligandPool = new ArrayList<>();
    public List<Cytokine> createNumberOfCytokine(Integer numCytokines, List<EpoR> epoRList, SubjectRegistry subjectRegistry) {
        return IntStream.range(0, numCytokines)
                .mapToObj(i -> {
                   Cytokine cytokine = new Cytokine(epoRList, subjectRegistry);
                   return cytokine;
                })
                .map(Cytokine.class::cast)
                .toList();
    }

    public List<EGF> createNumberOfEGF(Integer numEGFs, List<RTK> rtkList, SubjectRegistry subjectRegistry) {
        return IntStream.range(0, numEGFs)
                .mapToObj(i -> {
                    EGF egf = new EGF(rtkList, subjectRegistry);
                    return egf;
                })
                .map(EGF.class::cast)
                .toList();
    }
    public List<TGFB> createNumberOfTGFB(Integer numTGFBs, SubjectRegistry subjectRegistry) {
        return IntStream.range(0, numTGFBs)
                .mapToObj(i -> {
                    TGFB tgfb = new TGFB(subjectRegistry);
                    return tgfb;
                })
                .map(TGFB.class::cast)
                .toList();
    }


}
