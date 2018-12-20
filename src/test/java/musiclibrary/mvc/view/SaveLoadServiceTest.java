package musiclibrary.mvc.view;

import com.google.common.collect.ImmutableList;
import com.google.inject.Guice;
import com.google.inject.Injector;
import musiclibrary.entities.*;
import musiclibrary.mvc.controller.ArtistController;
import musiclibrary.mvc.controller.TrackController;
import musiclibrary.mvc.controller.TrackListController;
import musiclibrary.mvc.controller.UserController;
import musiclibrary.mvc.model.Model;
import static org.junit.Assert.*;

import org.junit.*;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

@RunWith(Theories.class)
public class SaveLoadServiceTest {

    private Injector injector;
    ArtistController artistController;
    SaveLoadService saveLoadService;
    TrackController trackController;
    TrackListController trackListController;
    UserController userController;
    @DataPoints
    public static Object[][] testData;// = new Object[][]{{"Model1", "Model2"}};

    @Before
    public void doItBeforeTest() throws Exception {
        injector = Guice.createInjector();
        artistController = injector.getInstance(ArtistController.class);
        saveLoadService = injector.getInstance(SaveLoadService.class);
        trackController = injector.getInstance(TrackController.class);
        trackListController = injector.getInstance(TrackListController.class);
        userController = injector.getInstance(UserController.class);

        artistController.addListener(saveLoadService);
        trackController.addListener(saveLoadService);
        trackListController.addListener(saveLoadService);
        userController.addListener(saveLoadService);

        try {
            artistController.addArtist("Default artist");
        } catch (InterruptedException e) {
            throw new Exception(e.getMessage());
        }
        Artist defaultArtist = new Artist(0,"Default artist");
        try {
            trackController.addTrack("Default track", defaultArtist, 3.0, Genre.Pop);
        } catch (InterruptedException e) {
            throw new Exception(e.getMessage());
        }
        Album defaultAlbum = new Album(0, "Default album");
        LinkedList<Track> trackList = new LinkedList<>();
        trackList.add(new Track(0, "Default track", defaultArtist, 3.0, Genre.Pop));
        try {
            trackListController.addTrackList(defaultAlbum, trackList);
        } catch (InterruptedException e) {
            throw new Exception(e.getMessage());
        }
        LinkedList<Track> tracks = new LinkedList<>();
        tracks.add(new Track(0, "Default track", defaultArtist, 3.0, Genre.Pop));
        TrackList defaultTrackList = new TrackList(0, defaultAlbum, ImmutableList.copyOf(tracks));
        LinkedList<TrackList> defaultTrackLists = new LinkedList<>();
        defaultTrackLists.add(defaultTrackList);
        try {
            userController.addUser("Default user", defaultTrackLists);
        } catch (InterruptedException e) {
            throw new Exception(e.getMessage());
        }
        testData = new Object[][]{
            { artistController.getArtistContainer(), saveLoadService.load()[0] },
            { trackController.getTrackContainer(), saveLoadService.load()[1] },
            { trackListController.getTrackListContainer(), saveLoadService.load()[2] },
            { userController.getUserContainer(), saveLoadService.load()[3] }
        };

    }

    @Theory
    public void saveLoadParameteraizedTest(final Object... testData) {
        assertEquals("Message", (Model)testData[0], (Model)testData[1]);
    }
//    @After
//    public void doItAfterTest() {
//        injector = null;
//    }


    @Ignore("Obsolete test")
    @Test
    public void save() {
        int NUMBER_OF_MODELS = 4;
        Model<Artist> artistContainer = new Model<Artist>();
        HashMap<Integer, Artist> artistMap = new HashMap<>();
        Artist vasya = new Artist(0, "Vasya artist");
        artistMap.put(0, vasya);
        artistContainer.setMap(artistMap);
        Model<Track> trackContainer = new Model<Track>();
        HashMap<Integer, Track> trackMap = new HashMap<>();
        Track testTrack = new Track(0, "song", vasya, 5.0, Genre.Pop);
        trackMap.put(0, testTrack);
        trackContainer.setMap(trackMap);
        Model<TrackList> trackListContainer = new Model<TrackList>();
        ArrayList<Track> trackList = new ArrayList<>();
        trackList.add(testTrack);
        TrackList newTrackList = new TrackList(0, new Album(0, "new Album"), ImmutableList.copyOf(trackList));
        HashMap<Integer, TrackList> trackListMap = new HashMap<>();
        trackListMap.put(0, newTrackList);
        Model<User> userContainer = new Model<User>();
        HashMap<Integer, User> userMap = new HashMap<>();
        LinkedList<TrackList> trackListList = new LinkedList<>();
        trackListList.add(newTrackList);
        User myUser = new User(0, "test User", ImmutableList.copyOf(trackListList));
        userMap.put(0, myUser);
        userContainer.setMap(userMap);
        ArtistController artistController = new ArtistController(artistContainer);
        TrackController trackController = new TrackController(trackContainer);
        TrackListController trackListController = new TrackListController(trackListContainer);
        UserController userController = new UserController(userContainer);
        SaveLoadService saveLoadService = new SaveLoadService(artistController, trackController, trackListController, userController);
        saveLoadService.save(artistContainer, trackContainer, trackListContainer, userContainer);
        Model[] models = saveLoadService.load();
        assertEquals(models.length, NUMBER_OF_MODELS);
        assertEquals(models[0].getMap().get(0), artistContainer.getMap().get(0));
        assertEquals(models[1].getMap().get(0), trackContainer.getMap().get(0));
        assertEquals(models[2].getMap().get(0), trackListContainer.getMap().get(0));
        assertEquals(models[3].getMap().get(0), userContainer.getMap().get(0));
    }

