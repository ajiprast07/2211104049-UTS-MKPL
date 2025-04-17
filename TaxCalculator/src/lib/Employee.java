package lib;

public class Employee {
    private int monthlySalary;
    private int otherMonthlyIncome;
    private int numberOfMonthWorking;
    private int deductible;
    private boolean isMarried;
    private int numberOfChildren;

    public Employee(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
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
