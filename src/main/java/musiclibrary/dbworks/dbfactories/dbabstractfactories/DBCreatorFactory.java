package musiclibrary.dbworks.dbfactories.dbabstractfactories;

import com.mongodb.DBObject;
import musiclibrary.dbworks.dbcrud.documentcreators.*;
import musiclibrary.entities.*;

public class DBCreatorFactory {

    public DBObject createDBObject(Entity entity) {
        DBObjectAbstractCreator entityFactory;
        if (entity instanceof Album) {
            entityFactory = new DBAlbumCreator();
        } else if (entity instanceof Artist) {
            entityFactory = new DBArtistCreator();
        } else if (entity instanceof Track) {
            entityFactory = new DBTrackCreator();
        } else if (entity instanceof TrackList) {
            entityFactory = new DBTrackListCreator();
        } else if (entity instanceof User) {
            entityFactory = new DBUserCreator();
        } else throw new NullPointerException("entityFactory initialization exception");
        return entityFactory.createDBObject(entity);
    }
}
