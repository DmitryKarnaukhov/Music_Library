package musiclibrary.entities;

import org.mongodb.morphia.annotations.Id;

import java.io.Serializable;

import static musiclibrary.dbworks.dbconstants.DBconstants.COLLECTION_ALBUMS;

@org.mongodb.morphia.annotations.Entity(COLLECTION_ALBUMS)
public class Album extends Entity implements Serializable {
    @Id
    private final int id;
    private final String name;

    public Album(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Album() {
        id = -1;
        name = null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
