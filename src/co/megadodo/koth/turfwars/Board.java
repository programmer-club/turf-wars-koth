package co.megadodo.koth.turfwars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {

    public int dividingLine;
    public static final int height = 50;
    public static final int width=50;

    public CellType[][] board;

    public int turnNumber = 0;

    // red = y<dividingLine
    // blue = y>=dividingLine

    private interface PlayerInstantiator{
        Player instantiate(int x,int y,Team team);
    }

    public Board(){
        assert height % 2 == 0;
        dividingLine=height/2;

        board=new CellType[width][height];
        for(int x=0;x<width;x++){
            for(int y=0;y<height;y++){
                board[x][y]=CellType.EMPTY;
            }
        }

        setupPlayers(new PlayerInstantiator[]{
                PlayerRandom::new,
        });
    }

    private void setupPlayers(PlayerInstantiator[]instantiators){
        for(int i=0;i<instantiators.length;i++){
            int x_red=(int)(Math.random()*width);
            int x_blue=(int)(Math.random()*width);
            int y_red=2;
            int y_blue=height-y_red-1;
            board[x_red][y_red]=CellType.PLAYER(instantiators[i].instantiate(x_red,y_red,Team.RED));
            board[x_blue][y_blue]=CellType.PLAYER(instantiators[i].instantiate(x_blue,y_blue,Team.BLUE));
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
        turnNumber++;

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
            processAction(action);
        }
    }

    private CellType get(int x,int y){
        if(x<0||y<0||x>=width||y>=height)return CellType.EMPTY;
        return board[x][y];
    }

    private void set(int x,int y,CellType c){
        if(x<0||y<0||x>=width||y>=height)return;
        board[x][y]=c;
    }

    private void processAction(ActionPair pair){
        if(pair.action instanceof ActionPlace){
            ActionPlace action=(ActionPlace)pair.action;
            if(get(action.dx()+pair.playerX,action.dy()+pair.playerY)==CellType.EMPTY)set(action.dx()+pair.playerX,action.dy()+pair.playerY,CellType.WOOL);
        }
        if(pair.action instanceof ActionDestroy){
            ActionDestroy action=(ActionDestroy)pair.action;
            if(get(action.dx()+pair.playerX,action.dy()+pair.playerY)==CellType.WOOL)set(action.dx()+pair.playerX,action.dy()+pair.playerY,CellType.EMPTY);
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
