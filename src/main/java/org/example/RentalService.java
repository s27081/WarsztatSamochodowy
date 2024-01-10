package org.example;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Optional;
@Service
public class RentalService {

    private final RentStorage rentStorage;
    private final CarStorage carStorage;

    public RentalService(RentStorage rentStorage, CarStorage carStorage) {
        this.rentStorage = rentStorage;
        this.carStorage = carStorage;
    }
    int baseOneDayRent = 100;


    public boolean isAvailable(int vin){
        if(rentStorage.getRentList().isEmpty()){
            return true;
        }
        return rentStorage.getRentList().stream()
                .filter(Objects::nonNull)
                .noneMatch(v -> vin == v.getVIN()
                );
    }

    public void checkDateInRent(LocalDate currentDate){
        rentStorage.getRentList().removeIf(d -> d != null && !currentDate.isBefore(d.getDateTo()));
    }
    public CarRentInfo createRent(LocalDate dateFrom, LocalDate dateTo, Car car, User user) {
        if (dateFrom.isBefore(dateTo) && isAvailable(car.getVIN())) {
            car.setTemporaryOwner(user);
            return new CarRentInfo(car.getTemporaryOwner(), car.getVIN(), dateFrom, dateTo);
        } else {
            System.out.println("Błąd przy tworzeniu rezerwacji");
        }
        return null;
    }


    public boolean carExist(int vin) {
        return carStorage.getCarStorageList().stream().anyMatch(v -> v.getVIN() == vin);
    }

    public double estimatePrice(int vin, LocalDate dateFrom, LocalDate dateTo) {
        double price = baseOneDayRent;
        double daysBetween = ChronoUnit.DAYS.between(dateFrom, dateTo);
        Car carPrice = carStorage.getCarStorageList().stream()
                .filter(car -> car.getVIN() == vin)
                .findAny()
                .orElseThrow();

            price *= daysBetween * carPrice.carType.getStandardValue();
            price = (double) Math.round(price * 100.0) / 100.0;

        return price;
    }
}