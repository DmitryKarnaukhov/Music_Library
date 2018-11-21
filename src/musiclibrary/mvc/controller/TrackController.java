package musiclibrary.mvc.controller;

import musiclibrary.entities.Genre;
import musiclibrary.entities.Track;
import musiclibrary.mvc.model.TrackContainer;

import java.util.HashMap;

public class TrackController implements Controller {
    private TrackContainer trackContainer;
    private static int nextTrackId;

    public TrackController() {
        trackContainer = new TrackContainer();
        nextTrackId = 0;
    }

    private int getNextTrackId() {
        return nextTrackId++;
    }

    public void addTrack(String name, String artist, String album, double trackLenght, Genre genre) throws InterruptedException {
        try {
            Track track = new Track(name, artist, album, trackLenght, genre, getNextTrackId());
            HashMap<Integer, Track> map = trackContainer.getMap();
            map.put(track.getId(), track);
        } catch (NumberFormatException e) {
        }
    }

    public void delTrack(int trackId) {
        HashMap<Integer, Track> map = trackContainer.getMap();
        if (map.containsKey(trackId)) {
            map.remove(trackId);
        }
    }

    public void changeTrack(int changedTrackId, String name, String artist, String album, double trackLenght, Genre genre) throws InterruptedException {
        try {
            HashMap<Integer, Track> map = trackContainer.getMap();
            Track track = new Track(name, artist, album, trackLenght, genre, changedTrackId);
            if (map.containsKey(changedTrackId)) {
                map.remove(changedTrackId);
            }
            map.put(changedTrackId, track);
        } catch (Exception e) {
        }
    }

    public Track getTrack(int id) {
        HashMap<Integer, Track> map = trackContainer.getMap();
        if (map.containsKey(id)) return map.get(id);
        return null;

    }
}
