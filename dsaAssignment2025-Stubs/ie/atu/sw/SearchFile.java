package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
//import java.io.IOException;
import java.io.InputStreamReader;
//import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class SearchFile {
	private static List<String> words = new ArrayList<>();
	private static List<String> numbers = new ArrayList<>();
	static String EncodeFile;

	public static void readingEncodingFile() throws Exception {
		EncodeFile = "./encodings-10000.csv";

		try (var br = new BufferedReader(new InputStreamReader(new FileInputStream(EncodeFile)))) {
			String next;
			while ((next = br.readLine()) != null) {
				words.add(next);
			}

			while ((next = br.readLine()) != null) {
				numbers.add(next);
			}
		}

		catch (Exception exception) {
			throw new Exception("Cannot DEBUG");
		}

		System.out.println(words);

	}

	public static void main(String[] args) throws Exception {
		EncodeDecoder encoder = new EncodeDecoder(EncodeFile);
		readingEncodingFile(); // Load the encoding map

		try {
			// Load the encoding map (make sure the path to your CSV is correct)
			encoder.EncodingMap("./encodings-10000.csv");

			// Test encoding a line
			String testLine = "hello world goodbye";
			List<Integer> encoded = encoder.encodeLine(testLine);
			System.out.println("Encoded: " + encoded);

			// Test decoding the line
			String decoded = encoder.decodeLine(encoded);
			System.out.println("Decoded: " + decoded);

			// Optionally, test file encoding/decoding
			 encoder.encodeFile("input.txt", "encoded_output.txt");
			 encoder.decodeFile("encoded_output.txt", "decoded_output.txt");

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

}
