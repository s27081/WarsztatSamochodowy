package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class RentStorageTest {
    private RentalService rentalService;
    private CarStorage carStorage;
    private RentStorage rentStorage;
    private Car car1;
    private User user1;

    @BeforeEach
    void setup() {
        this.rentStorage = new RentStorage();
        this.carStorage = new CarStorage();
        this.rentalService = new RentalService(rentStorage,carStorage);
        this.car1 = new Car(12345,"Mercedes","B220", carStandard.B,null);
        this.user1 = new User(1);
    }

    @Test
    void doesRentAdd(){
        LocalDate dateFrom = LocalDate.parse("2023-10-16");
        LocalDate dateTo = LocalDate.parse("2023-10-22");
        Car car = car1;
        User user = user1;

        CarRentInfo carRentInfoTest = new CarRentInfo(user,car.getVIN(),dateFrom,dateTo);

        rentStorage.addRent(carRentInfoTest);
        assertThat(rentStorage.getRentList().get(rentStorage.getRentList().size()-1)).isEqualTo(carRentInfoTest);
    }


}
