package com.example.inurcalculator;

public class HistoryResult {
    private String id;
    private String result;
    private String equasion;

    public HistoryResult(String id, String result, String equasion) {
        this.id = id;
        this.result = result;
        this.equasion = equasion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getEquasion() {
        return equasion;
    }

    public void setEquasion(String equasion) {
        this.equasion = equasion;
    }
}
