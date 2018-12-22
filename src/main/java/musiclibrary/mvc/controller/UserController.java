package musiclibrary.mvc.controller;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import musiclibrary.entities.Gender;
import musiclibrary.entities.TrackList;
import musiclibrary.entities.User;
import musiclibrary.mvc.model.Model;
import musiclibrary.mvc.view.Listener;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

@Singleton
public class UserController implements Controller {
    private Model<User> userContainer;
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
            l.somethingChanged(3,del,id);
        }
    }

    private UserController() {
    }

    @Inject
    public UserController(Model<User> userContainer) {
        this.userContainer = userContainer;
        path = new File("").getAbsolutePath();
        listeners = new ArrayList<Listener>();
    }

    private int getNextUserId(){
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

    public int addUser(String name, LinkedList<TrackList> trackLists) throws InterruptedException {
        int id=0;
        try{
            id=getNextUserId();
            User user = new User(id,name, ImmutableList.copyOf(trackLists));
            HashMap<Integer, User> map = userContainer.getMap();
            map.put(user.getId(),user);
        }
        catch (NumberFormatException e){}
        this.update(false, id);
        return id;
    }
    public void delUser(int userId){
        HashMap<Integer, User> map = userContainer.getMap();
        if (map.containsKey(userId)){
            map.remove(userId);
        }
        this.update(true, userId);
    }
    public void changeUser(int changedUserId,String name, LinkedList<TrackList> trackLists) throws InterruptedException{
        try{
            HashMap<Integer, User> map = userContainer.getMap();
            User user = new User(getNextUserId(),name, ImmutableList.copyOf(trackLists));
            if (map.containsKey(changedUserId)){
                map.remove(changedUserId);
            }
            map.put(changedUserId,user);
        }catch (Exception e){}
        this.update(false,changedUserId);
    }

    public User getUser(int id) {
        HashMap<Integer, User> map = userContainer.getMap();
        if (map.containsKey(id)) return map.get(id);
        return null;
    }

    public Model<User> getContainer() {
        return userContainer;
    }
}
