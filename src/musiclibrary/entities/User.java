package musiclibrary.entities;
// Класс юзера
public class User {
    static int nextId;
    private int id;
    private String name;
    private String secName;
    private int age;
    private Gender gender;
    private TrackList[] trackLists;
    // Конструктор
    public User(String name, String secName, int age, Gender gender,int id) throws InterruptedException {
        this.name = name;
        this.secName = secName;
        this.age = age;
        this.gender = gender;
        this.id = getUniqId();
        this.trackLists = new TrackList[0];
        this.id=id;
    }
    // Статический конструктор
    static {
        nextId = 0;
    }
    // Получает уникальный id
    private synchronized int getUniqId() throws InterruptedException {
        wait();
        if (nextId != 0)
            nextId++;
        notify();
        return nextId;
    }
    // Добавляет треклист
    public void addTrackList(TrackList trackList) {
        TrackList[] tempTrackLists = new TrackList[trackLists.length + 1];
        for (int trackListPointer = 0; trackListPointer < trackLists.length; trackListPointer++) {
            tempTrackLists[trackListPointer] = trackLists[trackListPointer];
        }
        tempTrackLists[tempTrackLists.length - 1] = trackList;
        trackLists = tempTrackLists;
    }
    // Проверка - есть ли трек в треклисте
    public  boolean hasTrackList(TrackList trackList) {
        for (TrackList trackListInList : trackLists) {
            if (trackListInList.equals(trackList))
                return true;
        }
        return false;
    }
    // Удаляет трек из треклиста, возвращает true если успешно
    public boolean removeTrack(TrackList trackList) {
        if (!hasTrackList(trackList))
            return false;
        boolean isDeleteSucceed = false;
        TrackList[] tempTrackLists = new TrackList[trackLists.length - 1];
        int tempTrackListPointer = 0;
        for (int deleteTrackListPoiter = 0; deleteTrackListPoiter < trackLists.length; deleteTrackListPoiter++) {
            if (deleteTrackListPoiter == trackLists.length - 1)
                break;
            if (isDeleteSucceed)
                tempTrackListPointer = deleteTrackListPoiter - 1;
            else
                tempTrackListPointer = deleteTrackListPoiter;
            tempTrackLists[tempTrackListPointer] = trackLists[deleteTrackListPoiter];
            if (trackList.equals(trackLists[deleteTrackListPoiter]))
                isDeleteSucceed = true;
        }
        return isDeleteSucceed;
    }

    public int getId() {
        return id;
    }

    public TrackList[] getTrackLists() {
        return trackLists;
    }


}
