package musiclibraryentities;

public class Track {
    private String name;
    private String artist;
    private String album;
    private double trackLenght;
    private Genre genre;

    public Track(String name, String artist,
                 String album, double trackLenght, Genre genre) throws NumberFormatException {
        this.name = name;
        this.artist = artist;
        this.album = album;
        if (trackLenght <= 0)
            throw new NumberFormatException("Недопустимое значение длины трека");
        this.trackLenght = trackLenght;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public double getTrackLenght() {
        return trackLenght;
    }
}
