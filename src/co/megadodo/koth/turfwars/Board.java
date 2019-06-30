package co.megadodo.koth.turfwars;

import co.megadodo.koth.turfwars.adapter.Adapter;
import co.megadodo.koth.turfwars.adapter.Language;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

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
//                PlayerRandom::new,
//                PlayerRandom::new,
//                PlayerRandom::new,
//                PlayerRandom::new,
                PlayerRandom::new,
                createAdaptorBot(Language.Python, "bots/python/sample.py"),
//                createAdaptorBot(Language.Python, "bots/python/chaos.py")
        });
    }

    private PlayerInstantiator createAdaptorBot(Language lang, String s) {
        return (int x, int y, Team team) -> {
            return new Adapter(x, y, team, new PlayerStats(5, 5, 4, 5, 5, 0.1F),
                    lang, s);
        };
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

        for(int x=0;x<width;x++){
            for(int y=0;y<height;y++){
                if(board[x][y].isPlayer()){
                    board[x][y].player.health+=board[x][y].player.initial_stats.regenPerTurn();
                }
            }
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

    private boolean inBounds(int x,int y){
        return x>=0&&y>=0&&x<width&&y<height;
    }

    private void processAction(ActionPair pair){
        if(pair.action instanceof ActionDirectional){
            ActionDirectional action=(ActionDirectional)pair.action;
            int tx=action.dx()+pair.playerX;
            int ty=action.dy()+pair.playerY;
            if(isRed(ty)&&pair.player.team==Team.BLUE)return;
            if(isBlue(ty)&&pair.player.team==Team.RED)return;
            Player player=pair.player;
            if(action instanceof ActionDestroy){
                if(get(tx,ty)==CellType.WOOL){
                    set(tx,ty,CellType.EMPTY);
                    player.blocks++;
                }
            } else if(action instanceof ActionPlace){
                if(get(tx,ty)==CellType.EMPTY&&player.blocks>0){
                    set(tx,ty,CellType.WOOL);
                    player.blocks--;
                }
            } else if(action instanceof ActionMove){
                if(get(tx,ty)==CellType.EMPTY&&inBounds(tx,ty)){
                    set(tx,ty,CellType.PLAYER(pair.player));
                    set(pair.playerX,pair.playerY,CellType.EMPTY);
                    pair.player.x=tx;
                    pair.player.y=ty;
                }
            }
        }else if(pair.action instanceof ActionShoot&&pair.player.ammo>0){
            pair.player.ammo--;
            for(int x=0;x<pair.player.initial_stats.shotCount();x++){
                fireShot(pair.playerX,pair.playerY,forwardY(pair.player.team),pair.player.damagePerShot());
            }
        }
    }

    private void fireShot(int fromX,int fromY,int dy,double damagePerShot){
        int x=fromX;
        int y=fromY+dy;
        while(true){
            if(!inBounds(x,y))return;
            if(get(x,y)!=CellType.EMPTY){
                explodeCell(x,y, get(fromX,fromY).player, damagePerShot);
                return;
            }
            y+=dy;
        }
    }

    private void killPlayer(int x,int y,Player from){
        if(from==null)return;
        Player player=get(x,y).player;
        set(x,y,CellType.EMPTY);
//        System.out.println(player + " " + from);
        System.out.println(player.name()+" was killed by "+from.name()+" with the help of Legolas.");
    }

    private void explodeCell(int x,int y,Player from,double damagePerShot){
        CellType cell=get(x,y);
        if (cell.isPlayer()){
            cell.player.health-=damagePerShot;
            if(cell.player.health<0)killPlayer(x,y,from);
        }else if(cell==CellType.WOOL){
            set(x,y,CellType.EMPTY);
        }
    }

    private int forwardY(Team team){
        if(team==Team.RED)return 1;
        if(team==Team.BLUE)return -1;
        return 0;
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
