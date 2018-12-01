package musiclibrary.entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Objects;

public class User implements Serializable {
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", trackLists=" + trackLists +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(trackLists, user.trackLists);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, trackLists);
    }
}
