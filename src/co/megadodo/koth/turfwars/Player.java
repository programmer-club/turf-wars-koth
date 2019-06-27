package co.megadodo.koth.turfwars;

public abstract class Player {

    public int x;
    public int y;
    public Team team;
    public PlayerStats stats;

    public Player(int startX, int startY,Team team,PlayerStats stats){
        this.x = startX;
        this.y = startY;
        this.team = team;
        this.stats=stats;
    }

    public abstract String name();
    public abstract Action move(Board board);

}
