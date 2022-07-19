package data.models;


public class Game {
    public final String name;
    public final int date;
    public final String company;
    public final Genre genre;

    public Game(String name, int date, String company, Genre genre) {
        this.name = name;
        this.date = date;
        this.company = company;
        this.genre = genre;
    }
}
