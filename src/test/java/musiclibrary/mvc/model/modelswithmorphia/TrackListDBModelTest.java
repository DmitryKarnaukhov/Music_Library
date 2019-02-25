package musiclibrary.mvc.model.modelswithmorphia;

import com.google.common.collect.ImmutableList;
import com.mongodb.MongoClient;
import musiclibrary.dbworks.dbconstants.DBconstants;
import musiclibrary.entities.*;
import org.junit.Assert;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TrackListDBModelTest {
    Morphia morphia;
    MongoClient mongoClient;
    Datastore datastore;
    ArtistDBModel artistModel;
    TrackDBModel trackModel;
    TrackListDBModel trackListModel;
    AlbumDBModel albumModel;

    String testArtistNameNo1 = "Elvis Presley";
    String testArtistNameNo2 = "John Lennon";
    String testArtistNameNo3 = "Eminem";
    String testArtistNameNo4 = "Radiohead";

    String testAlbumName = "Mixed Tracks";

    String testTrackNameNo1 = "Blue Shoes";
    String testTrackNameNo2 = "Imagine";
    String testTrackNameNo3 = "Stan";
    String testTrackNameNo4 = "Lose Yourself";
    String testTrackNameNo5 = "Creep";

    Track testTrackNo1,
            testTrackNo2,
            testTrackNo3,
            testTrackNo4,
            testTrackNo5;
    Artist testArtistNo1,
            testArtistNo2,
            testArtistNo3,
            testArtistNo4;
    ImmutableList<Track> tracks;
    Album testAlbum;

    public TrackListDBModelTest() {
        morphia = new Morphia();
        mongoClient = new MongoClient();
        datastore = morphia.createDatastore(mongoClient, DBconstants.DBNAME);
        artistModel = new ArtistDBModel();
        trackModel = new TrackDBModel();
        trackListModel = new TrackListDBModel();
        albumModel = new AlbumDBModel();

        initArtists();
        initTracks();
        testAlbum = new Album(albumModel.getNextId(), testAlbumName);
        albumModel.put(testAlbum);
    }

    private void initArtists() {
        testArtistNo1 = new Artist(artistModel.getNextId(), testArtistNameNo1);
        artistModel.put(testArtistNo1);
        testArtistNo2 = new Artist(artistModel.getNextId(), testArtistNameNo2);
        artistModel.put(testArtistNo2);
        testArtistNo3 = new Artist(artistModel.getNextId(), testArtistNameNo3);
        artistModel.put(testArtistNo3);
        testArtistNo4 = new Artist(artistModel.getNextId(), testArtistNameNo4);
        artistModel.put(testArtistNo4);
    }

    private void initTracks() {
        testTrackNo1 = new Track(trackModel.getNextId(),
                testTrackNameNo1, testArtistNo1, 3.45, Genre.Rock);
        trackModel.put(testTrackNo1);
        testTrackNo2 = new Track(trackModel.getNextId(),
                testTrackNameNo2, testArtistNo2, 2.45, Genre.Pop);
        trackModel.put(testTrackNo2);
        testTrackNo3 = new Track(trackModel.getNextId(),
                testTrackNameNo3, testArtistNo3, 5.55, Genre.Electronic);
        trackModel.put(testTrackNo3);
        testTrackNo4 = new Track(trackModel.getNextId(),
                testTrackNameNo4, testArtistNo3, 3.25, Genre.Dance);
        trackModel.put(testTrackNo4);
        testTrackNo5 = new Track(trackModel.getNextId(),
                testTrackNameNo5, testArtistNo4, 1.55, Genre.Rock);
        trackModel.put(testTrackNo5);
        ArrayList<Track> trList = new ArrayList();
        trList.add(testTrackNo1);
        trList.add(testTrackNo2);
        trList.add(testTrackNo3);
        trList.add(testTrackNo4);
        trList.add(testTrackNo5);
        tracks = ImmutableList.copyOf(trList);

    }

    @Test
    public void put() {
        TrackList testTrackList = new TrackList(trackListModel.getNextId(), testAlbum, tracks);
        trackListModel.put(testTrackList);
        TrackList putTrackListFromDB = trackListModel.getItem(testTrackList.getId());
        Assert.assertEquals(testTrackList, putTrackListFromDB);
    }
}