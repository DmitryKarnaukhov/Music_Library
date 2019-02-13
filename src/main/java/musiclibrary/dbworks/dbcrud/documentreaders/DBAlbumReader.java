package musiclibrary.dbworks.dbcrud.documentreaders;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import musiclibrary.entities.Album;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBAlbumReader {
    public ArrayList<Album> findDBAlbum(MongoCollection albumMongoCollection, Map queryMap) {
        FindIterable<Document> docs = albumMongoCollection.find(new BasicDBObject(queryMap));
        ArrayList<Album> resultAlbum = new ArrayList<>();
        for (Document doc : docs) {
            int id = (int)doc.get("_id");
            String name = (String)doc.get("name");
            Album album = new Album(id, name);
            resultAlbum.add(album);
        }
        return resultAlbum;
    }
}
