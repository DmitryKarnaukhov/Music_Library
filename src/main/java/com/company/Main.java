package com.company;

import com.google.inject.*;
import com.google.inject.util.Types;
import musiclibrary.entities.*;
import musiclibrary.mvc.controller.*;
import musiclibrary.mvc.model.Model;
import musiclibrary.mvc.view.SaveLoadService;
import musiclibrary.ui.MainForm;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.ParameterizedType;

public class Main {
    public static void main(String[] args) throws IOException,InterruptedException {
//        Injector injector = Guice.createInjector();
        System.out.println(new File("").getAbsolutePath());
        //Artist artist = injector.getInstance(Artist.class);
//        Model<Artist> modelOfArtist = injector.getInstance(new Key<Model<Artist>>() {});
//        modelOfArtist.getMap();
        //ArtistController artistController = new ArtistController(new Model<Artist>());
//        ArtistController artistController = injector.getInstance(ArtistController.class);
//        artistController.addArtist("fgdfgdf");
//        artistController.getArtist(0);

        //TrackController trackController = new TrackController(new Model<Track>());
//        TrackController trackController = injector.getInstance(TrackController.class);
        //TrackListController trackListController = new TrackListController((new Model<TrackList>()));
//        TrackListController trackListController = injector.getInstance(TrackListController.class);
        //UserController userController = new UserController(new Model<User>());
//        UserController userController = injector.getInstance(UserController.class);

        Injector injector = Guice.createInjector();
        ArtistController artistController = injector.getInstance(ArtistController.class);
        TrackController trackController = injector.getInstance(TrackController.class);
        TrackListController trackListController = injector.getInstance(TrackListController.class);
        UserController userController = injector.getInstance(UserController.class);

//        SaveLoadService saveLoadService = new SaveLoadService(artistController,trackController,trackListController,userController);
        SaveLoadService saveLoadService = injector.getInstance(SaveLoadService.class);

        artistController.addListener(saveLoadService);
        trackController.addListener(saveLoadService);
        trackListController.addListener(saveLoadService);
        userController.addListener(saveLoadService);

        int tygaid = artistController.addArtist("Tyga");
        int id= trackController.addTrack("Get Rich", artistController.getArtist(tygaid), 1.22, Genre.Rap);
        trackController.addTrack("Too Rich", artistController.getArtist(tygaid), 3.48, Genre.Rap);
        // System.out.println("Addad: "+ trackController.getTrack(id));

        //saveLoadService.save(trackController.getTrackContainer());

        TrackController trackController1 = new TrackController(saveLoadService.load()[1]);
        System.out.println("Getted:" +  trackController1.getTrack(id));
        MainForm mainForm = new MainForm();
    }
}
