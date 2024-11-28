package ooad.life.cells.pathway;

public class Binding<T, U> {
    private T key;
    private U value;

    public Binding(T key, U value) {
        this.key = key;
        this.value = value;
    }

    public String getName(){
        return key.toString() + " " + value.toString();
    }

    public T getKey() {
        return key;
    }

    public U getValue() {
        return value;
    }

    public void setValue(U value) {
        this.value = value;
    }
    public void unbind() {
        System.out.println(key + " is unbound from " + value);
        this.value = null;
    }

    public void rebind(U value){
        this.value = value;
    }
    @Override
    public String toString() {
        return value == null ? key + " is no longer bound" : key + " + " + value;
    }
}