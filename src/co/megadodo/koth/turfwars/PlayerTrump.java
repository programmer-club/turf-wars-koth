package co.megadodo.koth.turfwars;

public class PlayerTrump extends Player {
    public PlayerTrump(int startX, int startY, Team team) {
        super(startX, startY, team, new PlayerStats((int)Float.POSITIVE_INFINITY, 5, 4, 5, 5, 0.1F));
    }

    @Override
    public String name() {
        return "President Donald Trump";
    }

    public boolean hasGottenToZero = false;

    public boolean moveQ = false;

    public boolean hasGoneBackDown = false;

    public boolean hasPlacedAll = false;

    public boolean hasGottenToCentre = false;

    @Override
    public Action move(Board board) {
        int towardsCentre = this.team == Team.RED ? 1 : -1;

        if (!hasGottenToCentre) {
            if (Math.abs(this.y - board.dividingLine) <= 1) hasGottenToCentre = true;
            return new ActionMove(0, towardsCentre);
        } else if (!hasPlacedAll) {
            if (this.x == Board.width - 1) hasPlacedAll = true;
            if (hasGottenToZero) {
                moveQ = !moveQ;
                if (moveQ) {
                    return new ActionMove(1, 0);
                } else {
                    return new ActionPlace(-1, 0);
                }
            } else {
                if (this.x == 1) this.hasGottenToZero = true;
                return new ActionMove(-1, 0);
            }
        } else {
            if (hasGoneBackDown) {
                hasPlacedAll = false;
                hasGoneBackDown = false;
                hasGottenToZero = false;
                return new ActionPlace(0, towardsCentre);
            } else {
                hasGoneBackDown = true;
                return new ActionMove(0, -towardsCentre);
            }
        }
    }

}
