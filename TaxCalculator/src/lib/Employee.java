package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	private String employeeId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;

	private int yearJoined;
	private int monthJoined;
	private int dayJoined;
	private int monthWorkingInYear;

	private boolean isForeigner;
	private boolean gender;

	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;

	private String spouseName;
	private String spouseIdNumber;

	private List<String> childNames;
	private List<String> childIdNumbers;

	private static final double FOREIGNER_SALARY_MULTIPLIER = 1.5;

	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address,
	                int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, boolean gender) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.yearJoined = yearJoined;
		this.monthJoined = monthJoined;
		this.dayJoined = dayJoined;
		this.isForeigner = isForeigner;
		this.gender = gender;

		childNames = new LinkedList<>();
		childIdNumbers = new LinkedList<>();
	}

	public void setMonthlySalary(int grade) {
		int baseSalary = getBaseSalaryByGrade(grade);
		monthlySalary = adjustSalaryForForeigner(baseSalary);
	}

	private int getBaseSalaryByGrade(int grade) {
		switch (grade) {
			case 1:
				return 3000000;
			case 2:
				return 5000000;
			case 3:
				return 7000000;
			default:
				throw new IllegalArgumentException("Invalid grade: " + grade);
		}
	}

	private int adjustSalaryForForeigner(int baseSalary) {
		if (isForeigner) {
			return (int) (baseSalary * FOREIGNER_SALARY_MULTIPLIER);
		}
		return baseSalary;
	}

	public void setAnnualDeductible(int deductible) {
		this.annualDeductible = deductible;
	}

	public void setAdditionalIncome(int income) {
		this.otherMonthlyIncome = income;
	}

	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = spouseIdNumber;
	}

	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}

	public int getAnnualIncomeTax() {

		monthWorkingInYear = calculateMonthsWorkedThisYear();

		return TaxFunction.calculateTax(
			monthlySalary,
			otherMonthlyIncome,
			monthWorkingInYear,
			annualDeductible,
			spouseIdNumber.equals(""),
			childIdNumbers.size()
		);
	}

	private int calculateMonthsWorkedThisYear() {
		LocalDate date = LocalDate.now();

		if (date.getYear() == yearJoined) {
			return date.getMonthValue() - monthJoined;
		} else {
			return 12;
		}
	}
}
