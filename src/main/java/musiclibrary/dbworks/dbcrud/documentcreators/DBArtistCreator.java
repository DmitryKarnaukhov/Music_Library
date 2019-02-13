package musiclibrary.dbworks.dbcrud.documentcreators;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import musiclibrary.dbworks.dbfactories.dbabstractfactories.DBObjectAbstractCreator;
import musiclibrary.entities.Artist;
import musiclibrary.entities.Entity;

public class DBArtistCreator extends DBObjectAbstractCreator {
    public DBObject createArtistDBObject(Entity entity) {
        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
        Artist artist = (Artist) entity;

        docBuilder.append("name", artist.getName());
        return docBuilder.get();
    }

    @Override
    public DBObject createDBObject(Entity entity) {
        return createArtistDBObject(entity);
    }
}
