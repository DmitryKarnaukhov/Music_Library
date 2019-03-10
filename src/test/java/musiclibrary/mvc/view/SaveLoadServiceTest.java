package musiclibrary.mvc.view;

import com.google.common.collect.ImmutableList;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
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

//@RunWith(Theories.class)
//public class SaveLoadServiceTest {
//
//    private static Injector injector = Guice.createInjector(new AbstractModule() {
//        @Override
//        protected void configure() {
//            bind(ModelTypes.class).annotatedWith(Names.named("Track")).toInstance(ModelTypes.Track);
//            bind(ModelTypes.class).annotatedWith(Names.named("Track")).toInstance(ModelTypes.Track);
//            bind(ModelTypes.class).annotatedWith(Names.named("Track")).toInstance(ModelTypes.Track);
//            bind(ModelTypes.class).annotatedWith(Names.named("Track")).toInstance(ModelTypes.Track);
//        }
//    });
//    @DataPoint
//    public static ArtistController artistController = injector.getInstance(ArtistController.class);
//    @DataPoint
//    public static TrackController trackController = injector.getInstance(TrackController.class);
//    @DataPoint
//    public static TrackListController trackListController = injector.getInstance(TrackListController.class);
//    @DataPoint
//    public static UserController userController = injector.getInstance(UserController.class);
//    private static SaveLoadService saveLoadService  = injector.getInstance(SaveLoadService.class);;
//
//    private void initTestModels() {
//        int testArtistId = artistController.add("Test artist");
//        int testTrackId = trackController.add("NewTrack", artistController.get(testArtistId), 3.0, Genre.Pop);
//        ArrayList<Track> trackArray = new ArrayList<Track>();
//        trackArray.add(trackController.get(testTrackId));
//        int testTrackListId = 0;
//        try {
//            testTrackListId = trackListController.add(new Album(0, "New Album"), ImmutableList.copyOf(trackArray));
//        } catch (InterruptedException e) {
//            throw new RuntimeException("TrackList controller addition exception: " + e.getMessage(), e);
//        }
//        ArrayList<TrackList> trackListArray = new ArrayList<TrackList>();
//        trackListArray.add(trackListController.get(testTrackListId));
//        try {
//            userController.add("First User", ImmutableList.copyOf(trackListArray));
//        } catch (InterruptedException e) {
//            throw new RuntimeException("User controller addition exception: " + e.getMessage(), e);
//        }
//    }
//
//    @Before
//    public void doItBeforeTest() {
//        initTestModels();
//    }
//
//    @Theory
//    public void saveLoadParametrizedTest(GenericController testController) {
//        Model testModel = testController.getContainer(),
//              loadedModel = null;
//        switch (testController.getClass().getSimpleName()) {
//            case "ArtistController":
//                loadedModel = saveLoadService.load(ModelTypes.Artist);
//                break;
//            case "TrackController":
//                loadedModel = saveLoadService.load(ModelTypes.Track);
//                break;
//            case "TrackListController":
//                loadedModel = saveLoadService.load(ModelTypes.TrackList);
//                break;
//            case "UserController":
//                loadedModel = saveLoadService.load(ModelTypes.User);
//                break;
//            default:
//                throw new RuntimeException("No current model found");
//        }
//// todo: controller у метод геттер на модел тайп
//        assertEquals(testModel, loadedModel);
//    }
//}