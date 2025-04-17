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
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null.");
        }
        int taxableIncome = calculateTaxableIncome(employee);
        return calculateFinalTax(taxableIncome);
    }

    private static int calculateTaxableIncome(Employee employee) {
        int totalIncome = (employee.getMonthlySalary() + employee.getOtherMonthlyIncome()) * employee.getNumberOfMonthWorking();
        int nonTaxableIncome = calculateNonTaxableIncome(employee);
        return totalIncome - employee.getDeductible() - nonTaxableIncome;
    }

    private static int calculateNonTaxableIncome(Employee employee) {
        int nonTaxableIncome = BASE_PTWP;
        nonTaxableIncome += calculateMarriedAddition(employee);
        nonTaxableIncome += calculateChildrenAddition(employee);
        return nonTaxableIncome;
    }

    private static int calculateMarriedAddition(Employee employee) {
        if (employee.isMarried()) {
            return MARRIED_ADDITION;
        }
        return 0;
    }

    private static int calculateChildrenAddition(Employee employee) {
        int childrenCount = Math.min(employee.getNumberOfChildren(), MAX_CHILDREN_COUNTED);
        return childrenCount * CHILD_ADDITION;
    }

    private static int calculateFinalTax(int taxableIncome) {
        if (taxableIncome < 0) {
            return 0;
        }
        int tax = (int) Math.round(TAX_RATE * taxableIncome);
        return tax < 0 ? 0 : tax;
    }
}
