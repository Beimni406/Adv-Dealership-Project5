package com.pluralsight;

/*
 * LeaseContract.java
 * Author: Beimnet Kifle
 * Date: November 2025
 *
 * Description:
 * This class represents a lease contract for vehicles. It calculates
 * the total price and monthly payment for leased vehicles using
 * the vehicle's price, lease fee, and interest rate.
 */

// LeaseContract handles lease agreements for vehicles
public class LeaseContract extends Contract {

    // Step 1: Define fields for lease details
    private double expectedEndingValue; // how much the car will be worth after lease
    private double leaseFee; // percentage fee for leasing

    // Step 2: Create constructor that initializes all values and sets up lease details
    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);

        // The car will be worth 50% of its value at the end of the lease
        this.expectedEndingValue = vehicleSold.getPrice() * 0.5;

        // Lease fee is 7% of the vehicle’s price
        this.leaseFee = vehicleSold.getPrice() * 0.07;
    }

    // Step 3: Calculate total cost of the lease
    @Override
    public double getTotalPrice() {
        // Total = vehicle price + lease fee
        return getVehicleSold().getPrice() + leaseFee;
    }

    // Step 4: Calculate monthly payment using the lease formula
    @Override
    public double getMonthlyPayment() {
        double capCost = getVehicleSold().getPrice(); // starting value
        double residual = expectedEndingValue; // ending value
        double rate = 0.04 / 12; // 4% interest divided by 12 months
        int months = 36; // lease lasts 3 years

        // Lease payment formula
        return ((capCost - residual) * rate) / (1 - Math.pow(1 + rate, -months));
    }

    // Step 5: Getter for expected ending value
    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    // Step 6: Getter for lease fee
    public double getLeaseFee() {
        return leaseFee;
    }
}
