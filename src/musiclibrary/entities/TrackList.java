package musiclibrary.entities;

public class TrackList {
    private String name;
    private Track[] tracks;
    // Конструктор
    public TrackList(String name, Track[] tracks) {
        this.name = name;
        this.tracks = tracks;
    }
    public TrackList(String name) {
        this.name = name;
        tracks = new Track[0];
    }
// Добавление трека в треклист
    public void add(Track track) {
        Track[] tempTracks = new Track[tracks.length + 1];
        for (int trackPointer = 0; trackPointer < tracks.length; trackPointer++) {
            tempTracks[trackPointer] = tracks[trackPointer];
        }
        tempTracks[tempTracks.length - 1] = track;
        tracks = tempTracks;
    }
// Проверка - есть ли трек в треклисте
    public  boolean hasTrack(Track track) {
        for (Track trackInList : tracks) {
            if (trackInList.equals(track))
                return true;
        }
        return false;
    }
// Удаляет трек из треклиста, возвращает true если успешно
    public boolean removeTrack(Track track) {
        if (!hasTrack(track))
            return false;
        boolean isDeleteSucceed = false;
        Track[] tempTracks = new Track[tracks.length - 1];
        int tempTrackPointer = 0;
        for (int deleteTrackPoiter = 0; deleteTrackPoiter < tracks.length; deleteTrackPoiter++) {
            if (deleteTrackPoiter == tracks.length - 1)
                break;
            if (isDeleteSucceed)
                tempTrackPointer = deleteTrackPoiter - 1;
            else
                tempTrackPointer = deleteTrackPoiter;
            tempTracks[tempTrackPointer] = tracks[deleteTrackPoiter];
            if (track.equals(tracks[deleteTrackPoiter]))
                isDeleteSucceed = true;
        }
        return isDeleteSucceed;
    }
}
