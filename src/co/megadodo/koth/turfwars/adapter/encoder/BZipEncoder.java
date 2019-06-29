package co.megadodo.koth.turfwars.adapter.encoder;

import co.megadodo.koth.turfwars.Board;
import co.megadodo.koth.turfwars.CellType;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

public class BZipEncoder extends Encoder {
    public static void main(String[] args) {
        Board b = new Board();
        try {
            byte[] bytea = new BZipEncoder().encodeBoard(b);

            System.out.print("0x");
            for (byte byt : bytea) {
                System.out.print(Integer.toHexString(byt));
            }
            System.out.println();

            System.out.println(Base64.getEncoder().encodeToString(bytea));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] encodeBoard(Board b) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        BZip2CompressorOutputStream bz = new BZip2CompressorOutputStream(os);

        bz.write(Board.width);
        bz.write(Board.height);
        bz.write(b.dividingLine);
        bz.write(b.turnNumber);

        for (int x = 0; x < Board.width; x++) {
            for (int y = 0; y < Board.height; y++) {
                bz.write(transformCell(x, y, b));
            }
        }

        bz.close();

        return os.toByteArray();
    }

    private int transformCell(int x, int y, Board b) {
        // 0 == nothing
        // 1 == Red Wool
        // 2 == Blue Wool
        // 3 == Red Player
        // 4 == Blue Player
        if (b.isRedWool(x, y)) {
            return 1;
        } else if (b.isBlueWool(x, y)) {
            return 2;
        } else if (b.isRedPlayer(x, y)) {
            return 3;
        } else if (b.isBluePlayer(x, y)) {
            return 4;
        }
        return 0;
    }
}
