package co.megadodo.koth.turfwars;

public class Board {

    public int dividingLine;
    public static final int height = 50;
    public static final int width=50;

    public CellType[][] board;

    // red = y<dividingLine
    // blue = y>=dividingLine

    public Board(){
        assert height % 2 == 0;
        dividingLine=height/2;

        board=new CellType[width][height];
        for(int x=0;x<width;x++){
            for(int y=0;y<height;y++){
                board[x][y]=CellType.EMPTY;
            }
        }
    }

    public boolean isRed(int y){
        return y<dividingLine;
    }

    public boolean isBlue(int y){
        return y>=dividingLine;
    }

    public boolean isRedPlayer(int x,int y){
        return board[x][y]==CellType.PLAYER&&isRed(y);
    }

    public boolean isBluePlayer(int x,int y){
        return board[x][y]==CellType.PLAYER&&isBlue(y);
    }

    public boolean isRedWool(int x,int y){
        return board[x][y]==CellType.WOOL&&isRed(y);
    }

    public boolean isBlueWool(int x,int y){
        return board[x][y]==CellType.WOOL&&isBlue(y);
    }

}
