package test;
import models.World;

public class CheckLogic {
	public static void main(String[] args) {
		World world = new World(4, 7);
		world.countNeighbours(0, 0);
		world.countNeighbours(4, 7);
	}
}
