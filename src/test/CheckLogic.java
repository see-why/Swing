package test;
import java.io.File;
import java.io.IOException;
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
		
		File currentDirectory = new File(".");
		System.out.println(currentDirectory.getAbsolutePath());
		System.out.println(currentDirectory.getCanonicalPath());
		System.out.println();
		
		for(String f: currentDirectory.list()) {
			System.out.println(f);
		}
		System.out.println();

		String text = "It's\nbeen\na\nwhile.";
		Path path = Paths.get("text.txt");
		Files.write(path, text.getBytes());
		
		String readText = Files.readString(path);
		System.out.println(readText);
		System.out.println();

		String fileLocation = "/Users/ocyril/Documents/eclipse-workspace/Swing/text.txt";
		System.out.println(new File(fileLocation).exists());
	}
}
