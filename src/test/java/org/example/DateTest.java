package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

public class DateTest {
    private RentalService rentalService;
    private CarStorage carStorage;
    private RentStorage rentStorage;
    private Car car1;
    private User user1;
    @BeforeEach
    void setup() {
        this.rentalService = new RentalService(rentStorage,carStorage);
        this.rentStorage = new RentStorage();
        this.carStorage = new CarStorage();
        this.car1 = new Car(12345,"Mercedes","B220", carStandard.B,null);
        this.user1 = new User(1);
    }

    @ParameterizedTest
    @MethodSource("inputData")
    void shouldHaveOverLappingDates(LocalDate fromDate, LocalDate toDate){
        carStorage.addCar(car1);

    }

    public static Stream<Arguments> inputData(){
        return Stream.of(
                Arguments.of(LocalDate.of(2023,11,25), LocalDate.of(2023,11,30)),
                Arguments.of(LocalDate.of(2023,11,20), LocalDate.of(2023,11,25)),
                Arguments.of(LocalDate.of(2023,11,1), LocalDate.of(2023,11,5)),
                Arguments.of(LocalDate.of(2023,11,15), LocalDate.of(2023,11,26)),
                Arguments.of(LocalDate.of(2023,11,1), LocalDate.of(2023,12,6)),
                Arguments.of(LocalDate.of(2023,11,27), LocalDate.of(2023,11,28)),
                Arguments.of(LocalDate.of(2023,11,29), LocalDate.of(2023,12,6))

        );
    }
}
