package musiclibrary.entities;

import com.google.common.collect.ImmutableList;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class TrackList implements Serializable {
    private final int id;
    private final Album album;
    private ImmutableList<Track> tracks;

    public TrackList(int id, Album album, List<Track> tracks) {
        this.id = id;
        this.album = album;
        this.tracks = ImmutableList.copyOf(tracks);
    }

    public int getId() {
        return id;
    }

    public Album getGetAlbum() {
        return album;
    }

    public  ImmutableList<Track> getTracks() {
        return tracks;
    }

    @Override
    public String toString() {
        return "TrackList{" +
                "id=" + id +
                ", album=" + album +
                ", tracks=" + tracks +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass())
            return false;
        return this.id == ((TrackList)obj).id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, album, tracks);
    }
}
