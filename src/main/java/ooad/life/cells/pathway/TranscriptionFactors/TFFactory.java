package ooad.life.cells.pathway.TranscriptionFactors;

import ooad.life.cells.pathway.InnerCell;
import ooad.life.cells.pathway.RegulatoryProtein.SHP1;
import ooad.life.cells.pathway.SubjectFactory;
import ooad.life.cells.pathway.SubjectRegistry;

import java.util.List;
import java.util.stream.IntStream;

public class TFFactory {

    public List<Smad> createNumberOfSmad(Integer numSmad, InnerCell innerCell, SubjectRegistry subjectRegistry) {
        return IntStream.range(0, numSmad)
                .mapToObj(i -> {
                    Smad smad = new Smad(innerCell, subjectRegistry);
                    return smad;
                })
                .map(Smad.class::cast)
                .toList();
    }

    public List<STAT> createNumberOfSTAT(Integer numSTAT, InnerCell innerCell, SubjectRegistry subjectRegistry) {
        return IntStream.range(0, numSTAT)
                .mapToObj(i -> {
                    STAT stat = new STAT(innerCell, subjectRegistry);
                    return stat;
                })
                .map(STAT.class::cast)
                .toList();
    }


}
