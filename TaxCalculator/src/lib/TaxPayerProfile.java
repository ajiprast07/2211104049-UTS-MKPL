package lib;

public class TaxPayerProfile {
	private final int monthlySalary;
	private final int otherMonthlyIncome;
	private final int monthsWorked;
	private final int annualDeductible;
	private final boolean hasSpouse;
	private final int numberOfChildren;

	public TaxPayerProfile(int monthlySalary, int otherMonthlyIncome, int monthsWorked, int annualDeductible,
	                       boolean hasSpouse, int numberOfChildren) {
		this.monthlySalary = monthlySalary;
		this.otherMonthlyIncome = otherMonthlyIncome;
		this.monthsWorked = monthsWorked;
		this.annualDeductible = annualDeductible;
		this.hasSpouse = hasSpouse;
		this.numberOfChildren = numberOfChildren;
	}

	public int getMonthlySalary() {
		return monthlySalary;
	}

	public int getOtherMonthlyIncome() {
		return otherMonthlyIncome;
	}

	public int getMonthsWorked() {
		return monthsWorked;
	}

	public int getAnnualDeductible() {
		return annualDeductible;
	}

	public boolean hasSpouse() {
		return hasSpouse;
	}

	public int getNumberOfChildren() {
		return numberOfChildren;
	}
}
