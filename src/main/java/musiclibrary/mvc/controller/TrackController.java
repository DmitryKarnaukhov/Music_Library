package musiclibrary.mvc.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import musiclibrary.entities.Artist;
import musiclibrary.entities.Genre;
import musiclibrary.entities.Track;
import musiclibrary.mvc.model.Model;
import musiclibrary.mvc.view.Listener;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

@Singleton
public class TrackController implements Controller {
    private Model<Track> trackContainer;
    private String path;
    private ArrayList<Listener> listeners;

    public void addListener (Listener listener){
        listeners.add(listener);
    }
    // Model Number:
    // User - 3
    // TrackList - 2
    // Track - 1
    // Artist -0
    public void update(boolean del,int id){
        for (Listener l:listeners
        ) {
            l.somethingChanged(1,del,id);
        }
    }

    private TrackController() {
    }

    @Inject
    public TrackController(Model<Track> trackContainer) {
        this.trackContainer = trackContainer;
        path = new File("").getAbsolutePath();
        listeners = new ArrayList<Listener>();
    }

    private int getNextTrackId() {
        int id =0;
        if(! new File(path+"/src/savedfiles/id.out").exists()){
            try(FileWriter fileWriter = new FileWriter(path+"/src/savedfiles/id.out",false)){
                fileWriter.write(0);
            }catch (Exception e){
                e.getMessage();
            }
        }else{
            try(FileReader fileReader =new FileReader(path+"/src/savedfiles/id.out")){
                id=fileReader.read();
            }catch (Exception e){
                e.getMessage();
            }
            try(FileWriter fileWriter = new FileWriter(path+"/src/savedfiles/id.out",false)){
                fileWriter.write(id+1);
            }catch (Exception e){
                e.getMessage();
            }
        }
        return id;
    }

    public int addTrack(String name, Artist artist, double trackLenght, Genre genre) throws InterruptedException {
        int id=0;
        try {
            id=getNextTrackId();
            Track track = new Track(id, name, artist, trackLenght, genre);
            HashMap<Integer, Track> map = trackContainer.getMap();
            map.put(track.getId(), track);
        } catch (NumberFormatException e) {
        }
        this.update(false, id);
        return id;
    }

    public void delTrack(int trackId) {
        HashMap<Integer, Track> map = trackContainer.getMap();
        if (map.containsKey(trackId)) {
            map.remove(trackId);
        }
        this.update(true, trackId);
    }

    public void changeTrack(int changedTrackId, String name, Artist artist, double trackLenght, Genre genre) throws InterruptedException {
        try {
            HashMap<Integer, Track> map = trackContainer.getMap();
            Track track = new Track(getNextTrackId(), name, artist, trackLenght, genre);
            if (map.containsKey(changedTrackId)) {
                map.remove(changedTrackId);
            }
            map.put(changedTrackId, track);
        } catch (Exception e) {
        }
        this.update(false, changedTrackId);
    }

    public Track getTrack(int id) {
        HashMap<Integer, Track> map = trackContainer.getMap();
        if (map.containsKey(id)) return map.get(id);
        return null;
    }

    public Model<Track> getTrackContainer() {
        return trackContainer;
    }
}
