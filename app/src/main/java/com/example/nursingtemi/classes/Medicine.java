package com.example.nursingtemi.classes;

public class Medicine {

    private String brand;
    private String concentration;
    private String form;
    private String name;
    private String barcode;
    private String qr;


    public Medicine(String brand, String concentration, String form, String name, String barcode, String qr)
    {
        this.brand = brand;
        this.concentration = concentration;
        this.form = form;
        this.name = name;
        this.barcode = barcode;
        this.qr = qr;
    }


    public String getBrand() {
        return brand;
    }

    public String getConcentration() {
        return concentration;
    }

    public String getForm() {
        return form;
    }

    public String getName() {
        return name;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public void setConcentration(String concentration) {
        this.concentration = concentration;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

}
