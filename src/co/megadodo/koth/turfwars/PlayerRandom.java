package co.megadodo.koth.turfwars;

public class PlayerRandom extends Player {

    public PlayerRandom(int startX, int startY, Team team, PlayerStats stats) {
        super(startX, startY, team, stats);
    }

    @Override
    public String name() {
        return "Random";
    }

    @Override
    public Action move(Board board) {
        return new ActionMove(-1,0);
    }

}
