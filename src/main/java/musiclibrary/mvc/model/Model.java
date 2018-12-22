package musiclibrary.mvc.model;

import com.google.inject.Singleton;
import musiclibrary.mvc.view.Listener;
import sun.reflect.misc.FieldUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.lang.reflect.*;

@Singleton
public class Model<T> implements Serializable {
    private ArrayList<Listener> listeners;
    private HashMap<Integer, T> map;

    public void addListener (Listener listener){
        listeners.add(listener);
    }

    private void update(){
        for (Listener listener:listeners
        ) {
            listener.somthingChanged();
        }
    }

    public Model() {
        map = new HashMap<Integer, T>();
        listeners= new ArrayList<Listener>();
    }

    public Model(HashMap<Integer, T> map) {
        this.map = map;
    }

    public void setMap(HashMap<Integer, T> map) {
        this.map = map;
        this.update();
    }

    public void put(int id,T item) {
        if (!Objects.equals(map.get(id), item)) {
            map.put(id, item);
            this.update();
        }
    }

    public boolean remove(int id) {
        if (map.containsKey(id)) {
            map.remove(id);
            this.update();
            return true;
        }else return false;
    }

    public T getItem(int id){
        if (map.containsKey(id)){
            return map.get(id);
        } else throw new RuntimeException("Model have not item with id ="+id);
    }


    @Override
    public int hashCode() {
        return Objects.hash( map);
    }
    
    @Override
    public boolean equals(Object obj) {
        return map.equals(obj);
    }
}
