package ie.atu.sw;

import java.io.*;
import java.util.*;

public class EncodeDecoder {

	// Maps to hold encoding and decoding mappings
    private final Map<String, Integer> encodingMap = new HashMap<>();
    private final Map<Integer, String> decodingMap = new HashMap<>();
    //EncodeDecoder encoderDecoder = new EncodeDecoder("output.txt");
    private String outputFilePath;
    
    // Constructor to set the output file path
    public EncodeDecoder(String outputfile) {
        this.outputFilePath = outputfile;
    }

    // Load encoding and decoding map from CSV
    public void EncodingMap(String filePath) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String word = parts[0].trim();
                    int value = Integer.parseInt(parts[1].trim());
                    encodingMap.put(word, value);
                    decodingMap.put(value, word);
                }
            }
        } catch (IOException e) {
            throw new Exception("ERROR Cannot read the file: " + e.getMessage());
        }
    }

    // Encode a line of text
    public List<Integer> encodeLine(String line) {
        List<Integer> encoded = new ArrayList<>();
        String[] words = line.split("\\s+");

        for (String word : words) {
            Integer encodedValue = encodingMap.get(word);
            if (encodedValue != null) {
                encoded.addAll(encodeLine(word));
            } else {
                encoded.add(0); 
            }
        }
        System.out.println("Encoded line: " + encoded);
        return encoded;
    }

    // Decode a list of codes back into a line of text
    public String decodeLine(List<Integer> codes) {
        StringBuilder decoded = new StringBuilder();

        for (Integer code : codes) {
            String word = decodingMap.getOrDefault(code, "[???]"); // Default if code is not found
            decoded.append(word).append(" ");
        }

        return decoded.toString().trim();
    }

    // Encode the contents of a file
    public void encodeFile(String inputPath, String outputPath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {

            String line;
            while ((line = reader.readLine()) != null) // Read each line from the input file 
            	{
                List<Integer> encoded = encodeLine(line);
                String encodedLine = encoded.toString().replaceAll("[\\[\\],]", ""); // Clean formatting
                writer.write(encodedLine);
                writer.newLine();
            }
        }
    }

    // Decode the contents of a file and write to an output file
    public void decodeFile(String inputPath, String outputPath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                List<Integer> codes = new ArrayList<>();
                for (String part : parts) {
                    try {
                        codes.add(Integer.parseInt(part)); 
                    } catch (NumberFormatException e) {
                        codes.add(0); 
                    }
                }
                String decodedLine = decodeLine(codes);
                writer.write(decodedLine);
                writer.newLine();
            }
            
            System.out.println("");
        }
    }

    //Main method to demonstrate usage of EncodeDecoder
    public static void main(String[] args) throws Exception {
        // Example usage of EncodeDecoder class
        EncodeDecoder encoderDecoder = new EncodeDecoder("output.txt");

        // Load the encoding map
       encoderDecoder.EncodingMap("./encodings-10000.csv");

        // Example: Encode a file
       encoderDecoder.encodeFile("input.txt", "encoded_output.txt");
       //encoderDecoder.encodeFile("input.txt", null);
        
        // Example: Decode a file
       encoderDecoder.decodeFile("encoded_output.txt", "decoded_output.txt");
       //encoderDecoder.decodeFile("encoded_output.txt", null); 
       
       System.out.println("Done! Output written to: " + encoderDecoder.outputFilePath);
    }
}
