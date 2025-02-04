package org.example.carapp.service;

import org.example.carapp.car.Car;
import org.example.carapp.car.CarColor;

import java.util.List;

public class CarService {
    public static void printCarsAfter2006(List<Car> cars) {
        for (Car car : cars) {
            if (car.getYear() > 2006) {
                System.out.println(car.getInfo());
            } else {
                System.out.println("Устаревший авто: " + car.getModelName() + " (" + car.getYear() + ")");
            }
        }
    }

    public static void changeGreenToRed(List<Car> cars) {
        for (Car car : cars) {
            if (car.getColor() == CarColor.GREEN) {
                CarColor oldColor = car.getColor();
                car.setColor(CarColor.RED);
                System.out.printf("Цвет изменен: %s -> %s (%s)\n", oldColor, car.getColor(), car.getModelName());
            }
        }
    }

    public static void applyDiscountForOldCars(List<Car> cars, int yearThreshold, int percent) {
        for (Car car : cars) {
            if (car.getYear() <= yearThreshold) {
                double oldPrice = car.getPrice();
                double newPrice = oldPrice * (1 - percent / 100.0);
                car.setPrice(newPrice);
                System.out.printf("Цена снижена: %s - %.2f$ -> %.2f$\n", car.getModelName(), oldPrice, newPrice);
            }
        }
    }
}
