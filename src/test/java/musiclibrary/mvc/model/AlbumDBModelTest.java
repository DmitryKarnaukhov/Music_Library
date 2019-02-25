package musiclibrary.mvc.model;

import musiclibrary.entities.Album;
import musiclibrary.mvc.model.oldmongomodels.AlbumDBModel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class AlbumDBModelTest {
    AlbumDBModel model = new AlbumDBModel();
    String putTestAlbumName = "Album 1";
    String getTestAlbumName = "Album 2";
    String updateTestAlbumNameBefore = "Album 3";
    String updateTestAlbumNameAfter = "Album 4";
    String removeTestAlbumName = "Album 5";

    @Test
    public void put() {
        model.put(putTestAlbumName);
        Album albumFromDB = model.getItem(putTestAlbumName);
        Assert.assertEquals(putTestAlbumName, albumFromDB.getName());
    }

    @Test
    public void getItem() {
        model.put(getTestAlbumName);
        Album album = model.getItem(getTestAlbumName);
        Assert.assertEquals(getTestAlbumName, album.getName());
    }

    @Test
    public void update() {
        Album testAlbum = new Album(model.getNextId(), updateTestAlbumNameBefore);
        model.put(testAlbum);
        int testAlbumId = model.getItem(testAlbum.getName()).getId();
        String newName = updateTestAlbumNameAfter;
        model.update(testAlbumId, testAlbum.getName(), newName);
        Assert.assertEquals(model.getItem(testAlbumId).getName(), newName);
    }

    @Test
    public void remove() {
        Album testAlbum = new Album(model.getNextId(), removeTestAlbumName);
        model.put(testAlbum);
        model.remove(testAlbum.getName());
        boolean isGotException = false;
        try {
            model.getItem(testAlbum.getName());
        } catch (NullPointerException e) {
            isGotException = true;
        }
        Assert.assertTrue(isGotException);
    }

    @After
    public void deleteTestDocuments() {
        model.remove(putTestAlbumName);
        model.remove(getTestAlbumName);
        model.remove(updateTestAlbumNameBefore);
        model.remove(updateTestAlbumNameAfter);
    }

}