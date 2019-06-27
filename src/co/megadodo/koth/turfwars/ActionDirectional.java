package co.megadodo.koth.turfwars;

public class ActionDirectional implements Action {

    private int x;
    private int y;

    public ActionDirectional(int x,int y){
        this.x=x;
        this.y=y;
    }

    public int x(){
        return x;
    }

    public int y(){
        return y;
    }

}
