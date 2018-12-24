package musiclibrary.mvc.view;

import com.google.common.collect.ImmutableList;
import com.google.inject.Guice;
import com.google.inject.Injector;
import musiclibrary.entities.*;
import musiclibrary.mvc.controller.*;
import musiclibrary.mvc.model.Model;
import static org.junit.Assert.*;

import org.junit.*;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import musiclibrary.mvc.model.ModelTypes;
import java.util.ArrayList;

@RunWith(Theories.class)
public class SaveLoadServiceTest {

    private static Injector injector = Guice.createInjector();
    @DataPoint
    public static ArtistController artistController = injector.getInstance(ArtistController.class);
    @DataPoint
    public static TrackController trackController = injector.getInstance(TrackController.class);
    @DataPoint
    public static TrackListController trackListController = injector.getInstance(TrackListController.class);
    @DataPoint
    public static UserController userController = injector.getInstance(UserController.class);
    private static SaveLoadService saveLoadService  = injector.getInstance(SaveLoadService.class);;

    private void initTestModels() {
        int testArtistId = artistController.add("Test artist");
        int testTrackId = trackController.add("NewTrack", artistController.get(testArtistId), 3.0, Genre.Pop);
        ArrayList<Track> trackArray = new ArrayList<Track>();
        trackArray.add(trackController.get(testTrackId));
        int testTrackListId = 0;
        try {
            testTrackListId = trackListController.add(new Album(0, "New Album"), ImmutableList.copyOf(trackArray));
        } catch (InterruptedException e) {
            throw new RuntimeException("TrackList controller addition exception: " + e.getMessage(), e);
        }
        ArrayList<TrackList> trackListArray = new ArrayList<TrackList>();
        trackListArray.add(trackListController.get(testTrackListId));
        try {
            userController.add("First User", ImmutableList.copyOf(trackListArray));
        } catch (InterruptedException e) {
            throw new RuntimeException("User controller addition exception: " + e.getMessage(), e);
        }
    }

    @Before
    public void doItBeforeTest() {
        initTestModels();
    }

