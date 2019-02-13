package musiclibrary.dbworks.dbcrud.documentcreators;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import musiclibrary.dbworks.dbfactories.dbabstractfactories.DBObjectAbstractCreator;
import musiclibrary.entities.Album;
import musiclibrary.entities.Entity;

public class DBAlbumCreator extends DBObjectAbstractCreator {
    public DBObject createAlbumDBObject(Entity entity) {
        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
        Album album = (Album) entity;

        docBuilder.append("name", album.getName());
        return docBuilder.get();
    }

    @Override
    public DBObject createDBObject(Entity entity) {
        return createAlbumDBObject(entity);
    }
}
