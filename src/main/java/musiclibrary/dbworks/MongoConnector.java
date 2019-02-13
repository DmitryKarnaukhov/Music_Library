package musiclibrary.dbworks;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import musiclibrary.entities.Artist;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.Properties;

public class MongoConnector {
    private MongoClient mongoClient;
    private MongoDatabase musicLibDB;
    public String musName;

    public MongoConnector() {
        mongoClient = new MongoClient("localhost");
        musicLibDB = mongoClient.getDatabase("MusicLibraryDB");
        MongoCollection artistColl = musicLibDB.getCollection("Artist");
        BasicDBObject allQuery = new BasicDBObject();
        BasicDBObject fields = new BasicDBObject();
        fields.put("name", 1);
        DBObject obj = new BasicDBObject("name", "Tod");
        artistColl.insertOne(obj);

        Object o = artistColl.find(new BasicDBObject("name", "hill")).first();
        artistColl.find(new BasicDBObject(new HashMap()));
        FindIterable<Document> docs = artistColl.find(new BasicDBObject(new HashMap()));
        for (Document doc : docs) {
            Object value = doc.get("name");
        }
        MongoIterable mongoIterable = artistColl.find(new BasicDBObject(new HashMap())).map(name -> name == "s");
    }
}
