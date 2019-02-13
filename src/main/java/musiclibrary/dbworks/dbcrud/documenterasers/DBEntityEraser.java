package musiclibrary.dbworks.dbcrud.documenterasers;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import musiclibrary.entities.Album;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBEntityEraser {
    public void deleteDBEntity(MongoCollection mongoCollection, Map queryMap) {
        mongoCollection.findOneAndDelete(new BasicDBObject(queryMap));
    }
}
