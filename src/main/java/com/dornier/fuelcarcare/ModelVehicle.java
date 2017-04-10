package com.dornier.fuelcarcare;

import java.util.ArrayList;

/**
 * Created by Dornier on 16/03/2017.
 */

public class ModelVehicle {
    private String name;
    private String manufacturer;
    private String model;
    private String year;
    private ArrayList<ModelMaintenanceAlert> alerts;
    private ArrayList<ModelFillUp> fillUps;
    private ArrayList<ModelExpense> expenses;

    public ModelVehicle(String name) {
        this.name       = name;
        this.manufacturer= "";
        this.model      = "";
        this.year       = "";
        this.alerts     = new ArrayList<ModelMaintenanceAlert>();
        this.fillUps    = new ArrayList<ModelFillUp>();
        this.expenses   = new ArrayList<ModelExpense>();
    }

    public ModelVehicle(String name, String manufacturer, String model, String year) {
        this.name           = name;
        this.manufacturer   = manufacturer;
        this.model          = model;
        this.year           = year;
        this.alerts         = new ArrayList<ModelMaintenanceAlert>();
        this.fillUps        = new ArrayList<ModelFillUp>();
        this.expenses       = new ArrayList<ModelExpense>();
    }

    public String getName() {
        return name;
    }

    public String toString(){
        return getName();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ArrayList<ModelMaintenanceAlert> getAlerts() {
        return alerts;
    }

    public ArrayList<ModelFillUp> getFillUps() {
        return fillUps;
    }

    public ArrayList<ModelExpense> getExpenses() {
        return expenses;
    }
}
