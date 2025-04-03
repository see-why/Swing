package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

public class Serializer {
	private static final String VALID_EXTENSION = ".gol";
	private static void showError(String message) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}

	
	private static void validateFormat(String pathString) throws IllegalArgumentException {
		if (pathString == null || pathString.isEmpty()) {
			throw new IllegalArgumentException("File path cannot be null or empty");
		}
		if (!pathString.toLowerCase().endsWith(VALID_EXTENSION)) {
			throw new IllegalArgumentException("Invalid file format. Must end with " + VALID_EXTENSION);
		}
	}

	public static Object deserializeObject(String pathString) {
		Object result = null;
		try {
			validateFormat(pathString);
			try(var os = new ObjectInputStream(new FileInputStream(pathString))) {
				result = os.readObject();
			}
		}
		catch (IllegalArgumentException e) {
			showError(e.getMessage());
		}
		catch (FileNotFoundException e) {
			showError("File not found: " + pathString);
		} catch (IOException e) {
			showError("Error reading file: " + pathString + "\n" + e.getMessage());
		} catch (ClassNotFoundException e) {
			showError("Cannot read object from file: " + pathString + "\nInvalid file format");
		}
		return result;
	}
	
	public static void serializeObject(Object obj, String pathString) {
		try {
			validateFormat(pathString);
			try(var os = new ObjectOutputStream(new FileOutputStream(pathString))) {
				os.writeObject(obj);
			}
		}
		catch (IllegalArgumentException e) {
			showError(e.getMessage());
		}
		catch (FileNotFoundException e) {
			showError("File not found or format wrong: " + pathString);
		} catch (IOException e) {
			showError("Error writing file: " + pathString + "\n" + e.getMessage());
		}
	}
}
