package musiclibrary.entities;

import org.mongodb.morphia.annotations.Id;

import java.io.Serializable;
import java.util.Objects;

import static musiclibrary.dbworks.dbconstants.DBconstants.COLLECTION_ARTISTS;

@org.mongodb.morphia.annotations.Entity(COLLECTION_ARTISTS)
public class Artist extends Entity
        implements Serializable {
    @Id
    private final int id;
    private final String name;

    public Artist(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private Artist() {
        id = -1;
        name = null;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass())
            return false;
        return this.id == ((Artist)obj).id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
