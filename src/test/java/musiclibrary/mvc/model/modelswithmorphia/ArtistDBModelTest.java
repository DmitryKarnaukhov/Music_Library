package musiclibrary.mvc.model.modelswithmorphia;

import com.mongodb.MongoClient;
import musiclibrary.dbworks.dbconstants.DBconstants;
import musiclibrary.entities.Artist;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import static org.junit.Assert.*;

public class ArtistDBModelTest {
    Morphia morphia;
    MongoClient mongoClient;
    Datastore datastore;
    ArtistDBModel artistModel;

    String putTestArtistName = "Elvis Presley";
    String getTestArtistName = "John Lennon";
    String updateTestArtistNameBefore = "Eminem";
    String updateTestArtistNameAfter = "Radiohead";
    String removeTestArtistName = "Bob";

    public ArtistDBModelTest() {
        morphia = new Morphia();
        mongoClient = new MongoClient();
        datastore = morphia.createDatastore(mongoClient, DBconstants.DBNAME);
        artistModel = new ArtistDBModel();
    }

    @Test
    public void put() {
        Artist testArtist = new Artist(artistModel.getNextId(), putTestArtistName);
        artistModel.put(testArtist);
        Artist putArtistFromDB = artistModel.getItem(putTestArtistName);
        Assert.assertEquals(putTestArtistName, putArtistFromDB.getName());
    }

    @Test
    public void getItem() {
        Artist testArtist = new Artist(artistModel.getNextId(), getTestArtistName);
        artistModel.put(testArtist);
        Artist artistFromDB = artistModel.getItem(getTestArtistName);
        Assert.assertEquals(getTestArtistName, artistFromDB.getName());
    }

    @Test
    public void update() {
        Artist testArtist = new Artist(artistModel.getNextId(), updateTestArtistNameBefore);
        artistModel.put(testArtist);
        Artist updatedArtist = new Artist(testArtist.getId(), updateTestArtistNameAfter);
        artistModel.update(updatedArtist);
        Artist artistFromDB = artistModel.getItem(updatedArtist.getId());
        Assert.assertEquals(updateTestArtistNameAfter, artistFromDB.getName());
    }

    @Test
    public void remove() {
        Artist testArtist = new Artist(artistModel.getNextId(), removeTestArtistName);
        artistModel.put(testArtist);
        artistModel.remove(testArtist.getId());
        Artist deletedArtist = null;
        deletedArtist = artistModel.getItem(testArtist.getId());
        Assert.assertNull(deletedArtist);
    }

    @After
    public void deleteTestDocuments() {
        artistModel.remove(putTestArtistName);
        artistModel.remove(getTestArtistName);
        artistModel.remove(updateTestArtistNameAfter);
    }
}