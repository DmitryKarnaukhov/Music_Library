package musiclibrary.dbworks.dbcrud.documentreaders;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import musiclibrary.entities.Album;
import musiclibrary.entities.Artist;
import musiclibrary.entities.Genre;
import musiclibrary.entities.Track;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBTrackReader {
    public ArrayList<Track> findDBTrack(MongoCollection trackMongoCollection, Map queryMap) {
        FindIterable<Document> docs = trackMongoCollection.find(new BasicDBObject(queryMap));
        ArrayList<Track> resultTrack = new ArrayList<>();
        for (Document doc : docs) {
            int id = (int)doc.get("_id");
            String name = (String)doc.get("name");
            Artist artist = (Artist) doc.get("artist");
            int trackLength = (int)doc.get("trackLength");
            Genre genre = (Genre)doc.get("genre");
            Track track = new Track(id, name, artist, trackLength, genre);
            resultTrack.add(track);
        }
        return resultTrack;
    }
}
