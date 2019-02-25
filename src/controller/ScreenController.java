package controller;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.function.Function;

public class ScreenController {
    private HashMap<String, Pane> screenMap = new HashMap<>();
    private HashMap<String, Function<Object, Void>> beforeChange = new HashMap<>();
    private HashMap<String, Function<Object, Void>> afterChange = new HashMap<>();

    private Scene main;

    public ScreenController(Scene main) {
        this.main = main;
    }

    public void add(String name, Pane pane){
        screenMap.put(name, pane);
    }

    public void  addBeforeHook(String name,  Function<Object, Void> fn) {
        beforeChange.put(name, fn);
    }
    public void  addAfterHook(String name,  Function<Object, Void> fn) {
        afterChange.put(name, fn);
    }
    public void remove(String name){
        screenMap.remove(name);
    }

    public void activate(String name){
        if(beforeChange.get(name)!= null) {
            beforeChange.get(name).apply(null);
        }


        main.setRoot( screenMap.get(name) );
        if(afterChange.get(name)!= null) {
            afterChange.get(name).apply(null);
        }

    }
}