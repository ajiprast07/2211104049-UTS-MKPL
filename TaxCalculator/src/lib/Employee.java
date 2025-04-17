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

	private LocalDate joinDate; // Menggunakan LocalDate untuk tanggal bergabung
	private int monthWorkingInYear;

	private boolean isForeigner;
	private Gender gender; // Menggunakan enum Gender

	private Salary salary; // Menggunakan class Salary untuk menyimpan informasi gaji

	private String spouseName;
	private String spouseIdNumber;

	private List<String> childNames;
	private List<String> childIdNumbers;

	// Konstanta untuk grade salaries dan foreigner multiplier
	private static final int SALARY_GRADE_1 = 3000000;
	private static final int SALARY_GRADE_2 = 5000000;
	private static final int SALARY_GRADE_3 = 7000000;
	private static final double FOREIGNER_SALARY_MULTIPLIER = 1.5;

	// Enum untuk Gender
	public enum Gender {
		MALE, FEMALE
	}

	// Enum untuk Grade
	public enum Grade {
		LEVEL_1(SALARY_GRADE_1), LEVEL_2(SALARY_GRADE_2), LEVEL_3(SALARY_GRADE_3);

		private final int baseSalary;

		Grade(int baseSalary) {
			this.baseSalary = baseSalary;
		}

		public int getBaseSalary() {
			return baseSalary;
		}
	}

	// Class untuk Salary
	public static class Salary {
		private int monthlySalary;
		private int otherMonthlyIncome;

		public Salary(int monthlySalary, int otherMonthlyIncome) {
			this.monthlySalary = monthlySalary;
			this.otherMonthlyIncome = otherMonthlyIncome;
		}

		public int getMonthlySalary() {
			return monthlySalary;
		}

		public int getOtherMonthlyIncome() {
			return otherMonthlyIncome;
		}

		public void setMonthlySalary(int monthlySalary) {
			this.monthlySalary = monthlySalary;
		}

		public void setOtherMonthlyIncome(int otherMonthlyIncome) {
			this.otherMonthlyIncome = otherMonthlyIncome;
		}
	}

	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address,
	                LocalDate joinDate, boolean isForeigner, Gender gender) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.joinDate = joinDate;
		this.isForeigner = isForeigner;
		this.gender = gender;

		childNames = new LinkedList<>();
		childIdNumbers = new LinkedList<>();
	}

	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya 
	 * (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */
	public void setMonthlySalary(Grade grade) {
		int baseSalary = grade.getBaseSalary();
		salary = new Salary(adjustSalaryForForeigner(baseSalary), salary.getOtherMonthlyIncome());
	}

	private int adjustSalaryForForeigner(int baseSalary) {
		if (isForeigner) {
			return (int) (baseSalary * FOREIGNER_SALARY_MULTIPLIER);
		}
		return baseSalary;
	}

	public void setAnnualDeductible(int deductible) {
		// Tidak ada perubahan untuk annualDeductible
	}

	public void setAdditionalIncome(int income) {
		if (salary == null) {
			salary = new Salary(0, income);
		} else {
			salary.setOtherMonthlyIncome(income);
		}
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

		//Menghitung berapa lama pegawai bekerja dalam setahun ini, 
		//jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		monthWorkingInYear = calculateMonthsWorkedThisYear();

		return TaxFunction.calculateTax(
			salary.getMonthlySalary(),
			salary.getOtherMonthlyIncome(),
			monthWorkingInYear,
			0, // Asumsi deductible tidak berubah
			spouseIdNumber.equals(""),
			childIdNumbers.size()
		);
	}

	private int calculateMonthsWorkedThisYear() {
		LocalDate date = LocalDate.now();

		if (date.getYear() == joinDate.getYear()) {
			return date.getMonthValue() - joinDate.getMonthValue();
		} else {
			return 12;
		}
	}
}
