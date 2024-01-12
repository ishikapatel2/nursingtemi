package com.example.nursingtemi;

public class Record {

    private String pN;
    private String hR;
    private String temp;
    private String bp;
    private String oxySat;
    private int id;

    public Record(String s) {
        String[] parts = s.split(", ");
        this.pN = parts[0].split(": ")[1];
        this.hR = parts[1].split(": ")[1];
        this.temp = parts[2].split(": ")[1];
        this.bp = parts[3].split(": ")[1];
        this.oxySat = parts[4].split(": ")[1];
        this.id = Integer.parseInt(parts[5].split(": ")[1]);
    }

    public int getId() {
        return this.id;
    }

    public String getPatientName() {
        return this.pN;
    }

    public String getHeartRate() {
        return this.hR;
    }

    public String getTemp() {
        return this.temp;
    }

    public String getBloodPressure() {
        return this.bp;
    }

    public String getOxySat() {
        return this.oxySat;
    }

}
