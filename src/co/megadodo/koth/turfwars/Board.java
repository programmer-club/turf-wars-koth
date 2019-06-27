package co.megadodo.koth.turfwars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    private class ActionPair{
        Action action;
        int playerX, playerY;
        Player player;

        public ActionPair(Action action, int playerX, int playerY,Player player) {
            this.action = action;
            this.playerX = playerX;
            this.playerY = playerY;
            this.player=player;
        }
    }

    public void runTurn(){
        List<ActionPair>actions=new ArrayList<>();
        for(int x=0;x<width;x++){
            for(int y=0;y<height;y++){
                if(board[x][y].isPlayer()){
                    actions.add(new ActionPair(board[x][y].player.move(this),x,y,board[x][y].player));
                }
            }
        }
        Collections.shuffle(actions);  // Process actions in a random order, this means dupe moves and shoot-eachothers are resolved randomly

        for(ActionPair action : actions){
            // process action
        }


    }

    public boolean isRed(int y){
        return y<dividingLine;
    }

    public boolean isBlue(int y){
        return y>=dividingLine;
    }

    public boolean isRedPlayer(int x,int y){
        return board[x][y].isPlayer()&&isRed(y);
    }

    public boolean isBluePlayer(int x,int y){
        return board[x][y].isPlayer()&&isBlue(y);
    }

    public boolean isRedWool(int x,int y){
        return board[x][y]==CellType.WOOL&&isRed(y);
    }

    public boolean isBlueWool(int x,int y){
        return board[x][y]==CellType.WOOL&&isBlue(y);
    }

}
