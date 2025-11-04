package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {

    public void saveContract(Contract contract) {
        try (FileWriter writer = new FileWriter("contracts.csv", true)) {
            Vehicle v = contract.getVehicleSold();
            String line = "";

            if (contract instanceof SalesContract sc) {
                line = String.format(
                        "SALE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f|%b|%.2f\n",
                        sc.getDate(), sc.getCustomerName(), sc.getCustomerEmail(),
                        v.getVin(), v.getYear(), v.getMake(), v.getModel(), v.getVehicleType(), v.getColor(),
                        v.getOdometer(), v.getPrice(), sc.getTotalPrice(), 0.05, 100.00,
                        sc.getTotalPrice(), sc.isFinanced(), sc.getMonthlyPayment()
                );
            } else if (contract instanceof LeaseContract lc) {
                line = String.format(
                        "LEASE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f\n",
                        lc.getDate(), lc.getCustomerName(), lc.getCustomerEmail(),
                        v.getVin(), v.getYear(), v.getMake(), v.getModel(), v.getVehicleType(), v.getColor(),
                        v.getOdometer(), v.getPrice(), lc.getTotalPrice(), 0.07,
                        lc.getTotalPrice(), lc.getMonthlyPayment()
                );
            }

            writer.write(line);
            System.out.println("✅ Contract saved to contracts.csv successfully!");
        } catch (IOException e) {
            System.out.println("⚠️ Error saving contract: " + e.getMessage());
        }
    }
}
