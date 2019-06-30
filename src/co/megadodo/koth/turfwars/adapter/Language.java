package co.megadodo.koth.turfwars.adapter;

public enum Language {
    Python("python3 "),
    CPP("./"),
    Node("node ");

    private String command;

    private Language(String command) {
        this.command = command;
    }

    public String getCommand() {
        return this.command;
    }
}
