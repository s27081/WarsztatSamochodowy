package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarStorage {

    static List<Car> carStorageList = new ArrayList<>();


    public void setCarStorageList(List<Car> carStorageList) {
        CarStorage.carStorageList = carStorageList;
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

    public void deleteCar(int vin) {
        carStorageList.removeIf(car -> car.getVIN() == vin);
    }
}
