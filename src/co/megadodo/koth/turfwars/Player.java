package co.megadodo.koth.turfwars;

public abstract class Player {

    public int x;
    public int y;
    public Team team;

    public Player(int startX, int startY,Team team){
        this.x = startX;
        this.y = startY;
        this.team = team;
    }

}
