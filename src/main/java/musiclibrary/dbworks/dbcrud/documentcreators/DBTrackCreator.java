package musiclibrary.dbworks.dbcrud.documentcreators;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import musiclibrary.dbworks.dbfactories.dbabstractfactories.DBObjectAbstractCreator;
import musiclibrary.entities.Entity;
import musiclibrary.entities.Track;

public class DBTrackCreator extends DBObjectAbstractCreator {
    public DBObject createTrackDBObject(Entity entity) {
        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
        Track track = (Track) entity;

        docBuilder.append("name", track.getName());
        docBuilder.append("artist", track.getArtist());
        docBuilder.append("trackLength", track.getTrackLenght());
        docBuilder.append("name", track.getGenre());
        return docBuilder.get();
    }

    @Override
    public DBObject createDBObject(Entity entity) {
        return createTrackDBObject(entity);
    }
}
