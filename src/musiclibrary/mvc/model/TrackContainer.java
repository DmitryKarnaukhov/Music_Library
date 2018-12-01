package musiclibrary.mvc.model;

import musiclibrary.entities.Track;

import java.util.HashMap;

public class TrackContainer {
    private HashMap<Integer, Track> map;

    public void setMap(HashMap<Integer, Track> map) {
        this.map = map;
    }

    public HashMap<Integer, Track> getMap() {
        return map;
    }
}
