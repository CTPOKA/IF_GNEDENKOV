package org.example.carapp.car;

public class Toyota extends Car {
    final private String hybridType;

    public Toyota(int year, String transmission, String model, CarColor color,
                  double price, int power, String hybridType) {
        super(year, transmission, model, color, price, power);
        this.hybridType = hybridType;
    }

    @Override
    public String getInfo() {
        return "Toyota | %s | Гибрид: %s".formatted(super.getInfo(), hybridType);
    }
}
