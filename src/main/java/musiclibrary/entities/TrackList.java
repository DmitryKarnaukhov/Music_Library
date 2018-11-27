package musiclibrary.entities;

import java.util.LinkedList;

public class TrackList {
    private String name;
    private LinkedList<Track> tracks;

    public TrackList(String name, LinkedList<Track> tracks) {
        this.name = name;
        this.tracks = tracks;
    }
    public TrackList(String name) {
        this.name = name;
        this.tracks = new LinkedList<Track>();
    }

    public void add(Track track) {
        tracks.add(track);
    }

    public  void add(int index, Track track) {
        tracks.add(index, track);
    }

    public  boolean hasTrack(Track track) {
        return tracks.contains(track);
    }

    public void removeTrack(Track track) {
        tracks.remove(track);
    }

    public void removeTrack(int index) {
        tracks.remove(index);
    }
}
