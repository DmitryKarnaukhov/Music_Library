package musiclibrary.entities;

import java.util.ArrayList;

public class Artist {
    private int id;
    private String name;
    private String secName;
    private ArrayList<Track> tracks;

    public Artist(int id, String name, String secName, ArrayList<Track> tracks) {
        this.id = id;
        this.name = name;
        this.secName= secName;
        this.tracks = tracks;
    }

    public Artist(int id, String name, String secName) {
        this(id, name, secName, new ArrayList<Track>());
    }

    public void addTrack(Track track) {
        tracks.add(track);
    }

    public void removeTrack(Track track) {
        tracks.remove(track);
    }
}
