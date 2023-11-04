package org.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RentalService {

    int baseOneDayRent = 100;

    public CarRentInfo createRent(LocalDate dateFrom, LocalDate dateTo, Car car, User user) {
        if (!car.isRented(false)) {
            car.setRented(true);
            car.setTemporaryOwner(user);
            return new CarRentInfo(car.getTemporaryOwner(), car.getVIN(), dateFrom, dateTo);
        } else {
            System.out.println("Samochod juz jest wypozyczony");
        }
        return null;
    }

    public boolean carExisit(int vin) {
        return CarStorage.getInstance().getCarStorageList().stream().anyMatch(v -> v.getVIN() == vin);
    }

    public double estimatePrice(int vin, LocalDate dateFrom, LocalDate dateTo) {
        double price = baseOneDayRent;
        double daysBetween = ChronoUnit.DAYS.between(dateFrom, dateTo);
        Car carPrice = CarStorage.getInstance().getCarStorageList().stream()
                .filter(car -> car.getVIN() == vin)
                .findAny()
                .orElseThrow();

            price *= daysBetween * carPrice.carType.getStandardValue();
            price = (double) Math.round(price * 100.0) / 100.0;

        return price;
    }
}