package musiclibrary.mvc.model.modelswithmorphia;

import com.mongodb.MongoClient;
import musiclibrary.dbworks.dbconstants.DBconstants;
import musiclibrary.entities.Artist;
import musiclibrary.entities.Genre;
import musiclibrary.entities.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class TrackDBModelTest {
    Morphia morphia;
    MongoClient mongoClient;
    Datastore datastore;
    ArtistDBModel artistModel;
    TrackDBModel trackModel;

    String putTestArtistName = "Elvis Presley";
    String getTestArtistName = "John Lennon";
    String updateTestArtistName = "Eminem";
    String removeTestArtistName = "Radiohead";

    String putTestTrackName = "Blue Shoes";
    String getTestTrackName = "Imagine";
    String updateTestTrackNameBefore = "Stan";
    String updateTestTrackNameAfter = "Lose Yourself";
    String removeTestTrackName = "Creep";

    public TrackDBModelTest() {
        morphia = new Morphia();
        mongoClient = new MongoClient();
        datastore = morphia.createDatastore(mongoClient, DBconstants.DBNAME);
        artistModel = new ArtistDBModel();
        trackModel = new TrackDBModel();
    }

    @After
    public void cleanUp() {
        datastore.delete(datastore.createQuery(Track.class));
        datastore.delete(datastore.createQuery(Artist.class));
    }

    @Test
    public void put() {
        Artist testArtist = new Artist(artistModel.getNextId(), putTestArtistName);
        Track testTrack = new Track(trackModel.getNextId(),
                putTestTrackName, testArtist,3.35, Genre.Rock);
        artistModel.put(testArtist);
        trackModel.put(testTrack);
        Track putTrackFromDB = trackModel.getItem(putTestTrackName);
        Assert.assertEquals(putTestTrackName, putTrackFromDB.getName());
    }

    @Test
    public void getItem() {
        Artist testArtist = new Artist(artistModel.getNextId(), getTestArtistName);
        Track testTrack = new Track(trackModel.getNextId(),
                getTestTrackName, testArtist, 3.55, Genre.Pop);
        artistModel.put(testArtist);
        trackModel.put(testTrack);
        Track trackFromDB = trackModel.getItem(getTestTrackName);
        Assert.assertEquals(getTestTrackName, trackFromDB.getName());
    }

    @Test
    public void update() {
        Artist testArtist = new Artist(artistModel.getNextId(), updateTestArtistName);
        Track testTrack = new Track(trackModel.getNextId(),
                updateTestTrackNameBefore, testArtist, 5.25, Genre.Rap);
        artistModel.put(testArtist);
        trackModel.put(testTrack);
        Track updatedTrack = new Track(testTrack.getId(),
                updateTestTrackNameAfter, testTrack.getArtist(),
                testTrack.getTrackLenght(), testTrack.getGenre());
        trackModel.update(updatedTrack);
        Track trackFromDB = trackModel.getItem(updatedTrack.getId());
        Assert.assertEquals(updateTestTrackNameAfter, trackFromDB.getName());
    }

    @Test
    public void remove() {
        Artist testArtist = new Artist(artistModel.getNextId(), removeTestArtistName);
        Track testTrack = new Track(trackModel.getNextId(),
                removeTestTrackName, testArtist, 4.82, Genre.Rock);
        artistModel.put(testArtist);
        trackModel.put(testTrack);
        trackModel.remove(testTrack.getId());
        Track deletedTrack = null;
        deletedTrack = trackModel.getItem(testTrack.getId());
        Assert.assertNull(deletedTrack);
    }

    @After
    public void deleteTestDocuments() {
        trackModel.remove(putTestTrackName);
        trackModel.remove(getTestTrackName);
        trackModel.remove(updateTestTrackNameAfter);

        artistModel.remove(putTestArtistName);
        artistModel.remove(getTestArtistName);
        artistModel.remove(updateTestArtistName);
        artistModel.remove(removeTestArtistName);
    }
}