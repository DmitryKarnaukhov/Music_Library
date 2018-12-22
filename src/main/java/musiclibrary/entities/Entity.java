package musiclibrary.entities;

public abstract class Entity {
    private  int id;
    private  String name;

    public Entity(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
