package org.example.carapp.car;

public abstract class Car {
    private int year;
    private String transmission;
    private String modelName;
    private CarColor color;
    private double price;
    private int enginePower;

    public Car(int year, String transmission, String modelName, CarColor color, double price, int enginePower) {
        this.year = year;
        this.transmission = transmission;
        this.modelName = modelName;
        this.color = color;
        this.price = price;
        this.enginePower = enginePower;
    }

    public int getYear() { return year; }
    public String getTransmission() { return transmission; }
    public String getModelName() { return modelName; }
    public CarColor getColor() { return color; }
    public double getPrice() { return price; }
    public int getEnginePower() { return enginePower; }

    public void setColor(CarColor color) { this.color = color; }
    public void setPrice(double price) { this.price = price; }

    public String getInfo() {
        return String.format("%d г. | %s | %s | %s | %.2f$ | %d л.с.",
                year, transmission, modelName, color, price, enginePower);
    }
}