package co.megadodo.koth.turfwars;

public class Board {

    public int dividingLine;
    public static final int height = 50;
    public static final int width=50;

    public Board(){
        assert height % 2 == 0;
        dividingLine=height/2;
    }

}
