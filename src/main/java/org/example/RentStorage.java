package org.example;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Component
public class RentStorage {

    static List<CarRentInfo> rentList = new ArrayList<>();

    void addRent(CarRentInfo rent){
        rentList.add(rent);
    }

    public List<CarRentInfo> getRentList() {
        return rentList;
    }

    public void setRentList(List<CarRentInfo> rentList) {
        RentStorage.rentList = rentList;
    }

    public void deleteRent(LocalDate currentDate) {
        rentList.removeIf(d -> d.getDateTo().isAfter(currentDate));
        }

    public void printRentList(){
        for (CarRentInfo rentInfo : rentList) {
            System.out.println(rentInfo.getUser() + " " + rentInfo.getVIN() + " " + rentInfo.getDateNow() + " " + rentInfo.getDateTo());
        }
    }
}
