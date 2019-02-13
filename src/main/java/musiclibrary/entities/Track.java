package musiclibrary.entities;

import java.io.Serializable;
import java.util.Objects;

public class Track extends Entity implements Serializable {
    private final int id;
    private final String name;
    private final Artist artist;
    private final double trackLenght;
    private final Genre genre;

    public Track(int id, String name, Artist artist,
                 double trackLength, Genre genre) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        if (trackLength <= 0)
            throw new NumberFormatException("Недопустимое значение длины трека");
        this.trackLenght = trackLength;
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

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artist=" + artist +
                ", trackLenght=" + trackLenght +
                ", genre=" + genre +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return id == track.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, artist, trackLenght, genre);
    }
}

