//package musiclibrary.mvc.model;
//
//import com.google.common.collect.ImmutableList;
//import com.mongodb.DBRef;
//import com.mongodb.MongoClient;
//import com.mongodb.client.FindIterable;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import musiclibrary.entities.*;
//import org.bson.Document;
//
//import java.util.ArrayList;
//
//import static musiclibrary.dbworks.dbconstants.DBconstants.*;
//import static musiclibrary.dbworks.dbconstants.DBconstants.COLLECTION_ARTISTS;
//
//public class TrackListDBModel {
//    private MongoClient mongoClient;
//    private MongoDatabase mongoDatabase;
//    private MongoCollection trackColl;
//    private MongoCollection artistColl;
//    private MongoCollection trackListColl;
//    private MongoCollection albumColl;
//
//    public TrackListDBModel(String host, String databaseName,
//                            String trackCollName, String artistCollName,
//                            String trackListCollName, String albumCollName) {
//        mongoClient = new MongoClient(host);//"localhost"
//        mongoDatabase = mongoClient.getDatabase(databaseName);//"MusicLibraryDB"
//
//        trackColl = mongoDatabase.getCollection(trackCollName);
//        artistColl = mongoDatabase.getCollection(artistCollName);
//        trackListColl = mongoDatabase.getCollection(trackListCollName);
//        albumColl = mongoDatabase.getCollection(albumCollName);
//// TODO: delete next four lines when finish tests
//        albumColl.drop();
//        albumColl = mongoDatabase.getCollection(albumCollName);
//        trackListColl.countDocuments();
//        trackListColl.drop();
//        trackListColl = mongoDatabase.getCollection(trackListCollName);//COLLECTION_TRACKLIST
//        trackListColl.countDocuments();
//        trackColl.drop();
//        trackColl = mongoDatabase.getCollection(trackCollName);
//        artistColl.drop();
//        artistColl = mongoDatabase.getCollection(artistCollName);
//    }
//
//    public TrackListDBModel() {
//        this(DBHOST, DBNAME, COLLECTION_TRACKS,
//                COLLECTION_ARTISTS, COLLECTION_TRACKLIST, COLLECTION_ALBUMS);
//    }
//
//    public void put(TrackList trackList) {
//        ImmutableList<Track> tracks = trackList.getTracks();
//        ArrayList<DBRef> refListOnTracks = new ArrayList<>();
//        for (Track track : tracks) {
//            DBRef trackRef = new DBRef(COLLECTION_TRACKS, track.getId());
//            refListOnTracks.add(trackRef);
//        }
//        DBRef albumRef = new DBRef(COLLECTION_ALBUMS, trackList.getGetAlbum());
//        Document trackListToPut = new Document();
//        trackListToPut.append("id", trackList.getId())
//                .append("album", albumRef)
//                .append("tracks", refListOnTracks);
//        trackListColl.insertOne(trackListToPut);
//    }
//
//    public void put(Album album, ImmutableList<Track> tracks) {
//        int id = getNextId();
//        put(new TrackList(id, album, tracks));
//    }
//
//    public boolean remove(int id) {
//        Document document = new Document("id", id);
//        return trackListColl.deleteOne(document).wasAcknowledged();
//    }
////TODO: stop point
//    public ArrayList<Track> getItems(Album album, Track track){
//        Document docToFind = new Document();
//        if (album != null) {
//            Document findAlbum = new Document("id", album.getId());
//            DBRef albumRef = new DBRef(COLLECTION_ALBUMS,
//                    ((Document)albumColl.find(findAlbum).first()).get("_id"));
//            docToFind.append("album", albumRef);
//        }
//        if (track != null) {
//            FindIterable<Document> trackListDocuments = trackListColl.find();
//            for (Document doc : trackListDocuments) {
//                Document document = new Document("_id", ((DBRef)doc.get("trackList")).getId());
//                Document foundDoc = (Document)trackListColl.find(document);
//
//            }
//        }
//
//        return  new ArrayList<>();
//
//
////        Document docToFind = new Document();
////        if (trackName != null && trackName != "") {
////            docToFind.append("name", trackName);
////        }
////        if (artist != null) {
////            Document artistDoc = new Document("id", artist.getId());
////            Document artistFindDoc = (Document) artistColl.find(artistDoc).first();
////            docToFind.append("artist", new DBRef(COLLECTION_ARTISTS, artistFindDoc.get("_id")));
////        }
////        if (trackLenght > 0) {
////            docToFind.append("trackLenght", trackLenght);
////        }
////        if (genre != Genre.none) {
////            docToFind.append("genre", genre);
////        }
////        FindIterable<Document> resultDocs = trackColl.find(docToFind);
////        ArrayList<Track> returnList = new ArrayList<>();
////        for (Document doc : resultDocs) {
////            Document docRefArtist = (Document) artistColl.find(
////                    new Document("id", ((DBRef)doc.get("artist")).getId())).first();
////            Artist refArtist = new Artist((int)docRefArtist.get("id"), (String)docRefArtist.get("name"));
////            Track foundTrack = new Track((int)doc.get("id"),
////                    (String)doc.get("name"),
////                    refArtist,
////                    (double)doc.get("trackLenght"),
////                    (Genre)doc.get("genre"));
////            returnList.add(foundTrack);
////        }
////        return returnList;
//    }
//
//    public TrackList getItem(Album album){
//        Document albumDoc = new Document("id", album.getId());
//        Object objId = albumDoc.get("_id");
//        DBRef refOnAlbum = new DBRef(COLLECTION_ALBUMS, objId);
//        Document findTrackList = new Document("album", refOnAlbum);
//        Document resultDoc = (Document)trackListColl.find(findTrackList).first();
//        DBRef ref = (DBRef)resultDoc.get("tracks");
//        Document findTracks = new Document("_id", ref.getId());
//        FindIterable<Document> tracksDoc = trackColl.find(findTracks);
//        ArrayList<Track> tracks = new ArrayList<>();
//        for (Document doc : tracksDoc) {
//            Track track = new Track()
//        }
//    }
//
//    public void update(TrackList newTrackList) {
//        remove(newTrackList.getId());
//        put(newTrackList);
//    }
//
//    public ArrayList<Track> getAll() {
//        ArrayList<Track> list = new ArrayList<>();
//        FindIterable<Document> iter = trackColl.find();
//        for (Document doc : iter) {
//            DBRef refOnArtist = (DBRef)doc.get("artist");
//            Document artistDoc = (Document) artistColl.find(new Document("id", refOnArtist.getId())).first();
//            Artist artist = new Artist((int)artistDoc.get("id"), (String)artistDoc.get("name"));
//            Track track = new Track((int)doc.get("id"),
//                    (String) doc.get("name"),
//                    artist,
//                    (double)doc.get("trackList"),
//                    (Genre)doc.get("genre"));
//            list.add(track);
//        }
//        return list;
//    }
//
//    public int getNextId() {
//        FindIterable<Document> queryResult = trackColl.find();
//        int maxId = -1;
//        for (Document document : queryResult) {
//            int id = (int)document.get("id");
//            if (id > maxId) {
//                maxId = id;
//            }
//        }
//        return  maxId + 1;
//    }
//
//    @Override
//    protected void finalize() throws Throwable {
//        super.finalize();
//        mongoClient.close();
//    }
//}
