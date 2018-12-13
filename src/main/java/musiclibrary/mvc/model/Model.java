package musiclibrary.mvc.model;

import com.google.inject.Singleton;
import sun.reflect.misc.FieldUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;
import java.lang.reflect.*;

@Singleton
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        Field[] thisFields = FieldUtil.getFields(this.map.getClass());
        for (Field field : thisFields) {
            try {
                FieldUtil.getField(((Model<T>)obj).getClass(), field.getName());
            } catch (NoSuchFieldException e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(map);
    }
}
