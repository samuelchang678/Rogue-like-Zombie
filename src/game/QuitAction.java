package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class QuitAction extends Action {
    private String message;
    public QuitAction(String message){
        this.message = message;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return message;
    }
    @Override
    public String menuDescription(Actor actor) {
        return "Quit Game";
    }
    @Override
    public String hotkey() {
        return "q";
    }
}