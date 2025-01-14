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
		
		File currentDirectory = new File(".");
		System.out.println(currentDirectory.getAbsolutePath());
		System.out.println(currentDirectory.getCanonicalPath());
		
		for(String f: currentDirectory.list()) {
			System.out.println(f);
		}
		
		String text = "It's\nbeen\na\nwhile.";
		Path path = Paths.get("text.txt");
		Files.write(path, text.getBytes());
		
		String readText = Files.readString(path);
		System.out.println(readText);
	}
}
