package musiclibrary.entities;

import com.google.common.collect.ImmutableList;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import static musiclibrary.dbworks.dbconstants.DBconstants.COLLECTION_TRACKLIST;

@org.mongodb.morphia.annotations.Entity(COLLECTION_TRACKLIST)
public class TrackList extends Entity implements Serializable {
    @Id
    private final int id;
    @Reference
    private final Album album;
    @Reference(concreteClass = ImmutableList.class)
    private List<Track> tracks;

    public TrackList(int id, Album album, List<Track> tracks) {
        this.id = id;
        this.album = album;
        this.tracks = ImmutableList.copyOf(tracks);
    }

    private TrackList() {
        id = -1;
        album = null;
        tracks = null;
    }

    public int getId() {
        return id;
    }

    public Album getGetAlbum() {
        return album;
    }

    public  List<Track> getTracks() {
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
