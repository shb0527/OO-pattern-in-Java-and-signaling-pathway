package ooad.life.cells.pathway;


public class ObserverUI implements Observer{
    private final String elementId;
    public ObserverUI(String elementId){
        this.elementId = elementId;
    }
    public void update(String state){
        System.out.println(elementId + ": " + state);
    }
}
