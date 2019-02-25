package musiclibrary.mvc.model;

import musiclibrary.entities.Artist;
import musiclibrary.entities.Genre;
import musiclibrary.entities.Track;
import musiclibrary.mvc.model.oldmongomodels.ArtistDBModel;
import musiclibrary.mvc.model.oldmongomodels.TrackDBModel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class TrackDBModelTest {
    ArtistDBModel artistModel = new ArtistDBModel();
    TrackDBModel trackModel = new TrackDBModel();
    String putTestArtistName = "Elvis";
    String putTestTrackName = "Blue Shoes";
    String getTestArtistName = "Linkin Park";
    String getTestTrackName = "Castle of Glass";
    String updTestArtistName = "Чайф";
    String updTestTrackName = "Всему свое время";
    String updNewTrackName = "С войны";
    String removeTestArtistName = "Eminem";
    String removeTestTrackName = "Lose Yourself";

    @Test
    public void put() {
        Artist artist = new Artist(artistModel.getNextId(), putTestArtistName);
        artistModel.put(artist);
        Track track = new Track(trackModel.getNextId(), putTestTrackName,
                artist, 36.0, Genre.Rock);
        trackModel.put(track);
        Track trackFromBase = trackModel.getItem(track.getId());
        Assert.assertEquals(trackFromBase, track);
    }

    @Test
    public void getItem() {
        Artist artist = new Artist(artistModel.getNextId(), getTestArtistName);
        artistModel.put(artist);
        Track track = new Track(trackModel.getNextId(), getTestTrackName,
                artist, 3.25, Genre.Rock);
        trackModel.put(track);
        Track trackFromBase = trackModel.getItem(
                getTestTrackName, null, -1, Genre.none);
        Assert.assertEquals(trackFromBase, track);
    }

    @Test
    public void update() {
        Artist testArtist = new Artist(artistModel.getNextId(), updTestArtistName);
        artistModel.put(testArtist);
        Track testTrack = new Track(trackModel.getNextId(),
                updTestTrackName, testArtist, 3.26, Genre.Rock);
        trackModel.put(testTrack);
        Track newTestTrack = new Track(testTrack.getId(),
                updNewTrackName, testArtist, 3.26, Genre.Rock);
        trackModel.update(newTestTrack);
        Assert.assertEquals(trackModel.getItem(testTrack.getId()).getName(), updNewTrackName);
    }

    @Test
    public void remove() {
        Artist testArtist = new Artist(artistModel.getNextId(), removeTestArtistName);
        artistModel.put(testArtist);
        Track testTrack = new Track(trackModel.getNextId(),
                removeTestTrackName, testArtist, 5.26, Genre.Rap);
        trackModel.remove(testTrack.getId());
        boolean isGotException = false;
        try {
            trackModel.getItem(testTrack.getId());
        } catch (NullPointerException e) {
            isGotException = true;
        }
        Assert.assertTrue(isGotException);
    }

    @After
    public void deleteTestDocuments() {
        int putTestTrackId = trackModel.getItem(putTestTrackName,
                null, -1, Genre.none).getId();
        int getTestTrackId = trackModel.getItem(getTestTrackName,
                null, -1, Genre.none).getId();
        int updNewTrackId = trackModel.getItem(updNewTrackName,
                null, -1, Genre.none).getId();
        trackModel.remove(putTestTrackId);
        trackModel.remove(getTestTrackId);
        trackModel.remove(updNewTrackId);
        artistModel.remove(putTestArtistName);
        artistModel.remove(getTestArtistName);
        artistModel.remove(updTestArtistName);
        artistModel.remove(removeTestArtistName);
    }
}
