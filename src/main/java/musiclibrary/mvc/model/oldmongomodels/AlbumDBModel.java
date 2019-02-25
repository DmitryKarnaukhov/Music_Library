package musiclibrary.mvc.model.oldmongomodels;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import musiclibrary.entities.Album;
import musiclibrary.entities.Artist;
import org.bson.Document;

import java.util.ArrayList;

import static musiclibrary.dbworks.dbconstants.DBconstants.*;

public class AlbumDBModel {
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection albumColl;

    public AlbumDBModel(String host, String databaseName, String albumCollName) {
        mongoClient = new MongoClient(host);//"localhost"
        mongoDatabase = mongoClient.getDatabase(databaseName);//"MusicLibraryDB"
        albumColl = mongoDatabase.getCollection(albumCollName);//COLLECTION_ARTISTS
//        albumColl.countDocuments();
//        albumColl.drop();
//        albumColl = mongoDatabase.getCollection(collName);//COLLECTION_ARTISTS
//        albumColl.countDocuments();
    }

    public AlbumDBModel() {
        this(DBHOST, DBNAME, COLLECTION_ALBUMS);
    }

    public void put(Album album) {
//        put(artist.getName());
        Document document = new Document();
        document.append("id", album.getId()).append("name", album.getName());
        albumColl.insertOne(document);
    }

    public void put(String albumName) {
        Document document = new Document();
        document.append("id", getNextId()).append("name", albumName);
        albumColl.insertOne(document);
    }

    public boolean remove(int id) {
        return remove(id, null);
    }

    public boolean remove(String albumName) {
        Document document = new Document("name", albumName);
        return albumColl.deleteOne(document).wasAcknowledged();
    }

    public boolean remove(int id, String albumName) {
        Document docToDel = new Document();
        if(id < 0 && albumName == null)
            throw new IllegalArgumentException("Both arguments have illegal values");
        if (id > 0) {
            docToDel.append("id", id);
        }
        if (albumName != null) {
            docToDel.append("name", albumName);
        }
        if (albumColl.findOneAndDelete(docToDel) != null) {
            return true;
        }
        return false;
    }

    public Album getItem(int id){
        return getItem(id, null);
    }

    public Album getItem(String albumName){
        return getItem(-1, albumName);
    }

    public Album getItem(int id, String albumName){
        Document docToFind = new Document();
        if(id < 0 && albumName == null)
            throw new IllegalArgumentException("Both arguments have illegal values");
        if (id > 0) {
            docToFind.append("id", id);
        }
        if (albumName != null) {
            docToFind.append("name", albumName);
        }
        Object obj = albumColl.find(docToFind).first();
        Document doc = (Document) obj;
        Album album = new Album(((int)doc.get("id")), (String)doc.get("name"));

        return album;
    }

    public void update(int id, String albumBeforeName, String albumAfterName) {
        remove(id, albumBeforeName);
        put(new Album(id, albumAfterName));
    }

    public void update(int id, String albumAfterName) {
        remove(id);
        put(new Album(id, albumAfterName));
    }

    public void update(String albumBeforeName, String albumAfterName) {
        update(-1, albumBeforeName, albumAfterName);
        Document document = new Document("name", albumBeforeName);
        int id = (int)((Document) albumColl.find(document).first()).get("id");
        remove(albumBeforeName);
        put(new Album(id, albumAfterName));
    }

    public int getNextId() {
        FindIterable<Document> queryResult = albumColl.find();
        int maxId = -1;
        for (Document document : queryResult) {
            int id = (int)document.get("id");
            if (id > maxId) {
                maxId = id;
            }
        }
        return  maxId + 1;
    }

    public ArrayList<Album> getAll() {
        ArrayList<Album> list = new ArrayList<>();
        FindIterable<Document> iter = albumColl.find();
        for (Document doc : iter) {
            list.add(new Album((int)doc.get("id"), (String)doc.get("name")));
        }
        return list;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        mongoClient.close();
    }

}
