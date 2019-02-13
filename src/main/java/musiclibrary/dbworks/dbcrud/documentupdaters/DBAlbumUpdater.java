package musiclibrary.dbworks.dbcrud.documentupdaters;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import musiclibrary.entities.Album;
import musiclibrary.entities.Entity;

public class DBAlbumUpdater {
    public DBObject createAlbumDBObject(Entity entity) {
        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
        Album album = (Album) entity;

        docBuilder.append("name", album.getName());
        return docBuilder.get();
    }
}
