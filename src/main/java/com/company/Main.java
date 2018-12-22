package com.company;

import com.google.inject.*;
import com.google.inject.util.Types;
import musiclibrary.entities.*;
import musiclibrary.mvc.controller.*;
import musiclibrary.mvc.model.Model;
import musiclibrary.mvc.model.ModelTypes;
import musiclibrary.mvc.view.SaveLoadService;
import musiclibrary.ui.MainForm;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.ParameterizedType;

public class Main {
    public static void main(String[] args) throws IOException,InterruptedException {
        Injector injector = Guice.createInjector();
        ArtistController artistController = injector.getInstance(ArtistController.class);
        TrackController trackController = injector.getInstance(TrackController.class);
        TrackListController trackListController = injector.getInstance(TrackListController.class);
        UserController userController = injector.getInstance(UserController.class);
        SaveLoadService saveLoadService = injector.getInstance(SaveLoadService.class);

        int tygaid = artistController.add("Tyga");
        int id= trackController.add("Get Rich", artistController.get(tygaid), 1.22, Genre.Rap);
        int tr2 = trackController.add("Too Rich", artistController.get(tygaid), 3.48, Genre.Rap);
        trackController.change(id, "Get Rich", artistController.get(tygaid), 1.22, Genre.Rap);

        TrackController trackController1 = new TrackController(saveLoadService.load(ModelTypes.Track));
        System.out.println("Getted:" +  trackController1.get(id));
        System.out.println("Getted:" +  trackController1.get(tr2));
        //MainForm mainForm = new MainForm();
    }
}
