package musiclibrary.mvc.controller;

import com.google.inject.*;
import musiclibrary.entities.Artist;
import musiclibrary.entities.Genre;
import musiclibrary.entities.Artist;
import musiclibrary.mvc.model.Model;
import musiclibrary.mvc.view.Listener;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class ArtistController implements Controller {
    private Model<Artist> ArtistContainer;
    private String path;
    private ArrayList<Listener> listeners;

    public void addListener (Listener listener){
        listeners.add(listener);
    }
    // Model Number:
    // User - 3
    // ArtistList - 2
    // Track - 1
    // Artist -0
    public void update(boolean del,int id){
        for (Listener l:listeners
        ) {
            l.somethingChanged(0,del,id);
        }
    }

    private ArtistController() {
    }

    @Inject
    public ArtistController(Model<Artist> ArtistContainer) {
        this.ArtistContainer = ArtistContainer;
        path = new File("").getAbsolutePath();
        listeners = new ArrayList<Listener>();
    }

    private int getNextArtistId() {
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

    public int addArtist(String name) throws InterruptedException {
        int id=0;
        try {
            id=getNextArtistId();
            Artist Artist = new Artist(id,name);
            HashMap<Integer, Artist> map = ArtistContainer.getMap();
            map.put(Artist.getId(), Artist);
        } catch (NumberFormatException e) {
        }
        this.update(false, id);
        return id;
    }

    public void delArtist(int ArtistId) {
        HashMap<Integer, Artist> map = ArtistContainer.getMap();
        if (map.containsKey(ArtistId)) {
            map.remove(ArtistId);
        }
        this.update(true, ArtistId);
    }

    public void changeArtist(int changedArtistId, String name) throws InterruptedException {
        try {
            HashMap<Integer, Artist> map = ArtistContainer.getMap();
            Artist Artist = new Artist(getNextArtistId(), name);
            if (map.containsKey(changedArtistId)) {
                map.remove(changedArtistId);
            }
            map.put(changedArtistId, Artist);
        } catch (Exception e) {
        }
        this.update(false, changedArtistId);
    }

    public Artist getArtist(int id) {
        HashMap<Integer, Artist> map = ArtistContainer.getMap();
        if (map.containsKey(id)) return map.get(id);
        return null;
    }

    public Model<Artist> getArtistContainer() {
        return ArtistContainer;
    }
}
