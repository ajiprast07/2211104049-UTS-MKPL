package lib;

public class TaxFunction {

    private static final int BASE_PTWP = 54000000;
    private static final int MARRIED_ADDITION = 4500000;
    private static final int CHILD_ADDITION = 1500000;
    private static final double TAX_RATE = 0.05;
    private static final int MAX_WORKING_MONTH = 12;
    private static final int MAX_CHILDREN_COUNTED = 3;

    /**
     * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
     */
    public static int calculateTax(Employee employee) {
        validateInput(employee.getNumberOfMonthWorking());
        int taxableIncome = calculateTaxableIncome(employee);
        return calculateFinalTax(taxableIncome);
    }

    private static void validateInput(int numberOfMonthWorking) {
        if (numberOfMonthWorking > MAX_WORKING_MONTH) {
            throw new IllegalArgumentException("Working months cannot exceed 12.");
        }
    }

    private static int calculateTaxableIncome(Employee employee) {
        int totalIncome = (employee.getMonthlySalary() + employee.getOtherMonthlyIncome()) * employee.getNumberOfMonthWorking();
        int nonTaxableIncome = calculateNonTaxableIncome(employee);
        return totalIncome - employee.getDeductible() - nonTaxableIncome;
    }

    private static int calculateNonTaxableIncome(Employee employee) {
        int nonTaxableIncome = BASE_PTWP;
        if (employee.isMarried()) {
            nonTaxableIncome += MARRIED_ADDITION;
        }
        nonTaxableIncome += Math.min(employee.getNumberOfChildren(), MAX_CHILDREN_COUNTED) * CHILD_ADDITION;
        return nonTaxableIncome;
    }

    private static int calculateFinalTax(int taxableIncome) {
        int tax = (int) Math.round(TAX_RATE * taxableIncome);
        return tax < 0 ? 0 : tax;
    }
}
