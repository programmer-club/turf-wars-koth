package co.megadodo.koth.turfwars;

import processing.core.PApplet;

public class GUIRunner extends PApplet {

    public static void main(String...args){
        PApplet.main("co.megadodo.koth.turfwars.GUIRunner");
    }


    public Board board;

    public void settings(){
        size(1000,1000);
        board=new Board();

        System.out.println("GUIRunner renderer = "+sketchRenderer());

        tw=width/Board.width;
        th=height/Board.height;
    }

    private int tw;
    private int th;

    public void setup(){
        frameRate(5);
    }

    public void draw(){
        background(0);

        renderBoardBackground();

        renderBoard();

        board.runTurn();


        textAlign(RIGHT, TOP);
        fill(0);
        stroke(0);
        textSize(20);
        text("Turn #: "+board.turnNumber, width,0);

    }

    private void renderBoard(){
        int colorRedWool=color(255,255/3,255/3);
        int colorBlueWool=color(255/3,255/3,255);

        for(int x=0;x<Board.width;x++){
            for(int y=0;y<Board.height;y++){
                int color=-1;

                if(board.board[x][y]==CellType.WOOL)color=board.isBlueWool(x,y)?colorBlueWool:colorRedWool;
                if(board.board[x][y].isPlayer())color=color(0,255,0);

                if(color==-1)continue;
                fill(color);
                noStroke();
                rect(x*tw,y*th,tw,th);
            }
        }
    }

    private void renderBoardBackground(){
        int colorBgRed=color(255,255*2/3,255*2/3);
        int colorBgBlue=color(255*2/3,255*2/3,255);

        noStroke();

        fill(colorBgRed);
        rect(0,0,width,th*board.dividingLine);

        fill(colorBgBlue);
        rect(0,th*board.dividingLine,width,height-th*board.dividingLine);
    }

}
