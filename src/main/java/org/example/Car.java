package org.example;

import java.time.LocalDate;

public class Car {
    public int VIN;
    public String brand;
    public String model;
    public boolean isRented;
    public User temporaryOwner;
    public carStandard carType;

    Car(int VIN, String brand, String model, carStandard carStandard , User temporaryOwner, boolean isRented){
        this.VIN = VIN;
        this.brand = brand;
        this.model = model;
        this.temporaryOwner = temporaryOwner;
        this.carType = carStandard;
        this.isRented = isRented;
    }


    public int getVIN() {
        return VIN;
    }

    public void setVIN(int VIN) {
        this.VIN = VIN;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public User getTemporaryOwner() {
        return temporaryOwner;
    }

    public void setTemporaryOwner(User temporaryOwner) {
        this.temporaryOwner = temporaryOwner;
    }

    public boolean isRented(boolean b) {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }
}
