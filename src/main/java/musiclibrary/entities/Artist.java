package musiclibrary.entities;

import java.io.Serializable;
import java.util.Objects;

public class Artist implements Serializable {
    private final int id;
    private final String name;

    public Artist(int id, String name) {
        this.id = id;
        this.name = name;
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
