package ooad.life.cells.pathway;

public class GAP {
    //GTPase Activating protein
    private static int counter = 0;
    private int id;
    String name = "GAP";
    public GAP(){
        this.id = ++counter;
    }

    public int getId() { return id;}

    @Override
    public String toString() {
        return name + "(" + id + ")";
    }


}
