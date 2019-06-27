package co.megadodo.koth.turfwars;

public class ActionShoot implements Action {

    public int count;
    public ActionShoot(PlayerStats stats){
        count=stats.shotCount();
    }

    public double calculateDamage(){
        return (5.0 - count) / 2.0;
    }

}
