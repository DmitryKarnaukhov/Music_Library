package musiclibrary.entities;

import java.util.LinkedList;

public class User {
    private int id;
    private String name;
    private String secName;
    private int age;
    private Gender gender;
    private LinkedList<TrackList> trackLists;
    // Конструктор
    public User(String name, String secName, int age, Gender gender) throws InterruptedException {
        this.name = name;
        this.secName = secName;
        this.age = age;
        this.gender = gender;
        this.trackLists = new LinkedList<TrackList>();
    }
    public void addTrackList(TrackList trackList) {
        trackLists.add(trackList);
    }

    public  boolean hasTrackList(TrackList trackList) {
        return trackLists.contains(trackList);
    }

    public void removeTrack(TrackList trackList) {
        trackLists.remove(trackList);
    }

    public void removeTrack(int index) {
        trackLists.remove(index);
    }

    public TrackList getTrackList(int index) {
        return trackLists.get(index);
    }
}
