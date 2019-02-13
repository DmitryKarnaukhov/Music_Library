package musiclibrary.dbworks.dbcrud.documentreaders;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import musiclibrary.entities.Artist;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBArtistReader {
    public ArrayList<Artist> findDBArtist(MongoCollection artistMongoCollection, Map queryMap) {
        FindIterable<Document> docs = artistMongoCollection.find(new BasicDBObject(queryMap));
        ArrayList<Artist> resultArtist = new ArrayList<>();
        for (Document doc : docs) {
            int id = (int)doc.get("_id");
            String name = (String)doc.get("name");
            Artist artist = new Artist(id, name);
            resultArtist.add(artist);
        }
        return resultArtist;
    }
}
