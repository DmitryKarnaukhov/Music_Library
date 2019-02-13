package musiclibrary.dbworks.dbcrud.documentwriters;

import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import musiclibrary.dbworks.dbcrud.documentcreators.DBArtistCreator;
import musiclibrary.dbworks.dbfactories.dbabstractfactories.DBCreatorFactory;
import musiclibrary.entities.Entity;

import static musiclibrary.dbworks.dbconstants.DBconstants.COLLECTION_ARTISTS;
import static musiclibrary.dbworks.dbconstants.DBconstants.DBHOST;
import static musiclibrary.dbworks.dbconstants.DBconstants.DBNAME;

public class DBArtistWriter {
    private MongoClient mongoClient;
    private MongoDatabase musicLibDB;

    public DBArtistWriter(Entity entity) {
        mongoClient = new MongoClient(DBHOST);
        musicLibDB = mongoClient.getDatabase(DBNAME);
        MongoCollection artistColl = musicLibDB.getCollection(COLLECTION_ARTISTS);
        DBCreatorFactory creatorFactory = new DBCreatorFactory();
        DBObject object = creatorFactory.createDBObject(entity);
        artistColl.insertOne(object);
        mongoClient.close();
    }
}
