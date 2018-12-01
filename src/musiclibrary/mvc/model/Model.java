package musiclibrary.mvc.model;

import java.io.Serializable;
import java.util.HashMap;

public class Model<T> implements Serializable {
    private HashMap<Integer, T> map;

    public Model() {
        map = new HashMap<Integer, T>();
    }

    public Model(HashMap<Integer, T> map) {
        this.map = map;
    }

    public void setMap(HashMap<Integer, T> map) {
        this.map = map;
    }

    public HashMap<Integer, T> getMap() {
        return map;
    }

}
