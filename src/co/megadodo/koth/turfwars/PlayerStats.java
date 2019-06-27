package co.megadodo.koth.turfwars;

public class PlayerStats {

    private int startBlocks, startAmmo, shotCount, range;
    private double health, regenPerTurn;

    public PlayerStats(int startBlocks, int startAmmo, int shotCount, int range, double health, double regenPerTurn) {
        this.startBlocks = startBlocks;
        this.startAmmo = startAmmo;
        this.shotCount = shotCount;
        this.range = range;

        this.health=health;
        this.regenPerTurn=regenPerTurn;
    }

    public int startBlocks(){return startBlocks;}
    public int startAmmo(){return startAmmo;}
    public int shotCount(){return shotCount;}
    public int range(){return range;}
    public double health(){return health;}
    public double regenPerTurn(){return regenPerTurn;}

}
