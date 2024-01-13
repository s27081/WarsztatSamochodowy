package org.example;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RentalServiceITTest {

    @MockBean
    private CarStorage carStorage;
    @MockBean
    private RentStorage rentStorage;
    @Autowired
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
}