package com.pluralsight;

// The SalesContract class represents a contract for selling a vehicle
public class SalesContract extends Contract {
    private double salesTaxAmount;
    private double recordingFee = 100.00;
    private double processingFee;
    private boolean isFinanced;

    // Constructor that sets up the basic sale details
    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean isFinanced) {
        super(date, customerName, customerEmail, vehicleSold);
        this.isFinanced = isFinanced;

        // Sales tax = 5% of vehicle price
        this.salesTaxAmount = vehicleSold.getPrice() * 0.05;

        // Processing fee changes depending on price
        if (vehicleSold.getPrice() < 10000) {
            this.processingFee = 295.00;
        } else {
            this.processingFee = 495.00;
        }
    }

    // Calculates the total price including all fees and tax
    @Override
    public double getTotalPrice() {
        return getVehicleSold().getPrice() + salesTaxAmount + recordingFee + processingFee;
    }

    // Calculates monthly payment if the vehicle is financed
    @Override
    public double getMonthlyPayment() {
        if (!isFinanced) {
            return 0; // no monthly payment if not financed
        }

        double price = getTotalPrice();
        double rate;
        int months;

        // Financing terms: larger purchases get lower interest and longer term
        if (price >= 10000) {
            rate = 0.0425 / 12; // 4.25% APR for 48 months
            months = 48;
        } else {
            rate = 0.0525 / 12; // 5.25% APR for 24 months
            months = 24;
        }

        // Loan payment formula
        return (price * rate) / (1 - Math.pow(1 + rate, -months));
    }

    // Returns whether this sale was financed
    public boolean isFinanced() {
        return isFinanced;
    }

    // Returns the total amount of tax for the sale
    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    // Returns the processing fee that was applied
    public double getProcessingFee() {
        return processingFee;
    }

    // Returns the recording fee that was applied
    public double getRecordingFee() {
        return recordingFee;
    }
}
