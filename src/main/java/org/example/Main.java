package org.example;


import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        CarStorage carStorage = CarStorage.getInstance();
        RentStorage rentStorage = RentStorage.getInstance();
        RentalService rentalService = new RentalService();

        Car car1 = new Car(12345,"Mercedes","B220", carStandard.B,null, false);
        Car car2 = new Car(54321,"BMW","M5", carStandard.E, new User(2), false);
        Car car3 = new Car(23445,"Toyota","Prius", carStandard.A,null, false);

        carStorage.addCar(car1);
        carStorage.addCar(car2);
        carStorage.addCar(car3);

        rentStorage.addRent(rentalService.createRent(LocalDate.parse("2023-10-16"),LocalDate.parse("2023-11-16"),car1,new User(32)));
        rentStorage.printRentList();
        rentStorage.addRent(rentalService.createRent(LocalDate.parse("2023-10-16"),LocalDate.parse("2023-11-16"),car1,new User(24)));
        rentalService.isAvalable(12345);
        rentalService.isAvalable(323523423);
        rentalService.estimatePrice(23445,LocalDate.parse("2023-10-16"),LocalDate.parse("2023-11-16"));
    }

}