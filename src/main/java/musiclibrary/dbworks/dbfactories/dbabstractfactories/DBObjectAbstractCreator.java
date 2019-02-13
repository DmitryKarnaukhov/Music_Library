package musiclibrary.dbworks.dbfactories.dbabstractfactories;

import com.mongodb.DBObject;
import musiclibrary.entities.*;

public abstract class DBObjectAbstractCreator {
    public abstract DBObject createDBObject(Entity entity);
}
