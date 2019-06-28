package co.megadodo.koth.turfwars;

import java.util.ArrayList;
import java.util.List;

public class PlayerRandom extends Player {

    public PlayerRandom(int startX, int startY, Team team) {
        super(startX, startY, team, new PlayerStats(5,5,4,5,5,5));
    }

    @Override
    public String name() {
        return "Random";
    }

    @Override
    public Action move(Board board) {
        List<Action>actions=new ArrayList<>();
        actions.add(new ActionPlace(-1,0));
        actions.add(new ActionPlace(1,0));
        actions.add(new ActionPlace(0,-1));
        actions.add(new ActionPlace(0,1));
        actions.add(new ActionDestroy(-1,0));
        actions.add(new ActionDestroy(1,0));
        actions.add(new ActionDestroy(0,-1));
        actions.add(new ActionDestroy(0,1));
        return actions.get((int)(Math.random()*actions.size()));
    }

}
