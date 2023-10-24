import java.time.LocalDateTime;

public class Patient implements Comparable<Patient>{
	private String nameSurname;
	private int age;
	private String gender;
	private String pregnancy;
	private String disability;
	private String date;
	private String time;
	private int priority;
	private LocalDateTime localDateTime;
	
//	constructor
	public Patient(String nameSurname, int age, String gender, String pregnancy, String disability, String date, String time) {
		this.nameSurname = nameSurname;
		this.age = age;
		this.gender = gender;
		this.pregnancy = pregnancy;
		this.disability = disability;
		this.date = date;
		this.time = time;
		setPriority();
		localDateTime = setDateTime(date, time);
	}
	
	public boolean isPregnant() {
		if (pregnancy.equals("preg")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isDisabled() {
		if (disability.equals("disabled")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isChild() {
		if (age < 18) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isOld() {
		if (age > 65) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void setPriority() {
		if (isDisabled()) {
			this.priority = 5;
		}
		else if (isOld()) {
			this.priority = 4;
		}
		else if (isPregnant()) {
			this.priority = 3;
		}
		else if (isChild()) {
			this.priority = 2;
		}
		else {
			this.priority = 1;
		}
	}
	
	public int getPriority() {
		return priority;
	}
	
	public String getNameSurname() {
		return nameSurname;
	}
	public void setNameSurname(String nameSurname) {
		this.nameSurname = nameSurname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPregnancy() {
		return pregnancy;
	}
	public void setPregnancy(String pregnancy) {
		this.pregnancy = pregnancy;
	}
	public String getDisability() {
		return disability;
	}
	public void setDisability(String disability) {
		this.disability = disability;
	}
	public String getDate() {
		return date;
	}
	public LocalDateTime setDateTime(String date, String time) {
		String[] splittedDate = date.split("/");
		int day = Integer.parseInt(splittedDate[0]);
		int month = Integer.parseInt(splittedDate[1]);
		int year = Integer.parseInt(splittedDate[2]);
		String[] splittedTime = time.split(":");
		int hour = Integer.parseInt(splittedTime[0]);
		int minute = Integer.parseInt(splittedTime[1]);
		return LocalDateTime.of(year, month, day, hour, minute);
	}
	
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	

	@Override
	public int compareTo(Patient other) {
		if (other.getLocalDateTime().isBefore(this.localDateTime)) {
			return 1;
		}
		else if (other.getLocalDateTime().isAfter(this.localDateTime)) {
			return -1;
		}
		else {
			return 0;
		}
	}
	
}
