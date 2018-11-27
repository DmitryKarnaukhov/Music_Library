package musiclibrary.entities;

// Класс музыкального трека
public class Track {
    private int id;
    private String name;
    private String artist;
    private String album;
    private double trackLenght;
    private Genre genre;
// Конструктор
    public Track(String name, String artist,
                 String album, double trackLenght, Genre genre) throws InterruptedException {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Track))
            return false;
        Track trackObj = (Track)obj;
        if (this.id == trackObj.id)
            return true;
        return false;
    }
}

