package musiclibrary.mvc.controller;

import musiclibrary.entities.Gender;
import musiclibrary.entities.TrackList;
import musiclibrary.entities.User;
import musiclibrary.mvc.model.Model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.LinkedList;

public class UserController implements Controller {
    private Model<User> userContainer;
    private String path;

    private UserController() {
    }

    public UserController(Model<User> userContainer) {
        this.userContainer = userContainer;
        path = new File("").getAbsolutePath();
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
            User user = new User(id,name, trackLists);
            HashMap<Integer, User> map = userContainer.getMap();
            map.put(user.getId(),user);
        }
        catch (NumberFormatException e){}
        return id;
    }
    public void delTrack(int userId){
        HashMap<Integer, User> map = userContainer.getMap();
        if (map.containsKey(userId)){
            map.remove(userId);
        }
    }
    public void changeUser(int changedUserId,String name, LinkedList<TrackList> trackLists) throws InterruptedException{
        try{
            HashMap<Integer, User> map = userContainer.getMap();
            User user = new User(getNextUserId(),name, trackLists);
            if (map.containsKey(changedUserId)){
                map.remove(changedUserId);
            }
            map.put(changedUserId,user);
        }catch (Exception e){}
    }

    public User getUser(int id) {
        HashMap<Integer, User> map = userContainer.getMap();
        if (map.containsKey(id)) return map.get(id);
        return null;
    }

    public Model<User> getUserContainer() {
        return userContainer;
    }
}