    @Theory
    public void saveLoadParametrizedTest(GenericController testController) {
        Model testModel = testController.getContainer(),
              loadedModel = null;
        switch (testController.getClass().getSimpleName()) {
            case "ArtistController":
                loadedModel = saveLoadService.load(ModelTypes.Artist);
                break;
            case "TrackController":
                loadedModel = saveLoadService.load(ModelTypes.Track);
                break;
            case "TrackListController":
                loadedModel = saveLoadService.load(ModelTypes.TrackList);
                break;
            case "UserController":
                loadedModel = saveLoadService.load(ModelTypes.User);
                break;
            default:
                throw new RuntimeException("No current model found");
        }

        assertEquals(testModel, loadedModel);
    }
//
//    @Ignore("Obsolete test")
//    @Test
//    public void save() {
//        int NUMBER_OF_MODELS = 4;
//        Model<Artist> artistContainer = new Model<Artist>();
//        HashMap<Integer, Artist> artistMap = new HashMap<>();
//        Artist vasya = new Artist(0, "Vasya artist");
//        artistMap.put(0, vasya);
//        artistContainer.setMap(artistMap);
//        Model<Track> trackContainer = new Model<Track>();
//        HashMap<Integer, Track> trackMap = new HashMap<>();
//        Track testTrack = new Track(0, "song", vasya, 5.0, Genre.Pop);
//        trackMap.put(0, testTrack);
//        trackContainer.setMap(trackMap);
//        Model<TrackList> trackListContainer = new Model<TrackList>();
//        ArrayList<Track> trackList = new ArrayList<>();
//        trackList.add(testTrack);
//        TrackList newTrackList = new TrackList(0, new Album(0, "new Album"), ImmutableList.copyOf(trackList));
//        HashMap<Integer, TrackList> trackListMap = new HashMap<>();
//        trackListMap.put(0, newTrackList);
//        Model<User> userContainer = new Model<User>();
//        HashMap<Integer, User> userMap = new HashMap<>();
//        LinkedList<TrackList> trackListList = new LinkedList<>();
//        trackListList.add(newTrackList);
//        User myUser = new User(0, "test User", ImmutableList.copyOf(trackListList));
//        userMap.put(0, myUser);
//        userContainer.setMap(userMap);
//        ArtistController artistController = new ArtistController(artistContainer);
//        TrackController trackController = new TrackController(trackContainer);
//        TrackListController trackListController = new TrackListController(trackListContainer);
//        UserController userController = new UserController(userContainer);
//        SaveLoadService saveLoadService = new SaveLoadService(artistController.getContainer(), trackController.getContainer(), trackListController.getContainer(), userController.getContainer());
//        saveLoadService.save(artistController.getContainer(),ModelTypes.Artist);
//        saveLoadService.saveAll();
//        Model<Artist> artistModel = saveLoadService.load(ModelTypes.Artist);
//        Model<Track> trackModel = saveLoadService.load(ModelTypes.Track);
//        Model<TrackList> trackListModel = saveLoadService.load(ModelTypes.TrackList);
//        Model<User> userModel = saveLoadService.load(ModelTypes.User);
//        assertEquals(artistModel.getItem(0), artistContainer.getItem(0));
//        assertEquals(trackModel.getItem(0), trackContainer.getItem(0));
//        assertEquals(trackListModel.getItem(0), trackListContainer.getItem(0));
//        assertEquals(userModel.getItem(0), userContainer.getItem(0));
//    }
//
//    @Test
//    public void saveLoadEndToEnd() throws Exception {
////        Injector injector = Guice.createInjector();
//        ArtistController artistController = injector.getInstance(ArtistController.class);
//        TrackController trackController = injector.getInstance(TrackController.class);
//        TrackListController trackListController = injector.getInstance(TrackListController.class);
//        UserController userController = injector.getInstance(UserController.class);
//
//        SaveLoadService saveLoadService = injector.getInstance(SaveLoadService.class);
//
//        try {
//            artistController.add("Default artist");
//        } catch (Exception e) {
//            throw new Exception(e.getMessage());
//        }
//
//        assertEquals(artistController.getContainer(), saveLoadService.load(ModelTypes.Artist));
//    }
//
//
//    @Test
//    public void artistSaveLoadTest() throws Exception {
////        Injector injector = Guice.createInjector();
//        ArtistController artistController = injector.getInstance(ArtistController.class);
//
//        SaveLoadService saveLoadService = injector.getInstance(SaveLoadService.class);
//
//        try {
//            artistController.add("Default artist");
//        } catch (Exception e) {
//            throw new Exception(e.getMessage());
//        }
//
//        assertEquals(artistController.getContainer(), saveLoadService.load(ModelTypes.Artist));
//
//        artistController.del(0);
//
//        assertEquals(artistController.getContainer(), saveLoadService.load(ModelTypes.Artist));
//    }
//
//    @Test
//    public void trackSaveLoadTest() throws Exception {
////        Injector injector = Guice.createInjector();
//        TrackController trackController = injector.getInstance(TrackController.class);
//
//        SaveLoadService saveLoadService = injector.getInstance(SaveLoadService.class);
//
//        Artist defaultArtist = new Artist(0,"Default artist");
//
//        try {
//            trackController.add("Default track", defaultArtist, 3.0, Genre.Pop);
//        } catch (Exception e) {
//            throw new Exception(e.getMessage());
//        }
//
//        assertEquals(trackController.getContainer(), saveLoadService.load(ModelTypes.Track));
//    }
//
//    @Test
//    public void trackListSaveLoadTest() throws Exception {
//        Injector injector = Guice.createInjector();
//        TrackListController trackListController = injector.getInstance(TrackListController.class);
//
//        SaveLoadService saveLoadService = injector.getInstance(SaveLoadService.class);
//
//        Artist defaultArtist = new Artist(0, "Default artist");
//        Album defaultAlbum = new Album(0, "Default album");
//        LinkedList<Track> trackList = new LinkedList<>();
//        trackList.add(new Track(0, "Default track", defaultArtist, 3.0, Genre.Pop));
//
//        try {
//            trackListController.add(defaultAlbum,ImmutableList.copyOf(trackList));
//        } catch (InterruptedException e) {
//            throw new Exception(e.getMessage());
//        }
//
//        assertEquals(trackListController.getContainer(), saveLoadService.load(ModelTypes.TrackList));
//    }
//
//    @Test
//    public void userSaveLoadTest() throws Exception {
////        Injector injector = Guice.createInjector();
//        UserController userController = injector.getInstance(UserController.class);
//
//        SaveLoadService saveLoadService = injector.getInstance(SaveLoadService.class);
//
//
//        LinkedList<Track> tracks = new LinkedList<>();
//        Artist defaultArtist = new Artist(0, "Default artist");
//        tracks.add(new Track(0, "Default track", defaultArtist, 3.0, Genre.Pop));
//        Album defaultAlbum = new Album(0, "Default album");
//        TrackList defaultTrackList = new TrackList(0, defaultAlbum, ImmutableList.copyOf(tracks));
//        LinkedList<TrackList> defaultTrackLists = new LinkedList<>();
//        defaultTrackLists.add(defaultTrackList);
//
//        try {
//            userController.add("Default user", ImmutableList.copyOf(defaultTrackLists));
//        } catch (InterruptedException e) {
//            throw new Exception(e.getMessage());
//        }
//
//        assertEquals(userController.getContainer(), saveLoadService.load(ModelTypes.User));
//    }
//
//    @Test
//    public void deleteObjectTest() {
////        Injector injector = Guice.createInjector();
//        ArtistController artistController = injector.getInstance(ArtistController.class);
//        TrackController trackController = injector.getInstance(TrackController.class);
//        TrackListController trackListController = injector.getInstance(TrackListController.class);
//        UserController userController = injector.getInstance(UserController.class);
//
//        SaveLoadService saveLoadService = injector.getInstance(SaveLoadService.class);
//
//    }

}