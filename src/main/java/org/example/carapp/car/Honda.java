package org.example.carapp.car;

public class Honda extends Car {
    final private int safetyRating;

    public Honda(int year, String transmission, String model, CarColor color,
                 double price, int power, int safetyRating) {
        super(year, transmission, model, color, price, power);
        this.safetyRating = safetyRating;
    }

    @Override
    public String getInfo() {
        return "Honda | %s | Безопасность: %d/5".formatted(super.getInfo(), safetyRating);
    }
}
