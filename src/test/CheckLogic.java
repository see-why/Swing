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
import java.util.ArrayList;
import models.World;

public class CheckLogic {
	public static void main(String[] args) throws IOException {
		var list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);

		System.out.println(list.get(0));
		System.out.println(list.get(1));
		System.out.println(list.get(2));

		World world = new World(4, 7);
		world.countNeighbours(0, 0);
		world.countNeighbours(4, 7);
		System.out.println(world);
		
		String pathString = "text.bin";
		serializeObject(world, pathString);
		deserializeObject(pathString);
		
		File currentDirectory = new File(".");
		printDirectory(currentDirectory);

		Path path = Paths.get("text.txt");
		writeLines(path);
		readLines(path);

		String fileLocation = "/Users/ocyril/Documents/eclipse-workspace/Swing/text.txt";
		System.out.println(new File(fileLocation).exists());
		readLineByLine(fileLocation);
		
		String newFileLocation = "/Users/ocyril/Documents/eclipse-workspace/Swing/newText.txt";
		writeLineByLine(newFileLocation);
		
		pathString = "newText.bin";
		writeBinaryData(pathString);
		readBinaryData(pathString, false);
		readBinaryData("./bin/application/App.class", true);
		parseCsv("text.csv");
	}

	private static void parseCsv(String filePath) {
		if(! new File(filePath).exists()) {
			return;
		}
		int maxAge = Integer.MIN_VALUE;
		int minAge = Integer.MAX_VALUE;
		int sumAge = 0;
		int count = 0;
		
		try(var bf = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = bf.readLine()) != null) {
				String[] row = line.split(",");

				if (row.length != 4) {
					System.err.println("Line doesn't contain 4 fields" + line);
					continue;
				}
				int age = Integer.parseInt(row[2]);

				if (age < minAge) {
					minAge = age;
				}

				if (age > maxAge) {
					maxAge = age;
				}
				sumAge += age;
				count++;
			}
			if (count == 0) {
				System.err.println("No lines in the file");
				return;
			}
		} catch (FileNotFoundException e) {
			System.err.printf("File not found: %s \n", filePath);
		} catch (IOException e) {
			System.err.printf("Error reading file: %s \n", filePath);
		}

		System.out.printf("Minimum age: %d \n", minAge);
		System.out.printf("Maximum age: %d \n", maxAge);
		System.out.printf("Average age: %f \n", sumAge / (double)count);
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
		System.out.println("readBinaryData Completed! \n");
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
		System.out.println("writeBinaryData Completed! \n");
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
		System.out.println("deserializeObject Completed! \n");
	}
	
	private static void serializeObject(Object obj, String pathString) {		
		try(var os = new ObjectOutputStream(new FileOutputStream(pathString))) {
			os.writeObject(obj);
		} catch (FileNotFoundException e) {
			System.err.printf("File not found: %s \n", pathString);
		} catch (IOException e) {
			System.err.printf("Error wrting file: %s \n", pathString);
		}
		System.out.println("serializeObject Completed! \n");
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
		System.out.println();
	}
	
	private static void writeLines(Path path) {
		String text = "It's\nbeen\na\nwhile.";
		try {
			Files.write(path, text.getBytes());
		} catch (IOException e) {
			System.err.printf("File not found: %s \n", path.toAbsolutePath());
		}
		System.out.println("writeLines Completed! \n");
	}
	
	private static void readLines(Path path) {		
		String readText;
		try {
			readText = Files.readString(path);
			System.out.println(readText);
		} catch (IOException e) {
			System.err.printf("File not found: %s \n", path.toAbsolutePath());
		}
		System.out.println();
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
		System.out.println();
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
		System.out.println("writeLineByLine Completed! \n");
	}
}
