package musiclibrary.mvc.model;

import musiclibrary.entities.Track;
import musiclibrary.entities.User;
import musiclibrary.mvc.Listener;

import java.util.ArrayList;

public class Model {
    private UserContainer userList;
    private TrackContainer trackList;
    private ArrayList<Listener> listenerList;

    public Model() {
        userList=null;
        trackList=null;
        listenerList=null;
    }



}
