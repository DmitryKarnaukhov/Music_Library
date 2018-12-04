package com.company;

import com.google.inject.Key;
import com.google.inject.util.Types;
import musiclibrary.entities.*;
import musiclibrary.mvc.controller.ArtistController;
import musiclibrary.mvc.controller.TrackController;
import musiclibrary.mvc.controller.TrackListController;
import musiclibrary.mvc.controller.UserController;
import musiclibrary.mvc.model.Model;
import musiclibrary.mvc.view.SaveLoadService;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Inject;

import java.io.*;
import java.lang.reflect.ParameterizedType;

public class Main {
    public static void main(String[] args) throws IOException,InterruptedException {
        Injector injector = Guice.createInjector();
        System.out.println(new File("").getAbsolutePath());

//        Chocolate c = new Twix();
//        ParameterizedType pt = Types.newParameterizedType(ChocolateEater.class, c.getClass());
//        ChocolateEater<?> eater = (ChocolateEater<?>)injector.getInstance(Key.get(TypeLiteral.get(pt)));

//        Injector injector = Guice.createInjector(new MyModule());
//        Repository<Class1> repo1 = injector.getInstance(new Key<Repository<Class1>>() {});
//        Repository<Class2> repo2 = injector.getInstance(new Key<Repository<Class2>>() {});

        //Artist artist = injector.getInstance(Artist.class);
        Model<Artist> modelOfArtist = injector.getInstance(new Key<Model<Artist>>() {});
        //ArtistController artistController = new ArtistController(new Model<Artist>());
        ArtistController artistController = injector.getInstance(ArtistController.class);

        //TrackController trackController = new TrackController(new Model<Track>());
        TrackController trackController = injector.getInstance(TrackController.class);
        //TrackListController trackListController = new TrackListController((new Model<TrackList>()));
        TrackListController trackListController = injector.getInstance(TrackListController.class);
        //UserController userController = new UserController(new Model<User>());
        UserController userController = injector.getInstance(UserController.class);
        //SaveLoadService saveLoadService = new SaveLoadService(artistController,trackController,trackListController,userController);
        SaveLoadService saveLoadService = injector.getInstance(SaveLoadService.class);

        artistController.addListener(saveLoadService);
        trackController.addListener(saveLoadService);
        trackListController.addListener(saveLoadService);
        userController.addListener(saveLoadService);

        int tygaid = artistController.addArtist("Tyga");
        int id= trackController.addTrack("Get Rich", artistController.getArtist(tygaid), 1.22, Genre.Rap);
        trackController.addTrack("Too Rich",artistController.getArtist(tygaid),3.48,Genre.Rap);
        // System.out.println("Addad: "+ trackController.getTrack(id));

        //saveLoadService.save(trackController.getTrackContainer());

        TrackController trackController1 = new TrackController(saveLoadService.load()[1]);
        System.out.println("Getted:" +  trackController1.getTrack(id));

    }
}
