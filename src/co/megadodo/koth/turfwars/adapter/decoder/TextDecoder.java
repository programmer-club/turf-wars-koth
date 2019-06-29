package co.megadodo.koth.turfwars.adapter.decoder;

import co.megadodo.koth.turfwars.Action;
import co.megadodo.koth.turfwars.ActionDestroy;
import co.megadodo.koth.turfwars.ActionMove;
import co.megadodo.koth.turfwars.ActionPlace;

public class TextDecoder extends Decoder {
    @Override
    public Action decodeAction(String data) {
        int val = Integer.parseInt(data);

        // 4 place
        // 4 destroy
        // 4 move
        // shoot

        byte moveType = (byte)(val & 0x3);
        switch(moveType) {
            case 0: // place0
                return new ActionPlace(0, 0);
            case 1: // destroy
                return new ActionDestroy(0, 0);
            case 2: // move
                return new ActionMove(0, 0);
            case 3: // shoot
            default:
                return null;
        }
    }
}
