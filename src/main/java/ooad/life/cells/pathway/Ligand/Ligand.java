package ooad.life.cells.pathway.Ligand;

public class Ligand {
    private static int counter = 0; // Static counter for unique IDs
    private int id;

    String name = "Ligand";
    public Ligand(){
        this.id = ++counter;
    }
    public int getId(){
        return id;
    }
    @Override
    public String toString() {
        return   name + "(" + id + ")";
    }
    public void bindingToReceptor(){}
}
