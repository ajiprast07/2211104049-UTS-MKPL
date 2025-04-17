package lib;

public class Employee {
    private int monthlySalary;
    private int otherMonthlyIncome;
    private int numberOfMonthWorking;
    private int deductible;
    private boolean isMarried;
    private int numberOfChildren;

    public Employee(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
        if (monthlySalary < 0 || otherMonthlyIncome < 0 || numberOfMonthWorking < 0 || deductible < 0 || numberOfChildren < 0) {
            throw new IllegalArgumentException("Input values cannot be negative.");
        }
        if (numberOfMonthWorking > 12) {
            throw new IllegalArgumentException("Number of months working cannot exceed 12.");
        }
        if (numberOfChildren > 3) {
            throw new IllegalArgumentException("Number of children cannot exceed 3.");
        }

        this.monthlySalary = monthlySalary;
        this.otherMonthlyIncome = otherMonthlyIncome;
        this.numberOfMonthWorking = numberOfMonthWorking;
        this.deductible = deductible;
        this.isMarried = isMarried;
        this.numberOfChildren = numberOfChildren;
    }

    public int getMonthlySalary() {
        return monthlySalary;
    }

    public int getOtherMonthlyIncome() {
        return otherMonthlyIncome;
    }

    public int getNumberOfMonthWorking() {
        return numberOfMonthWorking;
    }

    public int getDeductible() {
        return deductible;
    }

    public boolean isMarried() {
        return isMarried;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }
}
