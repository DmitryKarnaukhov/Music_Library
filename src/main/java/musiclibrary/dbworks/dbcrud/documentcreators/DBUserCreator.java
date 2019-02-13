package musiclibrary.dbworks.dbcrud.documentcreators;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import musiclibrary.dbworks.dbfactories.dbabstractfactories.DBObjectAbstractCreator;
import musiclibrary.entities.Entity;
import musiclibrary.entities.User;

public class DBUserCreator extends DBObjectAbstractCreator {
    public static DBObject createUserDBObject(Entity entity) {
        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
        User user = (User) entity;

        docBuilder.append("name", user.getName());
        docBuilder.append("tracks", user.getTrackLists());
        return docBuilder.get();
    }

    @Override
    public DBObject createDBObject(Entity entity) {
        return createUserDBObject(entity);
    }
}
