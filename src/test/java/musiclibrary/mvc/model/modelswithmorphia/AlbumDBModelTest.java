package musiclibrary.mvc.model.modelswithmorphia;

import com.mongodb.MongoClient;
import musiclibrary.dbworks.dbconstants.DBconstants;
import musiclibrary.entities.Album;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class AlbumDBModelTest {
    Morphia morphia;
    MongoClient mongoClient;
    Datastore datastore;
    AlbumDBModel albumModel;

    String putTestAlbumName = "Album #1";
    String getTestAlbumName = "Album #2";
    String updateTestAlbumNameBefore = "Album #3";
    String updateTestAlbumNameAfter = "Album #4";
    String removeTestAlbumName = "Album #5";

    public AlbumDBModelTest() {
        morphia = new Morphia();
        mongoClient = new MongoClient();
        datastore = morphia.createDatastore(mongoClient, DBconstants.DBNAME);
        albumModel = new AlbumDBModel();
    }

    @Test
    public void put() {
        Album testAlbum = new Album(albumModel.getNextId(), putTestAlbumName);
        albumModel.put(testAlbum);
        Album putAlbumFromDB = albumModel.getItem(putTestAlbumName);
        Assert.assertEquals(putTestAlbumName, putAlbumFromDB.getName());
    }

    @Test
    public void getItem() {
        Album testAlbum = new Album(albumModel.getNextId(), getTestAlbumName);
        albumModel.put(testAlbum);
        Album albumFromDB = albumModel.getItem(getTestAlbumName);
        Assert.assertEquals(getTestAlbumName, albumFromDB.getName());
    }

    @Test
    public void update() {
        Album testAlbum = new Album(albumModel.getNextId(), updateTestAlbumNameBefore);
        albumModel.put(testAlbum);
        Album updatedAlbum = new Album(testAlbum.getId(), updateTestAlbumNameAfter);
        albumModel.update(updatedAlbum);
        Album albumFromDB = albumModel.getItem(updatedAlbum.getId());
        Assert.assertEquals(updateTestAlbumNameAfter, albumFromDB.getName());
    }

    @Test
    public void remove() {
        Album testAlbum = new Album(albumModel.getNextId(), removeTestAlbumName);
        albumModel.put(testAlbum);
        albumModel.remove(testAlbum.getId());
        Album deletedAlbum = null;
        deletedAlbum = albumModel.getItem(testAlbum.getId());
        Assert.assertNull(deletedAlbum);
    }

    @After
    public void deleteTestDocuments() {
        albumModel.remove(putTestAlbumName);
        albumModel.remove(getTestAlbumName);
        albumModel.remove(updateTestAlbumNameAfter);
    }

}