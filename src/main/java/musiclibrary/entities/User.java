package musiclibrary.entities;

import java.util.LinkedList;

public class User {
    private final int id;
    private final String name;
    private final LinkedList<TrackList> trackLists;

    public User(int id, String name, LinkedList<TrackList> trackLists) throws InterruptedException {
        this.id = id;
        this.name = name;
        this.trackLists = trackLists;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public TrackList getTrackList(int index) {
        return trackLists.get(index);
    }
}
