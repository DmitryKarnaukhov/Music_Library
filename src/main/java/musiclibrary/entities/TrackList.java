package musiclibrary.entities;

import java.util.LinkedList;

public class TrackList {
    private final int id;
    private final Album album;
    private final LinkedList<Track> tracks;

    public TrackList(int id, Album album, LinkedList<Track> tracks) {
        this.id = id;
        this.album = album;
        this.tracks = tracks;
    }

    public int getId() {
        return id;
    }

    public Album getGetAlbum() {
        return album;
    }

    public  LinkedList<Track> getTracks() {
        return tracks;
    }
}
