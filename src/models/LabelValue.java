package models;

import javafx.util.StringConverter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LabelValue {
    public String label;
    public String value;
    public ArrayList<String> meta;
    public LabelValue(String label, String value) {
            this.label = label;
            this.value = value;
            if(value == null) {
                this.value = label;
            }
    }

    @Override
    public String toString() {
        return label;
    }
}
