package musiclibrary.entities;

// Класс музыкального трека
public class Track {
    static int nextId;
    private int id;
    private String name;
    private String artist;
    private String album;
    private double trackLenght;
    private Genre genre;
// Конструктор
    public Track(String name, String artist,
                 String album, double trackLenght, Genre genre) throws InterruptedException {
        this.id = getUniqId();
        this.name = name;
        this.artist = artist;
        this.album = album;
        if (trackLenght <= 0)
            throw new NumberFormatException("Недопустимое значение длины трека");
        this.trackLenght = trackLenght;
        this.genre = genre;
    }
    // статический конструктор
    static {
        nextId = 0;
    }
// Геттер названия трека
    public String getName() {
        return name;
    }
// Сеттер названия трека
    public void setName(String name) {
        this.name = name;
    }
// Геттер имени артиста
    public String getArtist() {
        return artist;
    }
// Сеттер названия артиста
    public void setArtist(String artist) {
        this.artist = artist;
    }
// Геттер названия альбома
    public String getAlbum() {
        return album;
    }
// Сеттер названия альбома
    public void setAlbum(String album) {
        this.album = album;
    }
// Геттер длинны трека
    public double getTrackLenght() {
        return trackLenght;
    }
// Получает уникальный id для трека
    private synchronized int getUniqId() throws InterruptedException {
        wait();
        if (nextId != 0)
            nextId++;
        notify();
        return nextId;
    }
// Устанавливает в каком случае равны два трека
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Track))
            return false;
        Track trackObj = (Track)obj;
        if (this.id == trackObj.id)
            return true;
        return false;
    }
}

