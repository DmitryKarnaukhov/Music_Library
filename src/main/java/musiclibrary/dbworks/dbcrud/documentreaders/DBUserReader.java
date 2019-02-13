package musiclibrary.dbworks.dbcrud.documentreaders;

import com.google.common.collect.ImmutableList;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import musiclibrary.entities.Album;
import musiclibrary.entities.TrackList;
import musiclibrary.entities.User;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBUserReader {
    public ArrayList<User> findDBUser(MongoCollection userMongoCollection, Map queryMap) {
        FindIterable<Document> docs = userMongoCollection.find(new BasicDBObject(queryMap));
        ArrayList<User> resultUser = new ArrayList<>();
        for (Document doc : docs) {
            int id = (int)doc.get("_id");
            String name = (String)doc.get("name");
            ImmutableList<TrackList> trackLists = (ImmutableList<TrackList>) doc.get("name");
            User user = new User(id, name, trackLists);
            resultUser.add(user);
        }
        return resultUser;
    }
}
