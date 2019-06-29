package co.megadodo.koth.turfwars.adapter.decoder;

import co.megadodo.koth.turfwars.Action;

public abstract class Decoder {
    public abstract Action decodeAction(String data);
}
