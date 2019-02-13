package musiclibrary.dbworks.dbcrud.documentcreators;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import musiclibrary.dbworks.dbfactories.dbabstractfactories.DBObjectAbstractCreator;
import musiclibrary.entities.Entity;
import musiclibrary.entities.TrackList;

public class DBTrackListCreator extends DBObjectAbstractCreator {
    public DBObject createTrackListDBObject(Entity entity) {
        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
        TrackList trackList = (TrackList) entity;

        docBuilder.append("album", trackList.getGetAlbum());
        docBuilder.append("tracks", trackList.getTracks());
        return docBuilder.get();
    }

    @Override
    public DBObject createDBObject(Entity entity) {
        return createTrackListDBObject(entity);
    }
}
