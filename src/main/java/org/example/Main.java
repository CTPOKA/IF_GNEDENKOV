package org.example;

import org.example.carapp.car.*;
import org.example.carapp.service.CarService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();

        cars.add(new Suzuki(2005, "manual", "Jimny", CarColor.GREEN, 15000, 102, true));
        cars.add(new Toyota(2020, "automatic", "Camry", CarColor.SILVER, 25000, 178, "Hybrid"));
        cars.add(new Honda(2006, "manual", "Civic", CarColor.RED, 12000, 140, 4));
        cars.add(new Ford(2015, "automatic", "F-150", CarColor.BLUE, 35000, 325, 3.5));
        cars.add(new BMW(2022, "automatic", "X5", CarColor.BLACK, 65000, 335, true));
        cars.add(new Suzuki(2010, "manual", "Vitara", CarColor.GREEN, 18000, 112, false));
        cars.add(new Toyota(2007, "automatic", "Corolla", CarColor.WHITE, 17000, 132, null));
        cars.add(new Honda(2023, "automatic", "CR-V", CarColor.GREEN, 30000, 190, 5));
        cars.add(new Ford(2008, "manual", "Focus", CarColor.YELLOW, 9000, 120, 1.2));
        cars.add(new BMW(2018, "automatic", "320i", CarColor.GRAY, 42000, 184, false));

        System.out.println("1. Автомобили после 2006 года:");
        CarService.printCarsAfter2006(cars);

        System.out.println("\n2. Изменение зеленого цвета:");
        CarService.changeGreenToRed(cars);

        System.out.println("\n3. Снижение цены для старых автомобилей:");
        CarService.applyDiscountForOldCars(cars, 2006, 15);
    }
}