package test;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import models.World;

public class CheckLogic {
	public static void main(String[] args) throws IOException {
		World world = new World(4, 7);
		world.countNeighbours(0, 0);
		world.countNeighbours(4, 7);
		System.out.println();
		
		String pathString = "text.bin";
		serializeObject(world, pathString);
		
		File currentDirectory = new File(".");
		printDirectory(currentDirectory);
		System.out.println();

		Path path = Paths.get("text.txt");
		writeLines(path);
		
		readLines(path);
		System.out.println();

		String fileLocation = "/Users/ocyril/Documents/eclipse-workspace/Swing/text.txt";
		System.out.println(new File(fileLocation).exists());
		System.out.println();

		readLineByLine(fileLocation);
		
		String newFileLocation = "/Users/ocyril/Documents/eclipse-workspace/Swing/newText.txt";
		writeLineByLine(newFileLocation);
		System.out.println();
	}
	
	private static void serializeObject(Object obj, String pathString) {		
		try(var os = new ObjectOutputStream(new FileOutputStream(pathString))) {
			
		} catch (FileNotFoundException e) {
			System.err.printf("File not found: %s \n", pathString);
		} catch (IOException e) {
			System.err.printf("Error wrting file: %s \n", pathString);
		}
	}
	
	private static void printDirectory(File currentDirectory) {
		System.out.println(currentDirectory.getAbsolutePath());
		try {
			System.out.println(currentDirectory.getCanonicalPath());
		} catch (IOException e) {
			System.err.printf("File not found: %s \n", currentDirectory.getAbsolutePath());
		}
		System.out.println();
		
		for(String f: currentDirectory.list()) {
			System.out.println(f);
		}
	}
	
	private static void writeLines(Path path) {
		String text = "It's\nbeen\na\nwhile.";
		try {
			Files.write(path, text.getBytes());
		} catch (IOException e) {
			System.err.printf("File not found: %s \n", path.toAbsolutePath());
		}
	}
	
	private static void readLines(Path path) {		
		String readText;
		try {
			readText = Files.readString(path);
			System.out.println(readText);
		} catch (IOException e) {
			System.err.printf("File not found: %s \n", path.toAbsolutePath());
		}
	}
	
	private static void readLineByLine(String fileLocation) {
		try(BufferedReader reader = new BufferedReader(new FileReader(fileLocation))) {
			String line;
			while((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			
		} catch (FileNotFoundException e) {
			System.err.printf("File not found: %s \n", fileLocation);
		}
		catch (IOException e) {
			System.err.printf("Error reading file: %s \n", fileLocation);
		}
		
	}
	
	private static void writeLineByLine(String fileLocation) {
		try(BufferedWriter reader = new BufferedWriter(new FileWriter(fileLocation))) {
			reader.write("Since\n");
			reader.write("I've gone\n");
			reader.write("and f**cked\n");
			reader.write("thing up\n");
			reader.write("Like I always do.\n");
			
		} catch (FileNotFoundException e) {
			System.err.printf("File not found: %s \n", fileLocation);
		}
		catch (IOException e) {
			System.err.printf("Error wrting file: %s \n", fileLocation);
		}
	}
}
