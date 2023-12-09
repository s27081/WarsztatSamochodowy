package org.example;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarStorage {

    private List<Car> carStorageList = new ArrayList<>();


    public void setCarStorageList(List<Car> carStorageList) {
        this.carStorageList = carStorageList;
    }

    public List<Car> getCarStorageList() {
        return carStorageList;
    }


    public void printCarList(){
        for (Car car : carStorageList) {
            System.out.println(car.getBrand() + " " + car.getModel() + " " + car.VIN + " " + car.temporaryOwner);
        }
    }

    public void addCar(Car car){
        carStorageList.add(car);
    }
}
