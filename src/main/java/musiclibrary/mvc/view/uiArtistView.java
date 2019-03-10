package musiclibrary.mvc.view;

import musiclibrary.entities.*;
import musiclibrary.mvc.controller.*;

import java.util.ArrayList;
import java.util.List;


public class uiArtistView {
    private GenericController controller;

    public uiArtistView(GenericController controller) {
        this.controller = controller;
    }

    public void add(Entity entity) {
        controller.add(entity);
    }

    public void remove(int id) {
        controller.del(id);
    }

    public void replace(Entity entity) {
        controller.replace(entity);
    }

    public Artist get(int id) {
        return (Artist) controller.get(id);
    }

    public List<Artist> getAll() {
        return controller.getAll();
    }

    public String[][] getAllTableData() {
        ArrayList<Artist> all = (ArrayList<Artist>) controller.getAll();
        Object[] allStringArray = all.toArray();
        String[][] result = new String[all.size()][];
        for (int artistPointer = 0; artistPointer < all.size(); artistPointer++) {
            result[artistPointer] = new String[2];
            result[artistPointer][0] = String.valueOf(((Artist)allStringArray[artistPointer]).getId());
            result[artistPointer][1] = String.valueOf(((Artist)allStringArray[artistPointer]).getName());
        }
        return result;
    }
}
