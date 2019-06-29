package co.megadodo.koth.turfwars.adapter.encoder;

import co.megadodo.koth.turfwars.Action;
import co.megadodo.koth.turfwars.Board;

public abstract class Encoder {
    public abstract byte[] encodeBoard(Board b);
}
