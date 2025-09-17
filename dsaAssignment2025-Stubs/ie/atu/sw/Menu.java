package ie.atu.sw;

import java.util.Scanner;

public class Menu {
	public static int menu(int choice) throws Exception
	{
		Scanner scan = new Scanner(System.in);
		
		System.out.println(ConsoleColour.WHITE);
		System.out.println("(1) Specify Mapping File");
		System.out.println("(2) Specify Text File to Encode");
		System.out.println("(3) Specify Output File (default: ./out.txt)");
		System.out.println("(4) Configure Options");
		System.out.println("(5) Encode Text File");
		System.out.println("(6) Decode Text File");
		System.out.println("(?) Optional Extras...");
		System.out.println("(?) Quit");
		
		System.out.println(ConsoleColour.YELLOW_BOLD);
		System.out.println("Select Option 1-6 or -1 to exit ");
		return choice = scan.nextInt();
	}

	public static String filePath(String file_location) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println(ConsoleColour.BLUE);
		
		System.out.println("Enter the file you want to encode/decode");
		file_location = scanner.next();
		
		return file_location;
	}
	
//	public static void String (String textfile)
	//{
		
	//}

	
	public static int menus(int currentChoice) {
	    // Display the menu options
	    Scanner keyboard = new Scanner(System.in);
	    System.out.print("Select Option [1-?]: ");
	    return keyboard.nextInt(); // Get user input
	}
}
