import java.util.*;
import java.util.Map.Entry;
import java.io.*;

public class PhysiciansHelper
{
	// symptom to illnesses map 
	private Map<String, List<String>> symptomChecker;


	/* Constructor symptomChecker map using TreeMap */
	public PhysiciansHelper()
	{ 
		// use TreeMap, i.e. sorted order keys
		symptomChecker = new TreeMap<String,List<String>>();
	} // end default constructor


	/* Reads a text file of illnesses and their symptoms.
	   Each line in the file has the form
		Illness: Symptom, Symptom, Symptom, ...  
	   Store data into symptomChecker map */

	public void processDatafile()
	{
		// Step 1: read in a data filename from keyboard
		//         create a scanner for the file
		
		// Step 2: process data lines in file scanner
		// 2.1 for each line, split the line into a disease and symptoms
		//     make sure to trim() spaces and use toLowercase()
		
		// 2.2 for symptoms, split into individual symptom
		//     create a scanner for symptoms 
		//     useDelimeter(",") to split into individual symptoms 
		//     make sure to trim() spaces and use toLowercase()
		//     for each symptom
		//        if it is already in the map, insert disease into related list
		//        if it is not already in the map, create a new list with the disease
		
		// Step 3: display symptomChecker map
		
		Scanner input = new Scanner (System.in);
		Scanner dataFile = null;
		String file = "sample_data.txt";
		String[] symptoms;
		String[] key;
		
		System.out.print("Enter data filename: ");
		file = input.next();
		System.out.println("\n============================================");
		System.out.println("symptomChecker map:");
		try {
			dataFile = new Scanner(new File (file));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		if (dataFile != null) {
			while (dataFile.hasNext()) {
				String line = dataFile.nextLine();
				key = line.toLowerCase().split(":");
				String disease = key[0].trim();
				symptoms = key[1].trim().split(",");
				
				for (String s: symptoms) {
					s = s.trim();
					List<String> value = symptomChecker.get(s);
					
					if (value != null) {
						value.add(disease);
					} else {
						value = new ArrayList<>();
						value.add(disease);
						symptomChecker.put(s, value);
					}
				}
			}
			for (Entry<String, List<String>> entries: symptomChecker.entrySet()) {
				System.out.println(entries.getKey() + "=" + entries.getValue());
			}
			System.out.println("============================================");
	
		}
		// implement here.....

	}

	 // end processDatafile



	/*  Read patient's symptoms in a line and adds them to the list.
		Input format: Symptom, Symptom, Symptom,...
	    Shows diseases that match a given number of the symptoms. */


	public void processSymptoms()
	{

		// Step 1: get all possible symptoms from symptomChecker map
		//         and display them
		// Step 2: process patient symptoms, add to patientSymptoms list 
		//         read patient's symptoms in a line, separated by ','
		//         create a scanner for symptoms 
		//         UseDelimeter(",") to split into individual symptoms 
		//         make sure to trim() spaces and use toLowercase()
		//         display invalid/duplicate symptoms
		//         add valid symptoms to patientSymptoms list
		// Step 3: display patientSymptoms list
   	        // Step 4: process illnesses to frequency count
		//         create a map of disease name and frequency count
		//         for each symptom in patientSymptoms list
		//              get list of illnesses from symptomChecker map
		//              for each illness in the list
		// 	            if it is already in the map, increase counter by 1
	        //	            if it is not already in the map, create a new counter 1
		//         ** need to keep track of maximum counter numbers
		// Step 5: display result
		//         for count i = 1 to maximum counter number
		//             display illness that has count i
		 

		// implement here.....
		
		System.out.println("\nThe possible symptoms are: ");
		for (Map.Entry<String, List<String>> entry: symptomChecker.entrySet()) {
			String k = entry.getKey();
			System.out.println(k);
		}
		
		Scanner input = new Scanner(System.in);
		System.out.println("\n============================================");
		System.out.print("\nEnter symptoms: ");
		String symptomInput = input.nextLine();
		
		String [] check;
		check = symptomInput.trim().toLowerCase().split(",");
		
		Map<String, Integer> symptomMap = new TreeMap<String, Integer>();
		List<String> patientSymptoms = new ArrayList<>();
	
		for (String cs: check) {
			cs = cs.trim();
			for (Map.Entry<String, List<String>> entries: symptomChecker.entrySet()) {
				String k = entries.getKey();
				if (cs.equals(k)) {
					if (patientSymptoms.contains(cs)) {
						System.out.println("\t=>duplicate symptom: " + cs);
					} else {
						patientSymptoms.add(cs);
						break;
					}
				}
			}
			if (!patientSymptoms.contains(cs)) {
				System.out.println("\t=>invalid symptom: " + cs);
			}
		}
		System.out.println("\n============================================");
		System.out.print("\nPatientSymptoms list: ");
		String PS = Arrays.toString(patientSymptoms.toArray());
		System.out.println(PS);
		
		if (!PS.isEmpty()) {
			System.out.println("\nPossible illnesses that match any symptom are:");
		}
		
		for (String ps: patientSymptoms) {
			List<String> sickness = symptomChecker.get(ps);
			for (String sick: sickness) {
				if (!symptomMap.containsKey(sick)) {
					symptomMap.put(sick, 1);
				} else {
					int frequency = symptomMap.get(sick) + 1;
					symptomMap.put(sick, frequency);
				}
			}
		}
		for (int i = 1; i <= symptomMap.size(); i++) {
			int count = 1;
			for (Entry<String, Integer> entry: symptomMap.entrySet()) {
				int freq = entry.getValue();
				String disease = entry.getKey();
				if (i == freq) {
					if (count == 1) {
						System.out.println("\n==> Disease(s) match " + i + " symptom(s)");
						System.out.println(disease);
						
						count++;
					} else {
						System.out.println(disease);
					}
				}
			}
		}
	} // end processSymptoms 


	public static void main(String[] args)
	{
		PhysiciansHelper lookup = new PhysiciansHelper();
		lookup.processDatafile();
		lookup.processSymptoms();
	} // end main
} // end PhysiciansHelper
