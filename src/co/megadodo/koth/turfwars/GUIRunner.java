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

    }

    public void draw(){
        background(0);
        renderBoardBackground();
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
