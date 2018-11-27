package musiclibrary.entities;

public class Album {
    private int id;
    private String name;
    private TrackList trackList;

    public Album(int id, String name, TrackList trackList) {
        this.id = id;
        this.name = name;
        this.trackList = trackList;
    }

    public Album(int id, String name) {
        this(id, name, new TrackList(name));
    }

    public void addTrack(Track track) {
        trackList.add(track);
    }

    public void removeTrack(int index) {
        trackList.removeTrack(index);
    }

    public void removeTrack(Track track) {
        trackList.removeTrack(track);
    }
}
