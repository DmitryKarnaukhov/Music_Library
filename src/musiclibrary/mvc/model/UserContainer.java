package musiclibrary.mvc.model;

import musiclibrary.entities.User;

import java.util.HashMap;

public class UserContainer{
    private HashMap<Integer, User> map;

    public HashMap<Integer, User> getMap() {
        return map;
    }

    public void setMap(HashMap<Integer, User> map) {
        this.map = map;
    }
}
