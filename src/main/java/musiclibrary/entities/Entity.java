package musiclibrary.entities;

public abstract class Entity {
    private  int id;
    private  String name;

    public Entity() {}

    abstract int getId();

    public Entity(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
