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

public class ArtistController extends  GenericController<Artist> {
    // private  Model<Artist> container;

    private ArtistController() {
    }

    @Inject
    public ArtistController(Model<Artist> ArtistContainer) {
        this.container = ArtistContainer;
    }

    public int add(String name) {
        int id=0;
        try {
            id=getNextId();
            Artist artist = new Artist(id,name);
            container.put(id,artist);
        } catch (NumberFormatException e) {
            throw e;
        }
        return id;
    }

    public void change(int changedArtistId, String name){
        try {
            container.remove(changedArtistId);
            container.put(changedArtistId,new Artist(changedArtistId,name));
        } catch (Exception e) {
            throw  e;
        }
    }
}
