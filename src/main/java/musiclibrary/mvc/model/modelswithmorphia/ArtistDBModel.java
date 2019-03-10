package musiclibrary.mvc.model.modelswithmorphia;

import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import musiclibrary.entities.Artist;
import musiclibrary.mvc.model.Model;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.List;

import static musiclibrary.dbworks.dbconstants.DBconstants.DBNAME;

public class ArtistDBModel extends Model<Artist> {
    Morphia morphia;
    MongoClient mongoClient;
    Datastore ds;

    public ArtistDBModel() {
        morphia = new Morphia();
        mongoClient = new MongoClient();
        ds = morphia.createDatastore(mongoClient, DBNAME);
    }

    public void put(Artist artist) {
        ds.save(artist);
    }

    public void put(int id, Artist artist) {
        put(new Artist(getNextId(), artist.getName()));
    }

    public boolean remove(int id) {
        Query<Artist> query = ds.createQuery(Artist.class)
                .field("_id").equal(id);
        WriteResult result = ds.delete(query);
        return result.wasAcknowledged();
    }

    public boolean remove(String name) {
        Query<Artist> query = ds.createQuery(Artist.class)
                .field("name").equal(name);
        WriteResult result = ds.delete(query);
        return result.wasAcknowledged();
    }

    public Artist getItem(String artistName){
        Query<Artist> query = ds.createQuery(Artist.class)
                .field("name").equal(artistName);
        return query.get();
    }

    public Artist getItem(int id){
        Query<Artist> query = ds.createQuery(Artist.class)
                .field("_id").equal(id);
        return query.get();
    }

    public List<Artist> getItems(String artistName) {
        Query<Artist> query = ds.createQuery(Artist.class)
                .field("name").equal(artistName);
        return query.asList();
    }

    public List<Artist> getItems() {
        Query<Artist> query = ds.createQuery(Artist.class);
        return query.asList();
    }

    public void update(Artist artist) {
        Query<Artist> query = ds.createQuery(Artist.class)
                .field("_id").equal(artist.getId());
        UpdateOperations<Artist> updateOperation = ds.createUpdateOperations(Artist.class)
                .set("name", artist.getName());
        ds.update(query, updateOperation);
    }

    public int getNextId() {
        Query<Artist> query = ds.createQuery(Artist.class);
        if (query.count() > 0) {
            FindOptions findOptions = new FindOptions().limit(1);
            return ds.createQuery(Artist.class)
                    .order("-_id").get(findOptions).getId() + 1;
        }
        return 0;
    }
}