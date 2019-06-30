package co.megadodo.koth.turfwars;

import processing.core.PApplet;
import processing.core.PImage;

public class GUIRunner extends PApplet {

    public static void main(String...args){
        PApplet.main("co.megadodo.koth.turfwars.GUIRunner");
    }


    public Board board;

    private PImage img_red_wool,img_red_terracotta,img_blue_wool,img_blue_terracotta,img_steve_head;

    public void settings(){
        size(1000,1000);
        board=new Board();

        System.out.println("GUIRunner renderer = "+sketchRenderer());

        tw=width/Board.width;
        th=height/Board.height;

        img_red_wool=loadImage("red_wool.png");
        img_red_terracotta=loadImage("red_terracotta.png");
        img_blue_wool=loadImage("blue_wool.png");
        img_blue_terracotta=loadImage("blue_terracotta.png");
        img_steve_head=loadImage("stevehead.png");
    }

    private int tw;
    private int th;

    public void setup(){
//        frameRate(5);
    }

    private void highlightColumn(int x){
        noFill();
        stroke(0);
        rect(x*tw,0,tw,height);
    }

    public void draw(){
        background(0);

        renderBoardBackground();

        renderBoard();

        board.runTurn();


        textAlign(RIGHT, TOP);
        fill(255);
        noStroke();
        textSize(20);
        text("Turn #: "+board.turnNumber, width,0);

    }

    private void renderBoard(){
        for(int x=0;x<Board.width;x++){
            for(int y=0;y<Board.height;y++){
                int color=-1;
                PImage img=null;

                if(board.board[x][y]==CellType.WOOL){
                    img=board.isBlueWool(x,y)?img_blue_wool:img_red_wool;
                }
                else if(board.board[x][y].isPlayer()){
//                    color=color(0,255,0);
                    img=img_steve_head;

                }
                else img=board.isBlue(y)?img_blue_terracotta:img_red_terracotta;

                if(color==-1){
                    if(img!=null){
                        image(img,x*tw,y*th,tw,th);
                    }
                    continue;
                }
                fill(color);
                noStroke();
                rect(x*tw,y*th,tw,th);
            }
        }
        for(int x=0;x<Board.width;x++){
            for(int y=0;y<Board.height;y++){
                if(board.board[x][y].isPlayer()){
                    highlightColumn(x);
                }
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
