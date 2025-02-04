package org.example.carapp.car;

public class Suzuki extends Car {
    final private boolean allWheelDrive;

    public Suzuki(int year, String transmission, String model, CarColor color,
                  double price, int power, boolean allWheelDrive) {
        super(year, transmission, model, color, price, power);
        this.allWheelDrive = allWheelDrive;
    }

    @Override
    public String getInfo() {
        return "Suzuki | %s | Полный привод: %s".formatted(super.getInfo(), allWheelDrive);
    }
}
