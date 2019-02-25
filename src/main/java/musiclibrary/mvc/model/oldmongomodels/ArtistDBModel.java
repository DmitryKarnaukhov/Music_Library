package musiclibrary.mvc.model.oldmongomodels;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import musiclibrary.entities.Artist;
import musiclibrary.mvc.model.Model;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Objects;

import static musiclibrary.dbworks.dbconstants.DBconstants.COLLECTION_ARTISTS;
import static musiclibrary.dbworks.dbconstants.DBconstants.DBHOST;
import static musiclibrary.dbworks.dbconstants.DBconstants.DBNAME;

public class ArtistDBModel extends Model {
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection artistColl;

    public ArtistDBModel(String host, String databaseName, String collName) {
        mongoClient = new MongoClient(host);//"localhost"
        mongoDatabase = mongoClient.getDatabase(databaseName);//"MusicLibraryDB"
        artistColl = mongoDatabase.getCollection(collName);//COLLECTION_ARTISTS
//        artistColl.countDocuments();
//        artistColl.drop();
//        artistColl = mongoDatabase.getCollection(collName);//COLLECTION_ARTISTS
//        artistColl.countDocuments();
    }

    public ArtistDBModel() {
        this(DBHOST, DBNAME, COLLECTION_ARTISTS);
    }

    public void put(Artist artist) {
//        put(artist.getName());
        Document document = new Document();
        document.append("id", artist.getId()).append("name", artist.getName());
        artistColl.insertOne(document);
    }

    public void put(String artistName) {
        Document document = new Document();
        document.append("id", getNextId()).append("name", artistName);
        artistColl.insertOne(document);
    }

    public boolean remove(int id) {
        return remove(id, null);
    }

    public boolean remove(String artistName) {
        Document document = new Document("name", artistName);
        return artistColl.deleteOne(document).wasAcknowledged();
//        return remove(getNextId(), artistName);
    }

    public boolean remove(int id, String artistName) {
        Document docToDel = new Document();
        if(id < 0 && artistName == null)
            throw new IllegalArgumentException("Both arguments have illegal values");
        if (id > 0) {
            docToDel.append("id", id);
        }
        if (artistName != null) {
            docToDel.append("name", artistName);
        }
        if (artistColl.findOneAndDelete(docToDel) != null) {
            return true;
        }
        return false;
    }

    public Artist getItem(int id){
        return getItem(id, null);
    }

    public Artist getItem(String artistName){
        return getItem(-1, artistName);
    }

    public Artist getItem(int id, String artistName){
        Document docToFind = new Document();
        if(id < 0 && artistName == null)
            throw new IllegalArgumentException("Both arguments have illegal values");
        if (id > 0) {
            docToFind.append("id", id);
        }
        if (artistName != null) {
            docToFind.append("name", artistName);
        }
        Object obj = artistColl.find(docToFind).first();
        Document doc = (Document) obj;
        Artist artist = new Artist(((int)doc.get("id")), (String)doc.get("name"));

        return artist;
    }

    public void update(int id, String artistBeforeName, String artistAfterName) {
        remove(id, artistBeforeName);
        put(new Artist(id, artistAfterName));
//        Document docToUp = new Document();
//        if(id < 0 && artistBeforeName == null)
//            throw new IllegalArgumentException("Both arguments have illegal values");
//        if (id > 0) {
//            docToUp.append("id", id);
//        } else {
//            id = (int)((Document)artistColl.find(docToUp.append("name", artistBeforeName)).first()).get("id");
//        }
//        if (artistBeforeName != null) {
//            docToUp.append("name", artistBeforeName);
//        }
//        Document newDoc = new Document();
//        newDoc.append("id", newDoc.append("id", id).append("name", artistAfterName));
//        artistColl.deleteOne(docToUp);
//        artistColl.insertOne(newDoc);
//        artistColl.updateOne(docToUp, newDoc);
    }

    public void update(int id, String artistAfterName) {
        remove(id);
        put(new Artist(id, artistAfterName));
    }

    public void update(String artistBeforeName, String artistAfterName) {
        update(-1, artistBeforeName, artistAfterName);
        Document document = new Document("name", artistBeforeName);
        int id = (int)((Document)artistColl.find(document).first()).get("id");
        remove(artistBeforeName);
        put(new Artist(id, artistAfterName));
    }

    public int getNextId() {
        FindIterable<Document> queryResult = artistColl.find();
        int maxId = -1;
        for (Document document : queryResult) {
            int id = (int)document.get("id");
            if (id > maxId) {
                maxId = id;
            }
        }
        return  maxId + 1;
    }

    public ArrayList<Artist> getAll() {
        ArrayList<Artist> list = new ArrayList<>();
        FindIterable<Document> iter = artistColl.find();
        for (Document doc : iter) {
            list.add(new Artist((int)doc.get("id"), (String)doc.get("name")));
        }
        return list;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        mongoClient.close();
    }

//        @Override
//    public int hashCode() {
//        return Objects.hash();
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Model<?> model = (Model<?>) o;
//        return com.google.common.base.Objects.equal(map, model.map);
//    }
}
