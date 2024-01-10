package org.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class Main {
    private final CarStorage carStorage;

    private final RentalService rentalService;

    private final RentStorage rentStorage;

    Main(CarStorage carStorage, RentalService rentalService, RentStorage rentStorage){
        this.carStorage = carStorage;
        this.rentalService = rentalService;
        this.rentStorage = rentStorage;

        initProcess();
    }
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

    }

    public void initProcess() {


        Car car1 = new Car(12345, "Mercedes", "B220", carStandard.B, null);
        Car car2 = new Car(54321, "BMW", "M5", carStandard.E, new User(2));
        Car car3 = new Car(23445, "Toyota", "Prius", carStandard.A, null);

        carStorage.addCar(car1);
        carStorage.addCar(car2);
        carStorage.addCar(car3);

        rentStorage.addRent(rentalService.createRent(LocalDate.parse("2023-10-16"), LocalDate.parse("2023-11-17"), car1, new User(32)));
        rentStorage.printRentList();
        rentStorage.addRent(rentalService.createRent(LocalDate.parse("2023-10-16"), LocalDate.parse("2023-11-18"), car1, new User(24)));
        rentalService.carExist(12345);
        rentalService.carExist(323523423);
        rentalService.estimatePrice(23445, LocalDate.parse("2023-10-16"), LocalDate.parse("2023-11-17"));
    }

}