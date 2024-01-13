package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RentalServiceMockTest {

    @Mock
    private CarStorage carStorage;
    @Mock
    private RentStorage rentStorage;
    @InjectMocks
    private RentalService rentalService;

    private Car car1 = new Car(123452222,"Mercedes","B220", carStandard.B,null);
    private Car car2 = new Car(12345,"Mercedes","B220", carStandard.B,null);
    private User user1 = new User(1);

    @Test
    void doesCarExist(){
        //GIVEN
        when(carStorage.getCarStorageList()).thenReturn(List.of(car1));

        //WHEN
        boolean v1 = rentalService.carExist(car1.getVIN());

        //THEN
        assertThat(v1).isEqualTo(true);
    }

    @Test
    void doesNotCarExist(){
        when(carStorage.getCarStorageList()).thenReturn(List.of(car2));

        //WHEN
        boolean v1 = rentalService.carExist(car1.getVIN());

        //THEN
        assertThat(v1).isEqualTo(false);
    }

    @Test
    void isCreatingRentPossible_emptyRentals(){
        when(rentStorage.getRentList()).thenReturn(List.of());

        LocalDate dateFrom = LocalDate.parse("2023-10-16");
        LocalDate dateTo = LocalDate.parse("2023-10-21");

        Optional<CarRentInfo> optionalCarRentInfo = Optional.ofNullable(rentalService.createRent(dateFrom, dateTo, car1, user1));

        assertThat(optionalCarRentInfo.map(CarRentInfo::getVIN))
                .isEqualTo(Optional.of(car1.getVIN()));

    }

    @Test
    void isCreatingRentPossible(){
        CarRentInfo carRentInfo = new CarRentInfo(user1, car2.getVIN(),
                LocalDate.parse("2023-10-10"), LocalDate.parse("2023-10-15"));
        when(rentStorage.getRentList()).thenReturn(List.of(carRentInfo));

        LocalDate dateFrom = LocalDate.parse("2023-10-16");
        LocalDate dateTo = LocalDate.parse("2023-10-21");

        Optional<CarRentInfo> optionalCarRentInfo = Optional.ofNullable(rentalService.createRent(dateFrom, dateTo, car1, user1));

        assertThat(optionalCarRentInfo.map(CarRentInfo::getVIN))
                .isEqualTo(Optional.of(car1.getVIN()));

    }

    @Test
    void isNotCreatingRentPossible(){

        LocalDate dateFrom = LocalDate.parse("2023-10-19");
        LocalDate dateTo = LocalDate.parse("2023-10-15");
        Car car = car1;
        User user = user1;

        CarRentInfo rent = rentalService.createRent(dateFrom, dateTo, car, user);

        assertThat(rent).isNull();

    }
    @Test
    @Disabled
    void isEstimatedPriceEqual(){

        LocalDate dateFrom = LocalDate.parse("2023-10-17");
        LocalDate dateTo = LocalDate.parse("2023-11-22");
        Car car = car1;

        carStorage.addCar(car1);
        double price = rentalService.estimatePrice(car1.getVIN(),dateFrom,dateTo);

        assertThat(price).isEqualTo(600.0);

    }

    @Test
    void doesCheckDateWork(){
        LocalDate dateFrom = LocalDate.parse("2023-10-11");
        LocalDate dateTo = LocalDate.parse("2023-11-25");
        LocalDate dateFrom2 = LocalDate.parse("2023-10-12");
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
        LocalDate dateFrom = LocalDate.parse("2023-10-14");
        LocalDate dateTo = LocalDate.parse("2023-10-28");
        Car car = car1;
        User user = user1;

        rentStorage.addRent(rentalService.createRent(dateFrom, dateTo, car, user));

        rentalService.checkDateInRent(LocalDate.parse("2023-10-17"));

        assertThat(rentStorage.getRentList()).isNotEmpty();
    }
}