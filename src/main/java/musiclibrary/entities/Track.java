package musiclibrary.entities;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import java.io.Serializable;
import java.util.Objects;

import static musiclibrary.dbworks.dbconstants.DBconstants.COLLECTION_TRACKS;

@org.mongodb.morphia.annotations.Entity(COLLECTION_TRACKS)
public class Track extends Entity implements Serializable {
    @Id
    private final int id;
    private final String name;
    @Reference
    private final Artist artist;
    private final double trackLenght;
    @Embedded
    private final Genre genre;

    private Track() {
        id = -1;
        name = null;
        artist = null;
        trackLenght = -1.0;
        genre = Genre.none;
    }

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

