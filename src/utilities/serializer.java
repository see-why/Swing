package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

public class serializer {
    private static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static Object deserializeObject(String pathString) {
        Object result = null;
        try(var os = new ObjectInputStream(new FileInputStream(pathString))) {
            result = os.readObject();
        } catch (FileNotFoundException e) {
            showError("File not found: " + pathString);
        } catch (IOException e) {
            showError("Error reading file: " + pathString + "\n" + e.getMessage());
        } catch (ClassNotFoundException e) {
            showError("Cannot read object from file: " + pathString + "\nInvalid file format");
        }
        return result;
    }
    
    public static void serializeObject(Object obj, String pathString) {        
        try(var os = new ObjectOutputStream(new FileOutputStream(pathString))) {
            os.writeObject(obj);
        } catch (FileNotFoundException e) {
            showError("File not found: " + pathString);
        } catch (IOException e) {
            showError("Error writing file: " + pathString + "\n" + e.getMessage());
        }
    }
}
