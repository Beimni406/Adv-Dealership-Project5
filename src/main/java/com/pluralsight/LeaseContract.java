package com.pluralsight;

public class LeaseContract extends Contract {
    private double expectedEndingValue;
    private double leaseFee;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);

        // The car will be worth 50% of its value at the end
        this.expectedEndingValue = vehicleSold.getPrice() * 0.5;

        // Lease fee is 7% of the price
        this.leaseFee = vehicleSold.getPrice() * 0.07;
    }

    @Override
    public double getTotalPrice() {
        // Total = lease fee + residual value
        return getVehicleSold().getPrice() + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        double capCost = getVehicleSold().getPrice();
        double residual = expectedEndingValue;
        double rate = 0.04 / 12; // 4% annual interest
        int months = 36; // 3 years

        // Lease payment formula
        return ((capCost - residual) * rate) / (1 - Math.pow(1 + rate, -months));
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }
}
