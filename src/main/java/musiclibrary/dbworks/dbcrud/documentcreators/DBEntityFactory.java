package musiclibrary.dbworks.dbcrud.documentcreators;

import com.mongodb.DBObject;
import com.mongodb.DBRef;
import com.mongodb.client.MongoCollection;
import musiclibrary.dbworks.dbfactories.dbabstractfactories.DBObjectAbstractCreator;
import musiclibrary.entities.*;
import org.bson.Document;

public class DBEntityFactory {
    public void createDBdocument(Entity entity, MongoCollection collection) {
        DBObject doc = chooseEntityFactory(entity);
        collection.insertOne(doc);
        DBRef ref = new DBRef("coll", "3");
    }

    private DBObject chooseEntityFactory(Entity entity) {
        DBObject dbObject = null;
        DBObjectAbstractCreator creator = null;
        if (entity instanceof Album) {
            creator = new DBAlbumCreator();
            dbObject = creator.createDBObject(entity);
        } else if (entity instanceof Artist) {
            creator = new DBArtistCreator();
            dbObject = creator.createDBObject(entity);
        } else if (entity instanceof Track) {
            creator = new DBTrackCreator();
            dbObject = creator.createDBObject(entity);
        } else if (entity instanceof TrackList) {
            creator = new DBTrackListCreator();
            dbObject = creator.createDBObject(entity);
        } else if (entity instanceof User) {
            creator = new DBUserCreator();
            dbObject = creator.createDBObject(entity);
        }
        return dbObject;
    }
}
