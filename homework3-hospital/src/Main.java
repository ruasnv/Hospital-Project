import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		LinkedSortedList<Patient> sortedPatientsList = new LinkedSortedList<Patient>();
		PriorityQueue<Patient> patientQueue = new PriorityQueue<Patient>();
		LList<Patient> finalPatientsList = new LList<Patient>();
		
//		reading the txt file and creating patient objects
		
		File file = new File("records.txt");
		
		BufferedReader bReader = new BufferedReader(new FileReader(file));
		
		String line;
		
		while((line = bReader.readLine()) != null) {
			String[] splittedLine = line.split(",");
			String nameSurname = splittedLine[1];
			int age = Integer.parseInt(splittedLine[2]);
			String gender = splittedLine[3];
			String pregnancy = splittedLine[4];
			String disability = splittedLine[5];
			String date = splittedLine[6];
			String time = splittedLine[7];
			Patient patient = new Patient(nameSurname, age, gender, pregnancy, disability, date, time);
			sortedPatientsList.add(patient);
		}
		

//		printing all patients ordered according to their appointment date and time
		
		System.out.println("*** all patients ordered according to their appointment date and time ***\n");
		for (int i = 1; i <= sortedPatientsList.getLength(); i ++) {
			Patient current = sortedPatientsList.getEntry(i);
			System.out.println("Name-Surname: " + current.getNameSurname() + ", " + 
								"Age: " + current.getAge() + ", " + 
								"Gender: " + current.getGender() + ", " + 
								"Pregnancy: " + current.getPregnancy() + ", " + 
								"Disability: " + current.getDisability() + ", " + 
								"Date: " + current.getDate() + ", " + 
								"Time: " + current.getTime());
			patientQueue.add(current, current.getDate(), current.getPriority());
		}

		
//		printing all patients ordered according to their order of receiving treatment
		
		
		System.out.println("\n*** all patients ordered according to their order of receiving treatment ***\n");
		for (int i = 1; i <= patientQueue.getSize(); i++) {
			Patient current = patientQueue.remove();			
			System.out.println("Name-Surname: " + current.getNameSurname() + ", " + 
					"Age: " + current.getAge() + ", " + 
					"Gender: " + current.getGender() + ", " + 
					"Pregnancy: " + current.getPregnancy() + ", " + 
					"Disability: " + current.getDisability() + ", " + 
					"Date: " + current.getDate() + ", " + 
					"Time: " + current.getTime() + ", " +
					"Priority: " + current.getPriority());
			finalPatientsList.add(current);
		}
		
//		printing the remaining patients on the list after every 5 treatments
		
		while (finalPatientsList.getLength() != 5) {
			for (int i = 1; i <= 5; i++) {
				finalPatientsList.remove(1);
			}
			System.out.println("\nthe remaining patients on the list after 5 treatments: \n");
			for (int i = 1; i <= finalPatientsList.getLength(); i++) {
				System.out.println(finalPatientsList.getEntry(i).getNameSurname());
			}
		}
		
//		the final patient that received treatment
		
		for (int i = 1; i < 5; i++) {
			finalPatientsList.remove(1);
		}
	
		System.out.println("\nthe final patient that received treatment:\n\n" + finalPatientsList.getEntry(1).getNameSurname());
		
		
		
		
		
		bReader.close();
	}

}
