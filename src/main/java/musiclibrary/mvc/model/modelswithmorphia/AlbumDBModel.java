package musiclibrary.mvc.model.modelswithmorphia;

import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import musiclibrary.entities.Album;
import musiclibrary.entities.Artist;
import musiclibrary.mvc.model.Model;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.List;

import static musiclibrary.dbworks.dbconstants.DBconstants.DBNAME;

public class AlbumDBModel extends Model {
    Morphia morphia;
    MongoClient mongoClient;
    Datastore ds;

    public AlbumDBModel() {
        morphia = new Morphia();
        mongoClient = new MongoClient();
        ds = morphia.createDatastore(mongoClient, DBNAME);
    }

    public void put(Album album) {
        ds.save(album);
    }

    public void put(int id, Album album) {
        Album newAlbum = new Album(id, album.getName());
        ds.save(newAlbum);
    }

    public boolean remove(int id) {
        Query<Album> query = ds.createQuery(Album.class)
                .field("_id").equal(id);
        WriteResult result = ds.delete(query);
        return result.wasAcknowledged();
    }

    public boolean remove(String name) {
        Query<Album> query = ds.createQuery(Album.class)
                .field("name").equal(name);
        WriteResult result = ds.delete(query);
        return result.wasAcknowledged();
    }

    public Album getItem(String albumName){
        Query<Album> query = ds.createQuery(Album.class)
                .field("name").equal(albumName);
        return query.get();
    }

    public Album getItem(int id){
        Query<Album> query = ds.createQuery(Album.class)
                .field("_id").equal(id);
        return query.get();
    }

    public List<Album> getItems(String albumName) {
        Query<Album> query = ds.createQuery(Album.class)
                .field("name").equal(albumName);
        return query.asList();
    }

    public void update(Album album) {
        Query<Album> query = ds.createQuery(Album.class)
                .field("_id").equal(album.getId());
        UpdateOperations<Album> updateOperation = ds.createUpdateOperations(Album.class)
                .set("name", album.getName());
        ds.update(query, updateOperation);
    }

    public int getNextId() {
        Query<Album> query = ds.createQuery(Album.class);
        if (query.count() > 0) {
            FindOptions findOptions = new FindOptions().limit(1);
            return ds.createQuery(Album.class)
                    .order("-_id").get(findOptions).getId() + 1;
        }
        return 0;
    }
}
