package ooad.life.cells.pathway;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class Subject {
    private String state;
    private final List<Observer> observers = new ArrayList<>();
    Map<EventType, Observer> observerMap = new HashMap<>();



    public Subject(){}
    public void addObserver(Observer observer, EventType eventType){
        observerMap.put(eventType, observer);
    }

    public void removeObserver(Observer observer){
        observerMap.remove(observer);
    }

    private void notifyObservers(EventType eventType,String message){
        Observer observer = observerMap.get(eventType);
        if(observer != null){
            observer.update(message);
        }
    }

    public void setState(EventType eventType, String message){
        this.state = message;
        notifyObservers(eventType, message);
    }

    public String getState(){
        return this.state;
    }
}
