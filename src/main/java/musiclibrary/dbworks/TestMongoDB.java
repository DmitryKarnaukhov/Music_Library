package musiclibrary.dbworks;

import java.io.IOException;

public class TestMongoDB {
    public static void main(String[] args) {
        MongoConnector mongoConnector = new MongoConnector();
        System.out.println(mongoConnector.musName);
    }
}
