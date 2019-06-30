package co.megadodo.koth.turfwars.adapter.decoder;

import co.megadodo.koth.turfwars.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextDecoder extends Decoder {
    @Override
    public Action decodeAction(String data) {
        Pattern reg = Pattern.compile("\\d+");

        // 4 place
        // 4 destroy
        // 4 move
        // shoot

        Matcher m = reg.matcher(data);
        m.find();
        int moveType = Integer.parseInt(m.group());
        m.find();
        int x = Integer.parseInt(m.group());
        m.find();
        int y = Integer.parseInt(m.group());
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
