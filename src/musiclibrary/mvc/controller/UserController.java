package musiclibrary.mvc.controller;

import musiclibrary.entities.Gender;
import musiclibrary.entities.Track;
import musiclibrary.entities.TrackList;
import musiclibrary.entities.User;
import musiclibrary.mvc.model.UserContainer;
import java.util.HashMap;

public class UserController implements Controller {
    private static int nextUserId;
    private UserContainer userContainer;

    public UserController(){
        UserContainer userContainer=new UserContainer();
        nextUserId=0;
    }

    private int getNextUserId(){
        return nextUserId++;
    }

    public void addUser(String name, String secName, int age, Gender gender, TrackList[] trackLists) throws InterruptedException {
        try{
            User user = new User(name, secName,age,gender,getNextUserId());
            for (TrackList t:trackLists
                 ) {
                user.addTrackList(t);
            }
            HashMap<Integer, User> map = userContainer.getMap();
            map.put(user.getId(),user);
        }
        catch (NumberFormatException e){}
    }
    public void delTrack(int userId){
        HashMap<Integer, User> map = userContainer.getMap();
        if (map.containsKey(userId)){
            map.remove(userId);
        }
    }
    public void changeUser(int changedUserId,String name, String secName, int age, Gender gender, TrackList[] trackLists) throws InterruptedException{
        try{
            HashMap<Integer, User> map = userContainer.getMap();
            User user = new User(name, secName, age,gender ,changedUserId);
            for (TrackList t:trackLists
            ) {
                user.addTrackList(t);
            }
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
}
