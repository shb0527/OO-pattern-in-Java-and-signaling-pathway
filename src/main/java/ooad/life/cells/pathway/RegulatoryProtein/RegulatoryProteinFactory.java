package ooad.life.cells.pathway.RegulatoryProtein;

import ooad.life.cells.pathway.InnerCell;
import ooad.life.cells.pathway.SubjectFactory;
import ooad.life.cells.pathway.SubjectRegistry;
import ooad.life.cells.pathway.TranscriptionFactors.Smad;
import ooad.life.cells.pathway.receptors.TGFBReceptor;

import java.util.List;
import java.util.stream.IntStream;

public class RegulatoryProteinFactory {

    public List<SHP1> createNumberOfSHP1(Integer numSHP1, SubjectRegistry subjectRegistry) {
        return IntStream.range(0, numSHP1)
                .mapToObj(i -> {
                    SHP1 shp1 = new SHP1(subjectRegistry);
                    return shp1;
                })
                .map(SHP1.class::cast)
                .toList();
    }

    public List<Ski> createNumberOfSki(Integer numSki, InnerCell innerCell, SubjectRegistry subjectRegistry) {
        return IntStream.range(0, numSki)
                .mapToObj(i -> {
                    Ski ski = new Ski(innerCell, subjectRegistry);
                    return ski;
                })
                .map(Ski.class::cast)
                .toList();
    }

    public List<SOCS> createNumberOfSOCS(Integer numSOCS, SubjectRegistry subjectRegistry) {
        return IntStream.range(0, numSOCS)
                .mapToObj(i -> {
                    SOCS socs = new SOCS(subjectRegistry);
                    return socs;
                })
                .map(SOCS.class::cast)
                .toList();
    }

    public List<SOS> createNumberOfSOS(Integer numSOS, SubjectRegistry subjectRegistry) {
        return IntStream.range(0, numSOS)
                .mapToObj(i -> {
                    SOS sos = new SOS(subjectRegistry);
                    return sos;
                })
                .map(SOS.class::cast)
                .toList();
    }
}
