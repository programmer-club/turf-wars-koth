package co.megadodo.koth.turfwars.adapter;

import co.megadodo.koth.turfwars.*;
import co.megadodo.koth.turfwars.adapter.decoder.Decoder;
import co.megadodo.koth.turfwars.adapter.encoder.Encoder;

import java.io.IOException;
import java.io.InputStream;

public abstract class Adapter extends Player {
    protected Language language;
    protected String name;

    public static Encoder encoder;
    public static Decoder decoder;

    public Adapter(int startX, int startY, Team team, PlayerStats initial_stats,
                   Language language, String name) {
        super(startX, startY, team, initial_stats);

        this.language = language;
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    public String stringifiedAction(Board b) throws IOException {
        Runtime r = Runtime.getRuntime();

        Process p = r.exec(this.language + this.name);
        p.getOutputStream().write(encoder.encodeBoard(b));

        InputStream is = p.getInputStream();
        byte[] by = new byte[1024];
        is.read(by);

        return new String(by);
    }

    @Override
    public Action move(Board board) {
        try {
            return decoder.decodeAction(stringifiedAction(board));
        } catch(IOException e) {
            return null;
        }
    }
}