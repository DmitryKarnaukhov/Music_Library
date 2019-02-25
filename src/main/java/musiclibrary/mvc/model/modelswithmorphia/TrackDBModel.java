package musiclibrary.mvc.model.modelswithmorphia;

import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import musiclibrary.entities.Artist;
import musiclibrary.entities.Track;
import musiclibrary.mvc.model.Model;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.List;

import static musiclibrary.dbworks.dbconstants.DBconstants.DBNAME;

public class TrackDBModel extends Model {
    Morphia morphia;
    MongoClient mongoClient;
    Datastore ds;

    public TrackDBModel() {
        morphia = new Morphia();
        mongoClient = new MongoClient();
        ds = morphia.createDatastore(mongoClient, DBNAME);
    }

    public void put(Track track) {
        ds.save(track);
    }

    public boolean remove(int id) {
        Query<Track> query = ds.createQuery(Track.class)
                .field("_id").equal(id);
        WriteResult result = ds.delete(query);
        return result.wasAcknowledged();
    }

    public boolean remove(String name) {
        Query<Track> query = ds.createQuery(Track.class)
                .field("name").equal(name);
        WriteResult result = ds.delete(query);
        return result.wasAcknowledged();
    }

    public Track getItem(String trackName){
//        Query q = ds.find(Track.class).field("artist").equal(ds.get(Artist.class, new ObjectId("5693e72244ae9fe4803a4520")));
        Query<Track> query = ds.createQuery(Track.class)
                .field("name").equal(trackName);
//        query.get().getArtist();
        return query.get();
    }

    public Track getItem(int id){
        Query<Track> query = ds.createQuery(Track.class)
                .field("_id").equal(id);
        return query.get();
    }

    public List<Track> getItems(String trackName) {
        Query<Track> query = ds.createQuery(Track.class)
                .field("name").equal(trackName);
        return query.asList();
    }

    public void update(Track track) {
        Query<Track> query = ds.createQuery(Track.class)
                .field("_id").equal(track.getId());
        UpdateOperations<Track> updateOperation = ds.createUpdateOperations(Track.class)
                .set("name", track.getName())
                .set("artist", track.getArtist())
                .set("trackLenght", track.getTrackLenght())
                .set("genre", track.getGenre());
        ds.update(query, updateOperation);
    }

    public int getNextId() {
        Query<Track> query = ds.createQuery(Track.class);
        if (query.count() > 0) {
            FindOptions findOptions = new FindOptions().limit(1);
            return ds.createQuery(Track.class)
                    .order("-_id").get(findOptions).getId() + 1;
        }
        return 0;
    }
}
