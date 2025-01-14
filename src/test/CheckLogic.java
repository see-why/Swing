package test;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
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
		System.out.println(world);
		System.out.println();
		
		String pathString = "text.bin";
		serializeObject(world, pathString);
		System.out.println();
		
		deserializeObject(pathString);
		System.out.println();
		
		File currentDirectory = new File(".");
		printDirectory(currentDirectory);
		System.out.println();

		Path path = Paths.get("text.txt");
		writeLines(path);
		System.out.println();
		
		readLines(path);
		System.out.println();

		String fileLocation = "/Users/ocyril/Documents/eclipse-workspace/Swing/text.txt";
		System.out.println(new File(fileLocation).exists());
		System.out.println();

		readLineByLine(fileLocation);
		System.out.println();
		
		String newFileLocation = "/Users/ocyril/Documents/eclipse-workspace/Swing/newText.txt";
		writeLineByLine(newFileLocation);
		System.out.println();
		
		pathString = "newText.bin";
		writeBinaryData(pathString);
		System.out.println();
		readBinaryData(pathString, false);
		System.out.println();
		readBinaryData("./bin/application/App.class", true);
	}
	
	private static void readBinaryData(String pathString, boolean isJavaFile) {
		try(var dis = new DataInputStream(new FileInputStream(pathString))) {
			if(isJavaFile) {
				int magicNumber = dis.readInt();
				int minorVersion = dis.readUnsignedShort();
				int majorVersion = dis.readUnsignedShort();
				System.out.println(String.format("%02X", magicNumber));
				System.out.println(String.format("major version: %d, minor version: %d", majorVersion, minorVersion));
			} else {
				byte byte1 = dis.readByte();
				byte byte2 = dis.readByte();
				byte byte3 = dis.readByte();
				byte byte4 = dis.readByte();

				System.out.println(byte1 + "," + byte2 + "," + byte3 + "," + byte4);
			}
		} catch (FileNotFoundException e) {
			System.err.printf("File not found: %s \n", pathString);
		} catch (IOException e) {
			System.err.printf("Error reading file: %s \n", pathString);
		}
		System.out.println("readBinaryData Completed!");
	}
	
	private static void writeBinaryData(String pathString) {
		try(var dos = new DataOutputStream(new FileOutputStream(pathString))) {
			File file = new File(pathString);
			System.out.printf("File size: %d\n", file.length());

			byte[] arr = new byte[2];
			arr[0] = 2;
			arr[1] = 3;
			byte[] newArr = new byte[2];

			dos.write(arr);
			dos.write(newArr);
			
			System.out.printf("File size now: %d\n", file.length());
		} catch (FileNotFoundException e) {
			System.err.printf("File not found: %s \n", pathString);
		} catch (IOException e) {
			System.err.printf("Error writing file: %s \n", pathString);
		}
		System.out.println("writeBinaryData Completed!");
	}
	
	private static void deserializeObject(String pathString) {
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
		System.out.println("deserializeObject Completed!");
	}
	
	private static void serializeObject(Object obj, String pathString) {		
		try(var os = new ObjectOutputStream(new FileOutputStream(pathString))) {
			os.writeObject(obj);
		} catch (FileNotFoundException e) {
			System.err.printf("File not found: %s \n", pathString);
		} catch (IOException e) {
			System.err.printf("Error wrting file: %s \n", pathString);
		}
		System.out.println("serializeObject Completed!");
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
		System.out.println("writeLines Completed!");
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
		System.out.println("writeLineByLine Completed!");
	}
}
