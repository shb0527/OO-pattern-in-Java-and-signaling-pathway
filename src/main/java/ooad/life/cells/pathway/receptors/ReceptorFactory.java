package ooad.life.cells.pathway.receptors;

import ooad.life.cells.pathway.SubjectRegistry;
import ooad.life.cells.pathway.TranscriptionFactors.Smad;
import ooad.life.cells.pathway.TyrosineResidue;

import java.util.List;
import java.util.stream.IntStream;

public class ReceptorFactory {
    public List<TGFBReceptor> createNumberOfTGFBReceptor(Integer numTGFBReceptors, List<Smad> smadList,
                                                         SubjectRegistry subjectRegistry) {
        return IntStream.range(0, numTGFBReceptors)
                .mapToObj(i -> {
                    TGFBReceptor tgfbReceptor = new TGFBReceptor(smadList, subjectRegistry);
                    return tgfbReceptor;
                })
                .map(TGFBReceptor.class::cast)
                .toList();
    }

    public List<RTK> createNumberOfRTK(Integer numRTKs, List<TyrosineResidue> tyrosineResidueList, SubjectRegistry subjectRegistry) {
        return IntStream.range(0, numRTKs)
                .mapToObj(i -> {
                    RTK rtk = new RTK(tyrosineResidueList, subjectRegistry);
                    return rtk;
                })
                .map(RTK.class::cast)
                .toList();
    }

    public List<EpoR> createNumberOfEpoR(Integer numEpoRs, List<TyrosineResidue> tyrosineResidueList, SubjectRegistry subjectRegistry) {
        return IntStream.range(0, numEpoRs)
                .mapToObj(i -> {
                    EpoR epor = new EpoR(tyrosineResidueList, subjectRegistry);
                    return epor;
                })
                .map(EpoR.class::cast)
                .toList();
    }

}
