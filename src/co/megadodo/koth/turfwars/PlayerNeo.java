package co.megadodo.koth.turfwars;

import java.util.ArrayList;
import java.util.List;

public class PlayerNeo extends Player {
    public PlayerNeo(int startX, int startY, Team team) {
        super(startX, startY, team, new PlayerStats(5, 5, 4, 5, 5, 0.1F));
    }

    @Override
    public String name() {
        return "Neo";
    }

    private static boolean hasGlitchedMatrix = false;

    @Override
    public Action move(Board board) {
        if (hasGlitchedMatrix)
            return null;
        else {
            for (int x = 0; x < Board.width; x++) {
                for (int y = 0; y < Board.height; y++) {
                    board.board[x][y] = CellType.PLAYER(this);
                }
            }
            hasGlitchedMatrix = true;
            return null;
        }
    }

}
