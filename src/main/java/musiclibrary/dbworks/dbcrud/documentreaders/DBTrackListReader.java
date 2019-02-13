package musiclibrary.dbworks.dbcrud.documentreaders;

import com.google.common.collect.ImmutableList;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import musiclibrary.entities.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBTrackListReader {
    public ArrayList<TrackList> findDBTrackList(MongoCollection trackListMongoCollection, Map queryMap) {
        FindIterable<Document> docs = trackListMongoCollection.find(new BasicDBObject(queryMap));
        ArrayList<TrackList> resultTrackList = new ArrayList<>();
        for (Document doc : docs) {
            int id = (int)doc.get("_id");
            Album album = (Album) doc.get("album");
            ImmutableList<Track> tracks = (ImmutableList<Track>)doc.get("tracks");
            TrackList trackList = new TrackList(id, album, tracks);
            resultTrackList.add(trackList);
        }
        return resultTrackList;
    }
}
