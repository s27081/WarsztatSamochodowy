package org.example;

import java.util.ArrayList;
import java.util.List;

public class RentStorage {

    public static RentStorage instance;
    List<CarRentInfo> rentList = new ArrayList<>();

    public static RentStorage getInstance(){
        if(instance == null){
            instance = new RentStorage();
        }
        return instance;
    }
    void addRent(CarRentInfo rent){
        rentList.add(rent);
    }

    public List<CarRentInfo> getRentList() {
        return rentList;
    }

    public void setRentList(List<CarRentInfo> rentList) {
        this.rentList = rentList;
    }

    public void printRentList(){
        for (CarRentInfo rentInfo : rentList) {
            System.out.println(rentInfo.getUser() + " " + rentInfo.getVIN() + " " + rentInfo.getDateNow() + " " + rentInfo.getDateTo());
        }
    }
}
