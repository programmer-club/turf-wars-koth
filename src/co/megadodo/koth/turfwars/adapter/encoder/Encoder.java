package co.megadodo.koth.turfwars.adapter.encoder;

import co.megadodo.koth.turfwars.Action;
import co.megadodo.koth.turfwars.Board;

import java.io.IOException;

public abstract class Encoder {
    public abstract byte[] encodeBoard(Board b) throws IOException;
}
