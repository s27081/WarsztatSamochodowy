package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class CarStorageTest {

    private RentalService rentalService;
    private CarStorage carStorage;
    private RentStorage rentStorage;
    private Car car1;
    private User user1;

    @BeforeEach
    void setup() {
        this.rentalService = new RentalService();
        this.rentStorage = new RentStorage();
        this.carStorage = new CarStorage();
        this.car1 = new Car(12345,"Mercedes","B220", carStandard.B,null, false);
        this.user1 = new User(1);
    }

    @Test
    void doesCarAdd(){
        Car carTest = new Car(1234556,"Audi","RS6",carStandard.E,null,false);
        carStorage.addCar(carTest);
        assertThat(carStorage.getCarStorageList().getLast()).isEqualTo(carTest);
    }
}
