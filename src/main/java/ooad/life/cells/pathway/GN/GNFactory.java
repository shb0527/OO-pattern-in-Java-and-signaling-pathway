package ooad.life.cells.pathway.GN;

import ooad.life.cells.pathway.InnerCell;
import ooad.life.cells.pathway.Ligand.Cytokine;
import ooad.life.cells.pathway.SubjectRegistry;
import ooad.life.cells.pathway.receptors.EpoR;

import java.util.List;
import java.util.stream.IntStream;

public class GNFactory {

    InnerCell innerCell;
    public GNFactory(InnerCell innerCell){
        this.innerCell = innerCell;
    }
    public List<GDP> createNumberOfGDP(Integer numGDP, List<GTP> gtpPool, SubjectRegistry subjectRegistry) {
        return IntStream.range(0, numGDP)
                .mapToObj(i -> {
                    GDP gdp = new GDP(gtpPool, subjectRegistry);
                    return gdp;
                })
                .map(GDP.class::cast)
                .toList();
    }

    public List<GTP> createNumberOfGTP(Integer numGTP, SubjectRegistry subjectRegistry) {
        return IntStream.range(0, numGTP)
                .mapToObj(i -> {
                    GTP gtp = new GTP(innerCell, subjectRegistry);
                    return gtp;
                })
                .map(GTP.class::cast)
                .toList();
    }
}
