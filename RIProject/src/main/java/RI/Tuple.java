package RI;

import java.util.HashMap;

public class Tuple {

    private HashMap<String, Double> weightDoc;
    private double IDF;

    public Tuple(double IDF, HashMap<String, Double> weightDoc) {
        this.IDF = IDF;
        this.weightDoc = weightDoc;
    }

    public double getIDF() {
        return IDF;
    }

    public void setIDF(double iDF) {
        IDF = iDF;
    }

    public HashMap<String, Double> getWeightDoc() {
        return weightDoc;
    }
}