    @Test
    public void saveLoadEndToEnd() throws Exception {
//        Injector injector = Guice.createInjector();
        ArtistController artistController = injector.getInstance(ArtistController.class);
        TrackController trackController = injector.getInstance(TrackController.class);
        TrackListController trackListController = injector.getInstance(TrackListController.class);
        UserController userController = injector.getInstance(UserController.class);

        SaveLoadService saveLoadService = injector.getInstance(SaveLoadService.class);

        artistController.addListener(saveLoadService);
        trackController.addListener(saveLoadService);
        trackListController.addListener(saveLoadService);
        userController.addListener(saveLoadService);

        try {
            artistController.addArtist("Default artist");
        } catch (InterruptedException e) {
            throw new Exception(e.getMessage());
        }

        assertEquals(artistController.getArtistContainer(), saveLoadService.load()[0]);
    }


    @Test
    public void artistSaveLoadTest() throws Exception {
//        Injector injector = Guice.createInjector();
        ArtistController artistController = injector.getInstance(ArtistController.class);

        SaveLoadService saveLoadService = injector.getInstance(SaveLoadService.class);

        artistController.addListener(saveLoadService);

        try {
            artistController.addArtist("Default artist");
        } catch (InterruptedException e) {
            throw new Exception(e.getMessage());
        }

        assertEquals(artistController.getArtistContainer(), saveLoadService.load()[0]);

        artistController.delArtist(0);

        assertEquals(artistController.getArtistContainer(), saveLoadService.load()[0]);
    }

    @Test
    public void trackSaveLoadTest() throws Exception {
//        Injector injector = Guice.createInjector();
        TrackController trackController = injector.getInstance(TrackController.class);

        SaveLoadService saveLoadService = injector.getInstance(SaveLoadService.class);

        trackController.addListener(saveLoadService);

        Artist defaultArtist = new Artist(0,"Default artist");

        try {
            trackController.addTrack("Default track", defaultArtist, 3.0, Genre.Pop);
        } catch (InterruptedException e) {
            throw new Exception(e.getMessage());
        }

        assertEquals(trackController.getTrackContainer(), saveLoadService.load()[1]);
    }

    @Test
    public void trackListSaveLoadTest() throws Exception {
//        Injector injector = Guice.createInjector();
        TrackListController trackListController = injector.getInstance(TrackListController.class);

        SaveLoadService saveLoadService = injector.getInstance(SaveLoadService.class);

        trackListController.addListener(saveLoadService);

        Artist defaultArtist = new Artist(0, "Default artist");
        Album defaultAlbum = new Album(0, "Default album");
        LinkedList<Track> trackList = new LinkedList<>();
        trackList.add(new Track(0, "Default track", defaultArtist, 3.0, Genre.Pop));

        try {
            trackListController.addTrackList(defaultAlbum, trackList);
        } catch (InterruptedException e) {
            throw new Exception(e.getMessage());
        }

        assertEquals(trackListController.getTrackListContainer(), saveLoadService.load()[2]);
    }

    @Test
    public void userSaveLoadTest() throws Exception {
//        Injector injector = Guice.createInjector();
        UserController userController = injector.getInstance(UserController.class);

        SaveLoadService saveLoadService = injector.getInstance(SaveLoadService.class);

        userController.addListener(saveLoadService);

        LinkedList<Track> tracks = new LinkedList<>();
        Artist defaultArtist = new Artist(0, "Default artist");
        tracks.add(new Track(0, "Default track", defaultArtist, 3.0, Genre.Pop));
        Album defaultAlbum = new Album(0, "Default album");
        TrackList defaultTrackList = new TrackList(0, defaultAlbum, ImmutableList.copyOf(tracks));
        LinkedList<TrackList> defaultTrackLists = new LinkedList<>();
        defaultTrackLists.add(defaultTrackList);

        try {
            userController.addUser("Default user", defaultTrackLists);
        } catch (InterruptedException e) {
            throw new Exception(e.getMessage());
        }

        assertEquals(userController.getUserContainer(), saveLoadService.load()[3]);
    }

    @Test
    public void deleteObjectTest() {
//        Injector injector = Guice.createInjector();
        ArtistController artistController = injector.getInstance(ArtistController.class);
        TrackController trackController = injector.getInstance(TrackController.class);
        TrackListController trackListController = injector.getInstance(TrackListController.class);
        UserController userController = injector.getInstance(UserController.class);

        SaveLoadService saveLoadService = injector.getInstance(SaveLoadService.class);

        artistController.addListener(saveLoadService);
        trackController.addListener(saveLoadService);
        trackListController.addListener(saveLoadService);
        userController.addListener(saveLoadService);
    }

}