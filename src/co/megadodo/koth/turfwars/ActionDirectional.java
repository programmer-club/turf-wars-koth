package co.megadodo.koth.turfwars;

public class ActionDirectional implements Action {

    private int dx;
    private int dy;

    public ActionDirectional(int dx,int dy){
        this.dx=dx;
        this.dy=dy;
    }

    public int dx(){
        return dx;
    }

    public int dy(){
        return dy;
    }

}
