package co.megadodo.koth.turfwars;

public class CellType {

    public static final CellType EMPTY = new CellType();
    public static final CellType WOOL = new CellType();

    public Player player=null;

    public PlayerStats playerStats(){
        if(player==null)return null;
        return player.stats;
    }

    private CellType(){

    }

    public static CellType PLAYER(Player player){
        CellType ct=new CellType();
        ct.player=player;
        return ct;
    }

    public boolean isPlayer(){
        return player!=null;
    }

}
