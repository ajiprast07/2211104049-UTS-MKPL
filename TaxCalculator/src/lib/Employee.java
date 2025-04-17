package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	private String employeeId;
	private Name name;
	private String idNumber;
	private String address;
	private JoinDate joinDate;
	private boolean isForeigner;
	private boolean gender; // true = Laki-laki, false = Perempuan

	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;

	private Spouse spouse;

	private List<String> childNames;
	private List<String> childIdNumbers;

	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, boolean gender) {
		this.employeeId = employeeId;
		this.name = new Name(firstName, lastName);
		this.idNumber = idNumber;
		this.address = address;
		this.joinDate = new JoinDate(yearJoined, monthJoined, dayJoined);
		this.isForeigner = isForeigner;
		this.gender = gender;

		childNames = new LinkedList<>();
		childIdNumbers = new LinkedList<>();
	}

	public void setMonthlySalary(int grade) {
		int baseSalary;
		switch (grade) {
			case 1:
				baseSalary = 3000000;
				break;
			case 2:
				baseSalary = 5000000;
				break;
			case 3:
				baseSalary = 7000000;
				break;
			default:
				baseSalary = 0;
		}

		monthlySalary = isForeigner ? (int) (baseSalary * 1.5) : baseSalary;
	}

	public void setAnnualDeductible(int deductible) {
		this.annualDeductible = deductible;
	}

	public void setAdditionalIncome(int income) {
		this.otherMonthlyIncome = income;
	}

	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouse = new Spouse(spouseName, spouseIdNumber);
	}

	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}

	public int getAnnualIncomeTax() {
		int monthWorkingInYear;
		LocalDate currentDate = LocalDate.now();
		if (currentDate.getYear() == joinDate.year) {
			monthWorkingInYear = currentDate.getMonthValue() - joinDate.month;
		} else {
			monthWorkingInYear = 12;
		}

		boolean hasSpouse = (spouse == null || spouse.getIdNumber().isEmpty());

		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, hasSpouse, childIdNumbers.size());
	}
}

// Kelas tambahan hasil ekstraksi Data Clumps
class Name {
	private String firstName;
	private String lastName;

	public Name(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
}

class JoinDate {
	int year;
	int month;
	int day;

	public JoinDate(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
}

class Spouse {
	private String name;
	private String idNumber;

	public Spouse(String name, String idNumber) {
		this.name = name;
		this.idNumber = idNumber;
	}

	public String getIdNumber() {
		return idNumber;
	}
}