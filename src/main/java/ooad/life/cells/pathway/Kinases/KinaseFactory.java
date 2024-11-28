package ooad.life.cells.pathway.Kinases;

import ooad.life.cells.pathway.InnerCell;
import ooad.life.cells.pathway.SubjectRegistry;
import ooad.life.cells.pathway.TranscriptionFactors.TranscriptionFactor;
import ooad.life.cells.pathway.TyrosineResidue;
import ooad.life.cells.pathway.receptors.EpoR;

import java.util.List;
import java.util.stream.IntStream;

public class KinaseFactory {

    public List<JAK2> createNumberOfJAK2(Integer numJAK2, List<EpoR> epoRList,
                                         List<TyrosineResidue> tyrosineResidueList,
                                         SubjectRegistry subjectRegistry) {
        return IntStream.range(0, numJAK2)
                .mapToObj(i -> {
                    JAK2 jak2 = new JAK2(epoRList, tyrosineResidueList, subjectRegistry);
                    return jak2;
                })
                .map(JAK2.class::cast)
                .toList();
    }

    public List<MAPkinase> createNumberOfMAPKinase(Integer numMAPKinase, InnerCell innerCell,
                                                   SubjectRegistry subjectRegistry) {
        return IntStream.range(0, numMAPKinase)
                .mapToObj(i -> {
                    MAPkinase maPkinase = new MAPkinase(innerCell, subjectRegistry);
                    return maPkinase;
                })
                .map(MAPkinase.class::cast)
                .toList();
    }

    public List<MEK> createNumberOfMEK(Integer numMEK, SubjectRegistry subjectRegistry) {
        return IntStream.range(0, numMEK)
                .mapToObj(i -> {
                    MEK mek = new MEK(subjectRegistry);
                    return mek;
                })
                .map(MEK.class::cast)
                .toList();
    }

    public List<Raf> createNumberOfRaf(Integer numRaf, List<MEK> mekList, SubjectRegistry subjectRegistry) {
        return IntStream.range(0, numRaf)
                .mapToObj(i -> {
                    Raf raf = new Raf(subjectRegistry, mekList);
                    return raf;
                })
                .map(Raf.class::cast)
                .toList();
    }



}
