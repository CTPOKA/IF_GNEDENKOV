package org.example.carapp.car;

public class BMW extends Car {
    final private boolean hasPremiumPackage;

    public BMW(int year, String transmission, String model, CarColor color,
               double price, int power, boolean hasPremiumPackage) {
        super(year, transmission, model, color, price, power);
        this.hasPremiumPackage = hasPremiumPackage;
    }

    @Override
    public String getInfo() {
        return "BMW | %s | Премиум пакет: %s".formatted(super.getInfo(), hasPremiumPackage);
    }
}
