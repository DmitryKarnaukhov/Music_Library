package musiclibrary.mvc.controller;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import musiclibrary.entities.TrackList;
import musiclibrary.entities.User;
import musiclibrary.mvc.model.Model;

@Singleton
public class UserController extends GenericController<User>{

    @Inject
    public UserController(Model<User> userContainer) {
        this.container = userContainer;
    }

    public int add(String name, ImmutableList<TrackList> trackLists) throws InterruptedException {
        int id=0;
        id=getNextId();
        User user = new User(id, name, trackLists);
        container.put(id,user);
        return id;
    }

    public void change(int changedUserId,String name, ImmutableList<TrackList> trackLists) throws InterruptedException{
        try{
            User user = new User(changedUserId,name, trackLists);
            container.remove(changedUserId);
            container.put(changedUserId,user);
        }catch (Exception e){throw new RuntimeException("Cant change User"+changedUserId,e);}
    }
}