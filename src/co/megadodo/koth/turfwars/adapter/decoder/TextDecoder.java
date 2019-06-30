package co.megadodo.koth.turfwars.adapter.decoder;

import co.megadodo.koth.turfwars.*;

public class TextDecoder extends Decoder {
    @Override
    public Action decodeAction(String data) {
        String[] arr = data.split(" ");

        // 4 place
        // 4 destroy
        // 4 move
        // shoot

        int moveType = Integer.parseInt(arr[0]);
        int x = Integer.parseInt(arr[1]);
        int y = Integer.parseInt(arr[2]);
        switch(moveType) {
            case 0: // place0
                return new ActionPlace(x, y);
            case 1: // destroy
                return new ActionDestroy(x, y);
            case 2: // move
                return new ActionMove(x, y);
            case 3: // shoot
                return new ActionShoot();
            default:
                return null;
        }
    }
}
