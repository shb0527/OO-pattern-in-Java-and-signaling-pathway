package ooad.life.cells.pathway;

public enum MoleculeType {
    Cytokine(10),
    EGF(10),
    TGFB(10),
    EPOR(30),
    RTK(70),
    TGFBR(30),
    JAK2(200),
    MAPKinase(700),
    MEK(700),
    Raf(300),
    GAP(300),
    Ras(300),
    SHP1(300),
    Ski(120),
    SOCS(120),
    SOS(120),
    Smad(700),
    STAT(700),
    GDP(2000),
    GTP(3000),
    TyrosineResidue(50);


    private final int ratio;

    MoleculeType(int ratio) {
        this.ratio = ratio;
    }

    public int getRatio() {
        return ratio;
    }
}
