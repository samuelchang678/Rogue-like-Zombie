package game;

import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.GroundFactory;

public class ZombieGameMap extends GameMap{
    /**
     * randomizer
     */
    private Random rand = new Random();
    /**
     * a mambo marie to be spawn later
     */
    private MamboMarie priest;
    /**
     * Constructor for ZombieGameMap subclassed from GameMap
     * @param groundFactory 
     * @param lines
     */
    public ZombieGameMap(GroundFactory groundFactory, List<String>lines){
        super(groundFactory, lines);
        this.priest = new MamboMarie("Voodoo priest");
    }
    /**
     * override the tick function
     * spawn priest to the map with a 5 % chance if the priest is not on map and priest is not dead
     */
    @Override
    public void tick() {
        if (priest.isConscious() && !contains(priest) && rand.nextDouble() <0.05){
            int x []  = {getXRange().min() ,
                getXRange().max()};
            int y [] = {getYRange().max(),getYRange().max()};
            int xi = rand.nextInt(x.length);
            int yi = rand.nextInt(y.length);
            if (!at(xi,yi).containsAnActor()){
                at(xi,yi).addActor(priest);
            }
        }
        super.tick();
    }
}