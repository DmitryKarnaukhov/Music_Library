package musiclibrary.entities;

public class Track {
    private final int id;
    private final String name;
    private final Artist artist;
    private final double trackLenght;
    private final Genre genre;

    public Track(int id, String name, Artist artist,
                 double trackLenght, Genre genre) throws InterruptedException {
        this.id = id;
        this.name = name;
        this.artist = artist;
        if (trackLenght <= 0)
            throw new NumberFormatException("Недопустимое значение длины трека");
        this.trackLenght = trackLenght;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public Artist getArtist() {
        return artist;
    }

    public double getTrackLenght() {
        return trackLenght;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getId() {
        return id;
    }
}

