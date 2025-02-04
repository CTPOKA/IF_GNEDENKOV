package org.example.carapp.car;

public class Ford extends Car {
    final private double towingCapacity;

    public Ford(int year, String transmission, String model, CarColor color,
                double price, int power, double towingCapacity) {
        super(year, transmission, model, color, price, power);
        this.towingCapacity = towingCapacity;
    }

    @Override
    public String getInfo() {
        return "Ford | %s | Грузоподъемность: %s т.".formatted(super.getInfo(), towingCapacity);
    }
}
