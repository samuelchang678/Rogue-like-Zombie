package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;

/**
 * The main class for the zombie apocalypse game.
 *
 */
public class Application {

	public static void main(String[] args) {
		GameWorld world = new GameWorld(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Fence(), new Tree());
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		"....................................##########..................................",
		"..........................###########........#####..............................",
		"............++...........##......................########.......................",
		"..............++++.......#..............................##......................",
		".............+++...+++...#...............................#......................",
		"..........#########......##..............................##.....................",
		"..........#.......#.......#...............................#.....................",
		"..........#.......#......##...............................##....................",
		"..........#.......#......#...............................##.....................",
		"..........#.......#......###..............................##....................",
		"..........#.......#........####......................######.....................",
		"..........#.......#...........#########.........####............................",
		"..........#.......#...................#.........#...............................",
		"..........#########...................#.........#...............................",
		"...............++........................................+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		".........................................................................++.....",
		"........................................................................++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");
		GameMap gameMap = new ZombieGameMap(groundFactory, map );
		world.addGameMap(gameMap);

		FancyGroundFactory newGroundFactory = new FancyGroundFactory(new Dirt(), new Fence(), new Tree());
		
		List<String> newMap = Arrays.asList(
		".....",
		".....",
		".....",
		".....");
		GameMap newGameMap = new GameMap(newGroundFactory, newMap );
		world.addGameMap(newGameMap);

		gameMap.at(52,22).setGround(new Crop("broken"));	
		gameMap.at(0, 0).setGround(new Vehicle(newGameMap,0,0));
		newGameMap.at( 0,0).setGround(new Vehicle(gameMap,0,0));

		newGameMap.at(4, 3).addItem(new Sniper("sniper"));
		newGameMap.at(3, 2).addItem(new Shotgun("Shottie"));
		newGameMap.at(2, 3).addItem(new Ammunition("ammo",5));
		newGameMap.at(3, 3).addItem(new Ammunition("ammo",5));




		// Place some random humansb
		
		String[] humans = {"Carlton", "May", "Vicente", "Andrea", "Wendy",
				"Elina", "Winter", "Clem", "Jacob", "Jaquelyn"};
		int x, y;
		for (String name : humans) {
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			} 
			while (gameMap.at(x, y).containsAnActor());
			gameMap.at(x,  y).addActor(new Human(name));
		}
		Actor player = new Player("Player", '@', 100);
		world.addPlayer(player, gameMap.at(5, 5));
		gameMap.at(5,5).addItem(new Shotgun("shottie") );
		gameMap.at(26,12).addItem(new Hand("broken"));
		gameMap.at(5,5).addItem(new Sniper("niper"));
		// gameMap.at(5,6).addActor(new Zombie("human14"));//southeast


		gameMap.at(11,13).addItem(new Ammunition("ammo", 5));
		
		gameMap.at(11,8).addActor(new Zombie("human14"));//southeast
		gameMap.at(12,8).addActor(new Zombie("human14"));
		gameMap.at(13,8).addActor(new Zombie("human14"));
		gameMap.at(14,8).addActor(new Zombie("human14"));//south  
		gameMap.at(15,8).addActor(new Zombie("human14"));
		gameMap.at(16,8).addActor(new Zombie("human14"));
		gameMap.at(17,8).addActor(new Zombie("human14"));//southwest

		gameMap.at(11,9).addActor(new Zombie("human14"));
		gameMap.at(12,9).addActor(new Zombie("human14"));
		gameMap.at(13,9).addActor(new Zombie("human14"));
		gameMap.at(14,9).addActor(new Zombie("human14"));
		gameMap.at(15,9).addActor(new Zombie("human14"));
		gameMap.at(16,9).addActor(new Zombie("human14"));
		gameMap.at(17,9).addActor(new Zombie("human14"));

		gameMap.at(11,10).addActor(new Zombie("human14"));
		gameMap.at(12,10).addActor(new Zombie("human14"));
		gameMap.at(13,10).addActor(new Zombie("human14"));
		gameMap.at(14,10).addActor(new Zombie("human14"));
		gameMap.at(15,10).addActor(new Zombie("human14"));
		gameMap.at(16,10).addActor(new Zombie("human14"));
		gameMap.at(17,10).addActor(new Zombie("human14"));

		gameMap.at(11,11).addActor(new Zombie("human14"));//east
		gameMap.at(12,11).addActor(new Zombie("human14"));
		gameMap.at(13,11).addActor(new Zombie("human14"));
		gameMap.at(14,11).addActor(new Zombie("human14"));//center 
		gameMap.at(15,11).addActor(new Zombie("human14"));
		gameMap.at(16,11).addActor(new Zombie("human14"));
		gameMap.at(17,11).addActor(new Zombie("human14"));//west

		gameMap.at(11,12).addActor(new Zombie("human14"));
		gameMap.at(12,12).addActor(new Zombie("human14"));
		gameMap.at(13,12).addActor(new Zombie("human14"));
		gameMap.at(14,12).addActor(new Zombie("human14"));
		gameMap.at(15,12).addActor(new Zombie("human14"));
		gameMap.at(16,12).addActor(new Zombie("human14"));
		gameMap.at(17,12).addActor(new Zombie("human14"));

		gameMap.at(11,13).addActor(new Zombie("human14"));
		gameMap.at(12,13).addActor(new Zombie("human14"));
		gameMap.at(13,13).addActor(new Zombie("human14"));
		gameMap.at(14,13).addActor(new Zombie("human14"));
		gameMap.at(15,13).addActor(new Zombie("human14"));
		gameMap.at(16,13).addActor(new Zombie("human14"));
		gameMap.at(17,13).addActor(new Zombie("human14"));

		gameMap.at(11,14).addActor(new Zombie("human14"));//northeast
		gameMap.at(12,14).addActor(new Zombie("human14"));
		gameMap.at(13,14).addActor(new Zombie("human14"));
		gameMap.at(14,14).addActor(new Zombie("human14"));//north  
		gameMap.at(15,14).addActor(new Zombie("human14"));
		gameMap.at(16,14).addActor(new Zombie("human14"));
		gameMap.at(17,14).addActor(new Zombie("human14"));//northwest
		
		

		

		// place a simple weapon
		gameMap.at(74, 20).addItem(new Plank());
		
		// FIXME: Add more zombies!
		// gameMap.at(30, 20).addActor(new Zombie("Groan"));
		// gameMap.at(30,  18).addActor(new Zombie("Boo"));
		// gameMap.at(10,  4).addActor(new Zombie("Uuuurgh"));
		// gameMap.at(50, 18).addActor(new Zombie("Mortalis"));
		// gameMap.at(1, 10).addActor(new Zombie("Gaaaah"));
		// gameMap.at(62, 12).addActor(new Zombie("Aaargh"));	
		world.run();
	}
}
