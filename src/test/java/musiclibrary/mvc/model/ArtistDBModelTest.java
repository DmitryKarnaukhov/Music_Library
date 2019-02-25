package musiclibrary.mvc.model;

import musiclibrary.entities.Artist;
import musiclibrary.mvc.model.oldmongomodels.ArtistDBModel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class ArtistDBModelTest {
    ArtistDBModel model = new ArtistDBModel();
    String putTestArtistName = "Elvis";
    String getTestArtistName = "Marry";
    String updateTestArtistNameBefore = "Genry";
    String updateTestArtistNameAfter = "Denis";
    String removeTestArtistName = "Bob";

    @Test
    public void put() {
        model.put(putTestArtistName);
        Artist artistFromDB = model.getItem(putTestArtistName);
        Assert.assertEquals(putTestArtistName, artistFromDB.getName());
    }

    @Test
    public void getItem() {
        model.put(getTestArtistName);
        Artist artist = model.getItem(getTestArtistName);
        Assert.assertEquals(getTestArtistName, artist.getName());
    }

    @Test
    public void update() {
        Artist testArtist = new Artist(model.getNextId(), updateTestArtistNameBefore);
        model.put(testArtist);
        int testArtistId = model.getItem(testArtist.getName()).getId();
        String newName = updateTestArtistNameAfter;
        model.update(testArtistId, testArtist.getName(), newName);
        Assert.assertEquals(model.getItem(testArtistId).getName(), newName);
    }

    @Test
    public void remove() {
        Artist testArtist = new Artist(model.getNextId(), removeTestArtistName);
        model.put(testArtist);
        model.remove(testArtist.getName());
        boolean isGotException = false;
        try {
            model.getItem(testArtist.getName());
        } catch (NullPointerException e) {
            isGotException = true;
        }
        Assert.assertTrue(isGotException);
    }

    @After
    public void deleteTestDocuments() {
        model.remove(putTestArtistName);
        model.remove(getTestArtistName);
        model.remove(updateTestArtistNameBefore);
        model.remove(updateTestArtistNameAfter);
    }
}