package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class serializer {
	public static void deserializeObject(String pathString) {
		try(var os = new ObjectInputStream(new FileInputStream(pathString))) {
			var object = os.readObject();
			System.out.println(object);
		} catch (FileNotFoundException e) {
			System.err.printf("File not found: %s \n", pathString);
		} catch (IOException e) {
			System.err.printf("Error reading file: %s \n", pathString);
		} catch (ClassNotFoundException e) {
			System.err.printf("Cannot read object from file: %s \n", pathString);
		}
		System.out.println("deserializeObject Completed! \n");
	}
	
	public static void serializeObject(Object obj, String pathString) {		
		try(var os = new ObjectOutputStream(new FileOutputStream(pathString))) {
			os.writeObject(obj);
		} catch (FileNotFoundException e) {
			System.err.printf("File not found: %s \n", pathString);
		} catch (IOException e) {
			System.err.printf("Error wrting file: %s \n", pathString);
		}
		System.out.println("serializeObject Completed! \n");
	}

}
