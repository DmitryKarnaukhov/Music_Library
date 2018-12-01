package musiclibrary;

import musiclibrary.entities.Artist;
import musiclibrary.entities.Genre;
import musiclibrary.entities.Track;
import musiclibrary.entities.User;
import musiclibrary.mvc.controller.TrackController;
import musiclibrary.mvc.model.Model;
import musiclibrary.mvc.view.SaveLoadService;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException,InterruptedException {
        System.out.println(new File("").getAbsolutePath());


        SaveLoadService saveLoadService = new SaveLoadService();
        TrackController trackController = new TrackController(new Model<Track>());
        int id= trackController.addTrack("Get Rich", new Artist(0, "Tyga"), 1.22, Genre.Rap);
        System.out.println("Addad: "+ trackController.getTrack(id));

        saveLoadService.save(trackController.getTrackContainer());

        TrackController trackController1 = new TrackController(saveLoadService.load()[0]);
        System.out.println("Getted:" +  trackController1.getTrack(id));

    }
}
