package musiclibrary.mvc.model.oldmongomodels;

import com.mongodb.DBRef;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import musiclibrary.entities.Artist;
import musiclibrary.entities.Genre;
import musiclibrary.entities.Track;
import musiclibrary.mvc.model.Model;
import org.bson.Document;

import java.util.ArrayList;

import static musiclibrary.dbworks.dbconstants.DBconstants.*;

public class TrackDBModel extends Model {
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection trackColl;
    private MongoCollection artistColl;

    public TrackDBModel(String host, String databaseName, String collName, String secondCollName) {
        mongoClient = new MongoClient(host);//"localhost"
        mongoDatabase = mongoClient.getDatabase(databaseName);//"MusicLibraryDB"
        trackColl = mongoDatabase.getCollection(collName);
        artistColl = mongoDatabase.getCollection(secondCollName);
        artistColl.drop(); //TODO: Delete this after debugging
        artistColl = mongoDatabase.getCollection(secondCollName);
        // TODO: delete next four lines when finish tests
        trackColl.countDocuments();
        trackColl.drop();
        trackColl = mongoDatabase.getCollection(collName);//COLLECTION_ARTISTS
        trackColl.countDocuments();
    }

    public TrackDBModel() {
        this(DBHOST, DBNAME, COLLECTION_TRACKS, COLLECTION_ARTISTS);
    }

    public void put(Track track) {
        Document document = new Document();
        Document artistDoc = new Document("name", track.getArtist().getName());
        Document artistDocFindResult = (Document) artistColl.find(artistDoc).first();
        Object artistId = artistDocFindResult.get("_id");
        DBRef artistRef = new DBRef(COLLECTION_ARTISTS, artistId);
        document.append("id", track.getId())
                .append("name", track.getName())
                .append("artist", artistRef)
                .append("trackLenght", track.getTrackLenght())
                .append("genre", track.getGenre().toString());
        trackColl.insertOne(document);
    }

    public void put(String trackName, Artist artist, double trackLenght, Genre genre) {
        int id = getNextId();
        put(new Track(id, trackName, artist, trackLenght, genre));
    }

    public boolean remove(int id) {
        Document document = new Document("id", id);
        return trackColl.deleteOne(document).wasAcknowledged();
    }

    public ArrayList<Track> getItems(String trackName, Artist artist, double trackLenght, Genre genre){
        Document docToFind = new Document();
        if (trackName != null && trackName != "") {
            docToFind.append("name", trackName);
        }
        if (artist != null) {
            Document artistDoc = new Document("id", artist.getId());
            Document artistFindDoc = (Document) artistColl.find(artistDoc).first();
            docToFind.append("artist", new DBRef(COLLECTION_ARTISTS, artistFindDoc.get("_id")));
        }
        if (trackLenght > 0) {
            docToFind.append("trackLenght", trackLenght);
        }
        if (genre != Genre.none) {
            docToFind.append("genre", genre);
        }
        FindIterable<Document> resultDocs = trackColl.find(docToFind);
        ArrayList<Track> returnList = new ArrayList<>();
        for (Document doc : resultDocs) {
            Document docRefArtist = (Document) artistColl.find(
                    new Document("id", ((DBRef)doc.get("artist")).getId())).first();
            Artist refArtist = new Artist((int)docRefArtist.get("id"), (String)docRefArtist.get("name"));
            Track foundTrack = new Track((int)doc.get("id"),
                    (String)doc.get("name"),
                    refArtist,
                    (double)doc.get("trackLenght"),
                    (Genre)doc.get("genre"));
            returnList.add(foundTrack);
        }
        return returnList;
    }

    public Track getItem(int id) {
        Document trackFindDoc = new Document("id", id);
        Document resultTrack = (Document)trackColl.find(trackFindDoc).first();

        Document resultArtist = (Document)artistColl.find(new Document("_id",
                ((DBRef)resultTrack.get("artist")).getId())).first();
        Artist artist = new Artist((int)resultArtist.get("id"), (String)resultArtist.get("name"));
        return  new Track((int)resultTrack.get("id"), (String)resultTrack.get("name"),
                artist, (double)resultTrack.get("trackLenght"),
                Genre.valueOf((String)resultTrack.get("genre")));
    }

    public Track getItem(String trackName, Artist artist, double trackLenght, Genre genre){
        Document docToFind = new Document();
        if (trackName != null && trackName != "") {
            docToFind.append("name", trackName);
        }
        if (artist != null) {
            Document artistDoc = new Document("id", artist.getId());
            Document artistFindDoc = (Document) artistColl.find(artistDoc).first();
            docToFind.append("artist", new DBRef(COLLECTION_ARTISTS, artistFindDoc.get("_id")));
        }
        if (trackLenght > 0) {
            docToFind.append("trackLenght", trackLenght);
        }
        if (genre != Genre.none) {
            docToFind.append("genre", genre);
        }
        Document resultDoc = (Document) trackColl.find(docToFind).first();
//        Document docRefArtist = (Document) artistColl.find(
//                new Document("id", ((DBRef)resultDoc.get("artist")).getId())).first(); // TODO:!!!!!!!!!!!!

        Object objectId = ((DBRef)resultDoc.get("artist")).getId();
        Document resultArtist = (Document)artistColl.find(new Document("_id",
                objectId)).first();
        Artist refArtist = new Artist((int)resultArtist.get("id"), (String)resultArtist.get("name"));


//        Artist refArtist = new Artist((int)docRefArtist.get("id"), (String)docRefArtist.get("name"));
        Track foundTrack = new Track((int)resultDoc.get("id"),
                (String)resultDoc.get("name"),
                refArtist,
                (double)resultDoc.get("trackLenght"),
                Genre.valueOf((String) resultDoc.get("genre")));
        return foundTrack;
    }

    public void update(Track newTrack) {
        remove(newTrack.getId());
        put(newTrack);
    }

    public ArrayList<Track> getAll() {
        ArrayList<Track> list = new ArrayList<>();
        FindIterable<Document> iter = trackColl.find();
        for (Document doc : iter) {
            DBRef refOnArtist = (DBRef)doc.get("artist");
            Document artistDoc = (Document) artistColl.find(new Document("id", refOnArtist.getId())).first();
            Artist artist = new Artist((int)artistDoc.get("id"), (String)artistDoc.get("name"));
            Track track = new Track((int)doc.get("id"),
                    (String) doc.get("name"),
                    artist,
                    (double)doc.get("trackList"),
                    (Genre)doc.get("genre"));
            list.add(track);
        }
        return list;
    }

    public int getNextId() {
        FindIterable<Document> queryResult = trackColl.find();
        int maxId = -1;
        for (Document document : queryResult) {
            int id = (int)document.get("id");
            if (id > maxId) {
                maxId = id;
            }
        }
        return  maxId + 1;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        mongoClient.close();
    }
}
