package org.example;

import java.util.ArrayList;
import java.util.List;

public class CarStorage {

    private static CarStorage instance;
    private List<Car> carStorageList = new ArrayList<>();

    private CarStorage(){
        addCar(new Car(98765,"Audi","RS3", carStandard.A,null, false));
    }

    public void setCarStorageList(List<Car> carStorageList) {
        this.carStorageList = carStorageList;
    }

    public List<Car> getCarStorageList() {
        return carStorageList;
    }

    public static CarStorage getInstance(){
        if(instance == null){
            instance = new CarStorage();
        }
        return instance;
    }

    public void printCarList(){
        for (Car car : carStorageList) {
            System.out.println(car.getBrand() + " " + car.getModel() + " " + car.VIN + " " + car.temporaryOwner + " " + car.isRented);
        }
    }

    public void addCar(Car car){
        carStorageList.add(car);
    }
}
