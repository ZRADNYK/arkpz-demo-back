package ua.nure.arkpz.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

// string ~ label
// double y ~ Y axis
@Getter
@Setter
@NoArgsConstructor
public class Chart {
    private ArrayList<HashMap<String, Double>> jsonArray;

    public void addPointToChart(HashMap<String, Double> charPoint) {
        jsonArray.add(charPoint);
    }
}
