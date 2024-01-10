package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class RentalServiceTest {

    private RentalService rentalService;
    private CarStorage carStorage;
    private RentStorage rentStorage;
    private Car car1;
    private Car car2;
    private User user1;

    @BeforeEach
    void setup() {
        this.rentStorage = new RentStorage();
        this.carStorage = new CarStorage();
        this.rentalService = new RentalService(rentStorage,carStorage);
        this.car1 = new Car(123452222,"Mercedes","B220", carStandard.B,null);
        this.car2 = new Car(12345,"Mercedes","B220", carStandard.B,null);
        this.user1 = new User(1);
    }

    @Test
    void doesCarExist(){
        //GIVEN
        int vin1 = car1.getVIN();

        //WHEN
        carStorage.addCar(car1);
        boolean v1 = rentalService.carExist(vin1);

        //THEN
        assertThat(v1).isEqualTo(true);
    }

    @Test
    void doesNotCarExist(){
        int vin1 = 345678;

        carStorage.addCar(car1);

        //WHEN
        boolean v1 = rentalService.carExist(vin1);

        //THEN
        assertThat(v1).isEqualTo(false);
    }

    @Test
    void isCreatingRentPossible(){

        LocalDate dateFrom = LocalDate.parse("2023-10-16");
        LocalDate dateTo = LocalDate.parse("2023-10-21");
        Car car = car2;
        User user = user1;

        Optional<CarRentInfo> optionalCarRentInfo = Optional.ofNullable(rentalService.createRent(dateFrom, dateTo, car, user));

        assertThat(optionalCarRentInfo.map(CarRentInfo::getVIN)).isEqualTo(Optional.of(car.getVIN()));

    }

    @Test
    void isNotCreatingRentPossible(){

        LocalDate dateFrom = LocalDate.parse("2023-10-16");
        LocalDate dateTo = LocalDate.parse("2023-10-11");
        Car car = car1;
        User user = user1;

        CarRentInfo rent = rentalService.createRent(dateFrom, dateTo, car, user);

        assertThat(rent).isNull();

    }
    @Test
    void isEstimatedPriceEqual(){

        LocalDate dateFrom = LocalDate.parse("2023-10-16");
        LocalDate dateTo = LocalDate.parse("2023-10-21");
        Car car = car1;

        carStorage.addCar(car1);
        double price = rentalService.estimatePrice(car1.getVIN(),dateFrom,dateTo);

        assertThat(price).isEqualTo(600.0);

    }

    @Test
    void doesCheckDateWork(){
        LocalDate dateFrom = LocalDate.parse("2023-10-16");
        LocalDate dateTo = LocalDate.parse("2023-10-21");
        LocalDate dateFrom2 = LocalDate.parse("2023-10-11");
        LocalDate dateTo2 = LocalDate.parse("2023-10-13");
        Car car = car1;
        Car car_2 = car2;
        User user = user1;

        rentStorage.addRent(rentalService.createRent(dateFrom, dateTo, car, user));
        rentStorage.addRent(rentalService.createRent(dateFrom2, dateTo2, car_2, user));

        rentalService.checkDateInRent(LocalDate.parse("2023-10-23"));

        assertThat(rentStorage.getRentList().isEmpty());
    }

    @Test
    void doesNotCheckDateWork(){
        LocalDate dateFrom = LocalDate.parse("2023-10-16");
        LocalDate dateTo = LocalDate.parse("2023-10-21");
        Car car = car1;
        User user = user1;

        rentStorage.addRent(rentalService.createRent(dateFrom, dateTo, car, user));

        rentalService.checkDateInRent(LocalDate.parse("2023-10-17"));

        assertThat(rentStorage.getRentList()).isNotEmpty();
    }
}