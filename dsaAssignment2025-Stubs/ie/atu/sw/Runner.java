package ie.atu.sw;

import java.util.*;
import java.io.File;
import java.util.Scanner;

public class Runner {
	
	public static void main(String[] args) throws Exception {
		String file_location = "";
		String text_file = "";
		String outputfile = ",out.txt";
		int choice = 0;
		
		Scanner key = new Scanner(System.in);
		Menu menu = new Menu();
		String mappingFile = "./encodings-10000.csv";
		EncodeDecoder encoder = new EncodeDecoder(outputfile);
		encoder.EncodingMap("/encodings-10000.csv");		
		encoder.EncodingMap(mappingFile);
		
		// Checks if file exists
		File mappingFileObj = new File(mappingFile);
		if (!mappingFileObj.exists()) {
		    System.out.println("[ERROR] Mapping file not found: " + mappingFile);
		    return;
		}

		System.out.println(ConsoleColour.WHITE);
		System.out.println("************************************************************");
		System.out.println("*       Encoding Words with Suffixes                       *");
		System.out.println("*                                                          *");
		System.out.println("*              Enoch Olaoye                                *");
		System.out.println("*                                                          *");
		System.out.println("************************************************************");

		// main loop
		while (choice != -1) {
			choice = Menu.menu(choice);
			
			if(choice == 1)
			{
				file_location = Menu.filePath(file_location);
				System.out.println("\nDEBUG" + file_location);
			}
			
			else if (choice == 2)
			{
				text_file = Menu.filePath(text_file);
				System.out.println("\nDEBUG: " + text_file);
			}
			
			else if (choice == 3)
			{
				outputfile = Menu.filePath(outputfile);
				
				System.out.println("\nDebug " + outputfile);
			}
			
			else if (choice == 4)
			{
				System.out.println("Configure Options");
			}
			
			else if (choice == 5)
			{
				encoder.encodeFile(file_location , text_file);
				System.out.println("Encoding complete Output saved to " + outputfile);
			}
			
			else if (choice == 6)
			{
				encoder.decodeFile(file_location , text_file);
				System.out.println("Decoding complete Output saved to " + outputfile);
			}
			
			else
			{
				System.out.println("Invaild try again");
			}
			// Output a menu of options and solicit text from the user
			System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
			System.out.println("(1) Specify Mapping File");
			System.out.println("(2) Specify Text File to Encode");
			System.out.println("(3) Specify Output File (default: ./out.txt)");
			System.out.println("(4) Configure Options");
			System.out.println("(5) Encode Text File");
			System.out.println("(6) Decode Text File");
			System.out.println("(?) Optional Extras...");
			System.out.println("(?) Quit");
			System.out.print("Select Option [1-?]>");
			System.out.println();
		}
		// You may want to include a progress meter in you assignment!
		System.out.print(ConsoleColour.GREEN); // Change the colour of the console text
		int size = 100; // The size of the meter. 100 equates to 100%
		for (int i = 0; i < size; i++) { // The loop equates to a sequence of processing steps
			printProgress(i + 1, size); // After each (some) steps, update the progress meter
			Thread.sleep(10); // Slows things down so the animation is visible
		}

	}

	
	public static void printProgress(int index, int total) {
		if (index > total)
			return; // Out of range
		int size = 50; // Must be less than console width
		char done = '█'; // Change to whatever you like.
		char todo = '░'; // Change to whatever you like.

		// Compute basic metrics for the meter
		int complete = (100 * index) / total;
		int completeLen = size * complete / 100;

		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < size; i++) {
			sb.append((i < completeLen) ? done : todo);
		}

		System.out.print("\r" + sb + "] " + complete + "%");

		// Once the meter reaches its max, move to a new line.
		if (done == total)
			System.out.println("\n");
	}
}