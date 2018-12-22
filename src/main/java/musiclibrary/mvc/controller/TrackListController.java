package musiclibrary.mvc.controller;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import musiclibrary.entities.*;
import musiclibrary.entities.TrackList;
import musiclibrary.mvc.model.Model;
import musiclibrary.mvc.view.Listener;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

@Singleton
public class TrackListController implements Controller{
    private Model<TrackList> TrackListContainer;
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
            l.somethingChanged(2,del,id);
        }
    }

    private TrackListController() {
    }

    @Inject
    public TrackListController(Model<TrackList> TrackListContainer) {
        this.TrackListContainer = TrackListContainer;
        path = new File("").getAbsolutePath();
        listeners = new ArrayList<Listener>();
    }

    private int getNextTrackListId() {
        int id =0;
        if(! new File(path+"src/savedfiles/id.out").exists()){
            try(FileWriter fileWriter = new FileWriter(path+"src/savedfiles/id.out",false)){
                fileWriter.write(0);
            }catch (Exception e){
                e.getMessage();
            }
        }else{
            try(FileReader fileReader =new FileReader(path+"src/savedfiles/id.out")){
                id=fileReader.read();
            }catch (Exception e){
                e.getMessage();
            }
            try(FileWriter fileWriter = new FileWriter(path+"src/savedfiles/id.out",false)){
                fileWriter.write(id+1);
            }catch (Exception e){
                e.getMessage();
            }
        }
        return id;
    }

    public int addTrackList(Album album, LinkedList<Track> tracks) throws InterruptedException {
        int id=0;
        try {
            id=getNextTrackListId();
            TrackList TrackList = new TrackList(id, album, ImmutableList.copyOf(tracks));
            HashMap<Integer, TrackList> map = TrackListContainer.getMap();
            map.put(TrackList.getId(), TrackList);
        } catch (NumberFormatException e) {
        }
        this.update(false, id);
        return id;
    }

    public void delTrackList(int TrackListId) {
        HashMap<Integer, TrackList> map = TrackListContainer.getMap();
        if (map.containsKey(TrackListId)) {
            map.remove(TrackListId);
        }
        this.update(true, TrackListId);
    }

    public void changeTrackList(int changedTrackListId, Album album, LinkedList<Track> tracks) throws InterruptedException {
        try {
            HashMap<Integer, TrackList> map = TrackListContainer.getMap();
            TrackList TrackList = new TrackList(getNextTrackListId(), album, ImmutableList.copyOf(tracks));
            if (map.containsKey(changedTrackListId)) {
                map.remove(changedTrackListId);
            }
            map.put(changedTrackListId, TrackList);
        } catch (Exception e) {
        }
        this.update(false, changedTrackListId);
    }

    public TrackList getTrackList(int id) {
        HashMap<Integer, TrackList> map = TrackListContainer.getMap();
        if (map.containsKey(id)) return map.get(id);
        return null;
    }

    public Model<TrackList> getContainer() {
        return TrackListContainer;
    }
}
