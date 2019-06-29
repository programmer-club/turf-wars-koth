package co.megadodo.koth.turfwars;

public abstract class Player {

    public int x;
    public int y;
    public Team team;
    public PlayerStats initial_stats;

    public int blocks, ammo;
    public double health;

    public Player(int startX, int startY,Team team,PlayerStats initial_stats){
        this.x = startX;
        this.y = startY;
        this.team = team;
        this.initial_stats=initial_stats;

        blocks=initial_stats.startBlocks();
        ammo=initial_stats.startAmmo();
        health=initial_stats.health();
    }

    public abstract String name();
    public abstract Action move(Board board);

}
