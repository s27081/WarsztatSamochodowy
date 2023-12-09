package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class RentalServiceTest {

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
        this.car1 = new Car(12345,"Mercedes","B220", carStandard.B,null);
        this.user1 = new User(1);
    }

    @Test
    void doesCarExist(){
        //GIVEN
        int vin1 = car1.getVIN();

        //WHEN
        CarStorage.getInstance().addCar(car1);
        boolean v1 = rentalService.carExist(vin1);

        //THEN
        assertThat(v1).isEqualTo(true);
    }

    @Test
    void doesNotCarExist(){
        int vin1 = 345678;

        CarStorage.getInstance().addCar(car1);

        //WHEN
        boolean v1 = rentalService.carExist(vin1);

        //THEN
        assertThat(v1).isEqualTo(false);
    }

    @Test
    void isCreatingRentPossible(){

        LocalDate dateFrom = LocalDate.parse("2023-10-16");
        LocalDate dateTo = LocalDate.parse("2023-10-21");
        Car car = car1;
        User user = user1;


        CarRentInfo carRentInfo = new CarRentInfo(user,car.getVIN(),dateFrom,dateTo);
        int vin = rentalService.createRent(dateFrom,dateTo,car,user).getVIN();

        assertThat(vin).isEqualTo(carRentInfo.getVIN());

    }

    @Test
    void isNotCreatingRentPossible(){

        LocalDate dateFrom = LocalDate.parse("2023-10-16");
        LocalDate dateTo = LocalDate.parse("2023-10-11");
        Car car = car1;
        User user = user1;

        CarRentInfo carRentInfo = new CarRentInfo(user,car.getVIN(),dateFrom,dateTo);
        CarRentInfo rent = rentalService.createRent(dateFrom, dateTo, car, user);

        assertThat(rent).isNull();

    }
    @Test
    void isEstimatedPriceEqual(){

        LocalDate dateFrom = LocalDate.parse("2023-10-16");
        LocalDate dateTo = LocalDate.parse("2023-10-21");
        Car car = car1;

        CarStorage.getInstance().addCar(car1);
        double price = rentalService.estimatePrice(car1.getVIN(),dateFrom,dateTo);

        assertThat(price).isEqualTo(600.0);

    }

    @Test
    void doesCheckDateWork(){
        LocalDate dateFrom = LocalDate.parse("2023-10-16");
        LocalDate dateTo = LocalDate.parse("2023-10-21");
        Car car = car1;
        User user = user1;


        CarRentInfo carRentInfo = new CarRentInfo(user,car.getVIN(),dateFrom,dateTo);
        rentalService.createRent(dateFrom,dateTo,car,user);

        rentalService.checkDateInRent(LocalDate.parse("2023-12-12"));

        assertThat(rentStorage.getRentList()).isEmpty();
    }

//    @Test
 //   void doesNotCheckDateWork(){
  //      LocalDate dateFrom = LocalDate.parse("2023-10-16");
   //     LocalDate dateTo = LocalDate.parse("2023-10-21");
    //    Car car = car1;
     //   User user = user1;


      //  CarRentInfo carRentInfo = new CarRentInfo(user,car.getVIN(),dateFrom,dateTo);
      //  rentalService.createRent(dateFrom,dateTo,car,user);

        //rentalService.checkDateInRent(LocalDate.parse("2023-10-17"));

    //    assertThat(rentStorage.getRentList()).isNotEmpty();
   // }



}